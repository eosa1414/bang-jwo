package com.bangjwo.auth.application.service;

import org.springframework.stereotype.Service;

import com.bangjwo.auth.application.dto.KakaoAuthResponseDto;
import com.bangjwo.auth.application.dto.response.KakaoUserInfo;
import com.bangjwo.auth.application.dto.response.MemberAuthDto;
import com.bangjwo.auth.infrastructure.JwtTokenProvider;
import com.bangjwo.member.application.service.MemberQueryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

	private final KakaoWebClientService kakaoWebClientService;
	private final JwtTokenProvider jwtTokenProvider;
	private final MemberQueryService memberQueryService;
	// private final RefreshTokenStore refreshTokenStore;

	/**
	 * 카카오 인가 코드를 통해 사용자 정보를 조회하고,
	 * 회원 로그인 또는 회원가입 처리를 진행한 후,
	 * JWT 토큰을 발급하여 응답합니다.
	 */
	public KakaoAuthResponseDto loginWithKakao(String authCode) {
		KakaoUserInfo kakaoUserInfo = kakaoWebClientService.loginWithKakaoAuthCode(authCode);
		MemberAuthDto memberAuthDto = memberQueryService.loginOrSignupByKakao(kakaoUserInfo);

		String accessToken = jwtTokenProvider.createToken(memberAuthDto);
		String refreshToken = jwtTokenProvider.createRefreshToken();
		// refreshTokenStore.saveToken(refreshToken, memberAuthDto);

		return new KakaoAuthResponseDto(memberAuthDto.getLoginType(), accessToken, refreshToken);
	}
}
