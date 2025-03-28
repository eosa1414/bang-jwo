package com.bangjwo.chat.application.dto;

import lombok.Builder;

@Builder
public record ChatAlertDto(
	Long chatRoomId,
	Long receiverId,
	Long senderId,
	String senderImage,
	String senderNickname,
	String message,
	String sendAt,
	boolean read
) {
}
