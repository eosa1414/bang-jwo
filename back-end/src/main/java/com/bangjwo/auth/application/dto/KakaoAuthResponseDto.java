package com.bangjwo.auth.application.dto;

import com.bangjwo.auth.domain.vo.LoginType;

public record KakaoAuthResponseDto(LoginType type, String accessToken, String refreshToken) {
}
