package com.bangjwo.chat.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public record ChatRoomDto() {
	@Schema(description = "채팅방 생성 요청 DTO")
	public record RequestDto(

		@Schema(description = "임대인 ID", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
		Long landlordId,

		@Schema(description = "임차인 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
		Long tenantId,

		@Schema(description = "매물 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
		Long roomId
	) {
	}

	@Builder
	@Schema(description = "채팅방 생성 응답 DTO")
	public record ResponseDto(

		@Schema(description = "임대인 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
		Long landlordId,

		@Schema(description = "임차인 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
		Long tenantId,

		@Schema(description = "매물 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
		Long roomId,

		@Schema(description = "채팅방 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
		Long chatRoomId
	) {
	}
}
