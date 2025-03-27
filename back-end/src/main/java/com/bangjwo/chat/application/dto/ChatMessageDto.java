package com.bangjwo.chat.application.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ChatMessageDto(
	Long chatRoomId,
	Long roomId,
	Long receiverId,
	Long senderId,
	String senderNickname,
	String message,
	String sendAt,
	boolean read
) {
}
