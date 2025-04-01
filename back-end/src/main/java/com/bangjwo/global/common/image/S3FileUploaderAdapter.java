package com.bangjwo.global.common.image;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bangjwo.global.common.error.GlobalErrorCodes;
import com.bangjwo.global.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class S3FileUploaderAdapter implements FileUploaderPort {
	private final S3Client s3Client;

	@Value("${aws.s3.bucket}")
	private String bucketName;

	@Value("${aws.region}")
	private String awsRegion;

	@Override
	public String upload(MultipartFile file, String directory, String fileName) {
		try {
			String key = directory + fileName;

			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.bucket(bucketName)
				.key(key)
				.build();

			s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

			return "https://" + bucketName + ".s3." + awsRegion + ".amazonaws.com/" + key;
		} catch (IOException e) {
			throw new BusinessException(GlobalErrorCodes.FAIL_IMAGE_UPLOAD);
		}
	}
}
