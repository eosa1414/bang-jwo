package com.bangjwo.global.common.error.registry;

import org.springframework.http.HttpStatus;

import com.bangjwo.global.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegistryErrorCode implements ErrorCode {
	NOT_FOUND_REGISTRY_INFO(HttpStatus.NOT_FOUND, "REGISTRY4001", "해당 요청에 대한 등기부 정보가 존재하지 않습니다."),
	FAIL_REGISTRY_DESERIALIZATION(HttpStatus.NOT_FOUND, "REGISTRY4002", "등기부등본 역직렬화에 실패하였습니다."),
	NO_AUTH_TO__REGISTRY(HttpStatus.FORBIDDEN, "REGISTRY4003", "해당 등기부등본에 접근할 권한이 없습니다."),
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
