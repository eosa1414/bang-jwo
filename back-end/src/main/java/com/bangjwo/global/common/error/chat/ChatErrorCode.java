package com.bangjwo.global.common.error.chat;

import org.springframework.http.HttpStatus;

import com.bangjwo.global.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatErrorCode implements ErrorCode {

	// RDB 채팅방 관련 에러
	CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "CHATROOM4001", "채팅방 정보가 존재하지 않습니다."),
	CHAT_ROOM_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "CHATROOM4002", "채팅방 저장에 실패하였습니다."),

	// Redis 채팅방 관련 에러
	CHAT_REDIS_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "CHAT4001", "채팅방 정보가 존재하지 않습니다."),
	CHAT_REDIS_SERIALIZATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "CHAT5001", "Redis 직렬화 실패."),
	CHAT_REDIS_OPERATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "CHAT5002", "Redis 작업 중 오류가 발생하였습니다."),

	// 채팅 메시지 관련 에러
	CHAT_MESSAGE_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "CHATMESSAGE4001", "채팅 메시지 저장에 실패하였습니다."),
	CHAT_MESSAGE_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "CHATMESSAGE4002", "채팅 메시지 읽음 처리에 실패하였습니다."),
	CHAT_MESSAGE_RETRIEVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "CHATMESSAGE4003", "채팅 내역 조회에 실패하였습니다."),

	// 알림 관련 에러
	CHAT_ALERT_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "CHATALERT4001", "알림 저장에 실패하였습니다."),
	CHAT_ALERT_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "CHATALERT4002", "알림 읽음 처리에 실패하였습니다."),
	CHAT_ALERT_RETRIEVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "CHATALERT4003", "알림 조회에 실패하였습니다.");

	;
	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
