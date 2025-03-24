package com.bangjwo.global.common.error.room;

import org.springframework.http.HttpStatus;

import com.bangjwo.global.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomErrorCode implements ErrorCode {
	FAIL_IMAGE_UPLOAD(HttpStatus.SERVICE_UNAVAILABLE, "ROOM4001", "이미지 업로드 및 저장에 실패하였습니다."),
	ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "ROOM4002", "부동산 매물 조회에 실패하였습니다."),
	KAKAO_PLACE_API_ERROR(HttpStatus.BAD_GATEWAY, "ROOM4004", "카카오 장소 API 호출에 실패하였습니다."),
	;
	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
