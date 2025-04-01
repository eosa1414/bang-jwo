package com.bangjwo.register.application.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bangjwo.global.common.error.registry.RegistryErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.register.application.dto.RegistryHyphenDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

/**
 * S3에서 JSON 다운로드 -> Jackson -> RegistryHyphenDto
 */
@Service
@RequiredArgsConstructor
public class HyphenParsingService implements HyphenParsingInterface {

	private final S3Client s3Client;
	private final ObjectMapper objectMapper;

	@Value("${aws.s3.bucket}")
	public String BUCKET_NAME; // 실제 버킷명

	@Override
	public RegistryHyphenDto parseHyphenJson(String s3Key) {
		byte[] jsonBytes = downloadFromS3(s3Key);

		try {
			return objectMapper.readValue(jsonBytes, RegistryHyphenDto.class);
		} catch (IOException e) {
			throw new BusinessException(RegistryErrorCode.FAIL_REGISTRY_DESERIALIZATION);
		}
	}

	private byte[] downloadFromS3(String s3Key) {
		var req = GetObjectRequest.builder()
			.bucket(BUCKET_NAME)
			.key(s3Key)
			.build();

		try (InputStream s3is = s3Client.getObject(req);
			 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

			byte[] buffer = new byte[4096];
			int len;
			while ((len = s3is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			throw new BusinessException(RegistryErrorCode.NOT_FOUND_REGISTRY_INFO);
		}
	}
}
