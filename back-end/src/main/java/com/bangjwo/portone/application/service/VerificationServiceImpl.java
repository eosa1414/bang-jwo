package com.bangjwo.portone.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bangjwo.global.common.error.portone.PortoneErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.member.domain.entity.Member;
import com.bangjwo.member.domain.repository.MemberRepository;
import com.bangjwo.portone.application.dto.VerificationDto;
import com.bangjwo.portone.application.dto.IdentityDto;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerificationServiceImpl implements VerificationService {

	private final MemberRepository memberRepository;

	@Value("${imp.secret.verificate}")
	private String portoneSecretKey;

	@Override
	public IdentityDto.IdentityResponse getVerification(String identityVerificationId) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "PortOne " + portoneSecretKey);
		headers.setContentType(MediaType.APPLICATION_JSON);

		// 인증 정보 조회 (GET)
		String getUrl = "https://api.portone.io/identity-verifications/" + identityVerificationId;
		HttpEntity<Void> getRequest = new HttpEntity<>(headers);

		ResponseEntity<JsonNode> response = restTemplate.exchange(
			getUrl, HttpMethod.GET, getRequest, JsonNode.class
		);

		JsonNode body = response.getBody();

		if (!"VERIFIED".equals(body.get("status").asText())) {
			throw new BusinessException(PortoneErrorCode.UNAUTHORIZED_USER);
		}

		JsonNode user = body.get("verifiedCustomer");
		if (user == null || !user.hasNonNull("name")) {
			throw new BusinessException(PortoneErrorCode.USER_INFO_MISMATCH);
		}

		return new IdentityDto.IdentityResponse(
			user.get("name").asText(),
			user.get("birthDate").asText(),
			user.get("phoneNumber").asText()
		);
	}


	@Override
	public VerificationDto vertificateContract(Long userId, VerificationDto dto) {

		Member member = memberRepository.findById(userId).orElseThrow();
		String name = member.getName();
		String birthDate = member.getBirthday();
		String phone = member.getPhone();

		if(!name.equals(dto.name()) || !birthDate.equals(dto.birthDate()) || !phone.equals(dto.phone()))
			throw new BusinessException(PortoneErrorCode.USER_INFO_MISMATCH);

		return dto;
	}
}
