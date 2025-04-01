package com.bangjwo.global.common.exception;

import com.bangjwo.global.common.error.ErrorCode;

import lombok.Getter;

@Getter
public class Exceptions extends BusinessException {
	public Exceptions(ErrorCode errorCode) {
		super(errorCode);
	}
}
