package com.bangjwo.global.common.error.room;

import org.springframework.http.HttpStatus;

import com.bangjwo.global.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomErrorCode implements ErrorCode {
	NOT_FOUND_SEARCH_ROOM(HttpStatus.NOT_FOUND, "ROOM4001", "해당 매물 정보가 존재하지 않습니다"),
	ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "ROOM4003", "부동산 매물 조회에 실패하였습니다."),
	KAKAO_PLACE_API_ERROR(HttpStatus.BAD_GATEWAY, "ROOM4004", "카카오 장소 API 호출에 실패하였습니다."),
	NO_AUTH_TO_UPDATE_ROOM(HttpStatus.FORBIDDEN, "ROOM4005", "매물 정보를 수정할 권한이 없습니다"),
	NO_AUTH_TO_DELETE_ROOM(HttpStatus.FORBIDDEN, "ROOM4006", "매물 정보를 삭제할 권한이 없습니다"),
	NO_AUTH_TO_SEARCH_ROOM(HttpStatus.FORBIDDEN, "ROOM4007", "매물 정보를 조회할 권한이 없습니다"),
	NOT_FOUND_SEARCH_ROOM_IMAGE(HttpStatus.NOT_FOUND, "ROOM4008", "해당 매물이미지 정보가 존재하지 않습니다"),
	NOT_FOUND_SEARCH_ROOM_ADDRESS(HttpStatus.NOT_FOUND, "ROOM4009", "해당 매물의 주소 정보가 존재하지 않습니다"),
	INVALID_ROOM_STATUS(HttpStatus.BAD_REQUEST, "ROOM4010", "현재 상태에서는 매물을 판매 중으로 전환할 수 없습니다."),
	CONDITION_NOT_MET_FOR_ON_SALE(HttpStatus.BAD_REQUEST, "ROOM4011", "매물의 본인인증 및 등기부 등록이 완료되지 않았습니다."),
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
