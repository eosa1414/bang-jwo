package com.bangjwo.global.common.error.member;

import org.springframework.http.HttpStatus;

import com.bangjwo.global.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements ErrorCode {
	NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "MEMBER4001", "해당 회원을 찾을 수 없습니다."),
	DUPLICATED_MEMBER(HttpStatus.CONFLICT, "MEMBER4002", "이미 존재하는 회원입니다."),
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
