package com.bangjwo.chat.application.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "채팅 요청 DTO")
public record ChatMessageDto(

	@Schema(description = "채팅방 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	Long chatRoomId,

	@Schema(description = "매물 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	Long roomId,

	@Schema(description = "상대방 ID", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
	Long receiverId,

	@Schema(description = "사용자 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	Long senderId,

	@Schema(description = "사용자 닉네임", example = "InJun", requiredMode = Schema.RequiredMode.REQUIRED)
	String senderNickname,

	@Schema(description = "메세지", example = "Hi", requiredMode = Schema.RequiredMode.REQUIRED)
	String message,

	@Schema(description = "보낸 시간", example = "2025-03-31T15:45:00", requiredMode = Schema.RequiredMode.REQUIRED)
	String sendAt,

	@Schema(description = "읽음 여부", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
	boolean read
) {
}
