package com.bangjwo.global.common.error.blockchain;

import org.springframework.http.HttpStatus;

import com.bangjwo.global.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BlockchainErrorCode implements ErrorCode {
	BLOCKCHAIN_NOT_FOUND(HttpStatus.BAD_REQUEST, "BLOCKCHAIN4001", "블록체인을 조회할 수 없습니다."),
	BLOCKCHAIN_REJECTED(HttpStatus.BAD_REQUEST, "BLOCKCHAIN4002", "블록체인 등록에 실패했습니다."),
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}