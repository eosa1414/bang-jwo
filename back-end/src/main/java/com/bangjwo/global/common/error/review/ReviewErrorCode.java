package com.bangjwo.global.common.error.review;

import org.springframework.http.HttpStatus;

import com.bangjwo.global.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements ErrorCode {
	REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4001", "해당 리뷰가 존재하지 않습니다"),
	;
	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
