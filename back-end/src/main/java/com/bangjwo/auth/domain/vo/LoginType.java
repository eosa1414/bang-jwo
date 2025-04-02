package com.bangjwo.auth.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginType {
	LOGIN("로그인 성공"),
	SIGNUP("회원가입 성공");

	private final String description;
}
