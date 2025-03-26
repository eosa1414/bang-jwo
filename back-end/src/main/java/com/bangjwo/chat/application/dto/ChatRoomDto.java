package com.bangjwo.chat.application.dto;

import lombok.Builder;

public record ChatRoomDto() {
	public record RequestDto(
		Long landlordId,
		Long tenantId,
		Long roomId
	) {
	}

	@Builder
	public record ResponseDto(
		Long landlordId,
		Long tenantId,
		Long roomId,
		Long chatRoomId
	) {
	}
}
