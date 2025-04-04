package com.bangjwo.contract.application.service;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PinataStorageService {

	private final String pinataApiKey = System.getenv("PINATA_API_KEY");
	private final String pinataSecretApiKey = System.getenv("PINATA_SECRET_KEY");

	private static final String PINATA_URL = "https://api.pinata.cloud/pinning/pinFileToIPFS";
	private static final String PINATA_GATEWAY_URL = "https://gateway.pinata.cloud/ipfs/";

	private final RestTemplate restTemplate = new RestTemplate();
	private final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 파일을 Pinata에 업로드하고 반환된 CID를 문자열로 반환합니다.
	 */
	public String uploadEncryptedPdf(byte[] encryptedPdf, long id) throws IOException {
		String fileName = "encrypted_contract_" + id + ".pdf";

		HttpHeaders headers = new HttpHeaders();
		headers.set("pinata_api_key", pinataApiKey);
		headers.set("pinata_secret_api_key", pinataSecretApiKey);
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

		// ByteArrayResource를 사용하여 파일명을 재정의
		ByteArrayResource resource = new ByteArrayResource(encryptedPdf) {
			@Override
			public String getFilename() {
				return fileName;
			}
		};
		body.add("file", resource);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(PINATA_URL, requestEntity, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			JsonNode root = objectMapper.readTree(response.getBody());
			// Pinata 응답에서 "IpfsHash" 필드가 CID임
			return root.path("IpfsHash").asText();
		} else {
			throw new IOException("Pinata 업로드 실패: " + response.getBody());
		}
	}

	public byte[] getFileFromIpfs(String cid) throws IOException {
		String url = PINATA_GATEWAY_URL + cid;
		ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
			throw new IOException("IPFS 파일 불러오기 실패 (HTTP " + response.getStatusCode() + ")");
		}
	}
}

