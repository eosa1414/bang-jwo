package com.bangjwo.chat.application.dto;

import java.time.LocalDateTime;

public record ChatRoomListDto(
	Long chatRoomId,
	Long roomId,
	Long otherId,
	Long unReadCount,
	LocalDateTime latestMessageTime
) {
}
