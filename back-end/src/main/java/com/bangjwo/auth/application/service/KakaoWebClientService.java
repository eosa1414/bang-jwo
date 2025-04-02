package com.bangjwo.auth.application.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.bangjwo.auth.application.dto.response.KakaoUserInfo;
import com.bangjwo.global.common.error.auth.AuthErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.global.config.kakao.KakaoConfig;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KakaoWebClientService {
	private static final String KAKAO_OAUTH_URL = "https://kauth.kakao.com/oauth/token";
	private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

	private final WebClient webClient;
	private final KakaoConfig kakaoConfig;

	public KakaoUserInfo loginWithKakaoAuthCode(String authCode) {
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("grant_type", "authorization_code");
		formData.add("client_id", kakaoConfig.getClientId());
		formData.add("redirect_uri", kakaoConfig.getRedirectUri());
		formData.add("code", authCode);

		Map<String, Object> tokenMap = webClient.post()
			.uri(KAKAO_OAUTH_URL)
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.body(BodyInserters.fromFormData(formData))
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
			})
			.block();

		if (tokenMap == null || !tokenMap.containsKey("access_token")) {
			throw new BusinessException(AuthErrorCode.KAKAO_TOKEN_REQUEST_FAILED);
		}

		String accessToken = (String)tokenMap.get("access_token");
		KakaoUserInfo userInfo = getKakaoUserInfo(accessToken);

		if (userInfo == null || userInfo.getKakaoId() == null) {
			throw new BusinessException(AuthErrorCode.KAKAO_USER_INFO_REQUEST_FAILED);
		}

		return userInfo;
	}

	/**
	 * GET 요청 (Authorization 헤더 포함)
	 */
	public KakaoUserInfo getKakaoUserInfo(String accessToken) {
		return webClient.get()
			.uri(KAKAO_USER_INFO_URL)
			.header("Authorization", "Bearer " + accessToken)
			.retrieve()
			.bodyToMono(KakaoUserInfo.class)
			.block();
	}

}

