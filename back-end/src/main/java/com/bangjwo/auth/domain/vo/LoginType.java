package com.bangjwo.auth.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "기존 유저 로그인인지, 신규 가입인지 구분을 위한 로그인 타입")
public enum LoginType {

	@Schema(description = "기존 회원 로그인")
	LOGIN("로그인 성공"),

	@Schema(description = "신규 회원 가입")
	SIGNUP("회원가입 성공");

	private final String description;
}
