package com.bangjwo.global.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisLockErrorCode implements ErrorCode {
	LOCKED_RESOURCE(HttpStatus.CONFLICT, "LOCK4001", "현재 다른 사용자가 해당 리소스를 사용 중입니다. 잠시 후 다시 시도해주세요."),
	LANDLORD_IN_PROGRESS(HttpStatus.CONFLICT, "LOCK4002", "현재 임대인이 계약서를 작성 중입니다. 잠시 후 다시 시도해주세요."),
	TENANT_IN_PROGRESS(HttpStatus.CONFLICT, "LOCK4003", "현재 임차인이 계약서에 서명 중으로 계약서 수정이 불가능합니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
