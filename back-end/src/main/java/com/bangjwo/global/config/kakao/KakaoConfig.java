package com.bangjwo.global.config.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class KakaoConfig {
	@Value("${registration.kakao.client-id}")
	private String clientId;

	@Value("${registration.kakao.redirect-uri}")
	private String redirectUri;

	@Value("${registration.kakao.scope}")
	private String scope;

	@Value("${registration.kakao.authorization-grant-type}")
	private String authorizationGrantType;

	@Value("${registration.kakao.client-name}")
	private String clientName;

	@Value("${provider.kakao.authorization-uri}")
	private String authorizationUri;

	@Value("${provider.kakao.token-uri}")
	private String tokenUri;

	@Value("${provider.kakao.user-info-uri}")
	private String userInfoUri;

	@Value("${provider.kakao.user-name-attribute}")
	private String userNameAttribute;
}