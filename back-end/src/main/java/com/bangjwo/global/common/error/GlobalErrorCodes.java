package com.bangjwo.global.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCodes implements ErrorCode {
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON4001", "잘못된 요청 정보 입니다."),

	NOT_FOUND_URL(HttpStatus.NOT_FOUND, "COMMON4002", "존재하지 않는 url"),

	INVALID_JSON_DATA(HttpStatus.BAD_REQUEST, "COMMON4003", "잘못된 형식의 JSON data"),

	INVALID_HEADER_DATA(HttpStatus.BAD_REQUEST, "COMMON4004", "잘못된 형식의 Header data"),

	FAIL_IMAGE_UPLOAD(HttpStatus.SERVICE_UNAVAILABLE, "COMMON4005", "이미지 업로드 및 저장에 실패하였습니다."),

	INVALID_INPUT_TYPE(HttpStatus.BAD_REQUEST, "COMMON4006", "잘못된 파일 형식입니다"),

	BINDING_ERROR(HttpStatus.BAD_REQUEST, "COMMON4007", "필드 값이 유효하지 않습니다."),

	INTERNAL_SERVER_ERROR(HttpStatus.BAD_REQUEST, "COMMON5001", "내부 서버 오류"),
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
