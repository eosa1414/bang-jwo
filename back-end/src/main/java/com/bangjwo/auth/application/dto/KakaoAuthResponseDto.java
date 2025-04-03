package com.bangjwo.auth.application.dto;

import com.bangjwo.auth.domain.vo.LoginType;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카카오 로그인 응답 DTO")
public record KakaoAuthResponseDto(

	@Schema(description = "로그인 타입 (LOGIN: 기존 회원, SIGNUP: 신규 회원)", example = "LOGIN")
	LoginType type,

	@Schema(description = "Access Token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
	String accessToken,

	@Schema(description = "Refresh Token", example = "dGhpc19pc19yZWZyZXNoX3Rva2Vu")
	String refreshToken

) {
}
