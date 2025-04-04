package com.bangjwo.global.common.exception;

import com.bangjwo.global.common.error.ErrorCode;

import lombok.Getter;

@Getter
public class RoomException extends BusinessException {
	public RoomException(ErrorCode errorCode) {
		super(errorCode);
	}
}
