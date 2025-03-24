package com.bangjwo.global.common.error.room;

import org.springframework.http.HttpStatus;

import com.bangjwo.global.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomErrorCode implements ErrorCode {
	NOT_FOUND_SEARCH_ROOM(HttpStatus.NOT_FOUND, "ROOM4001", "해당 매물 정보가 존재하지 않습니다"),
	FAIL_IMAGE_UPLOAD(HttpStatus.SERVICE_UNAVAILABLE, "ROOM4002", "이미지 업로드 및 저장에 실패하였습니다."),
	ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "ROOM4003", "부동산 매물 조회에 실패하였습니다."),
	KAKAO_PLACE_API_ERROR(HttpStatus.BAD_GATEWAY, "ROOM4004", "카카오 장소 API 호출에 실패하였습니다."),
	NO_AUTH_TO_UPDATE_ROOM(HttpStatus.FORBIDDEN, "ROOM4005", "매물 정보를 수정할 권한이 없습니다"),
	NOT_FOUND_SEARCH_ROOM_IMAGE(HttpStatus.NOT_FOUND, "ROOM4006", "해당 매물이미지 정보가 존재하지 않습니다"),
	NOT_FOUND_SEARCH_ROOM_ADDRESS(HttpStatus.NOT_FOUND, "ROOM4007", "해당 매물의 주소 정보가 존재하지 않습니다"),
	;
	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
