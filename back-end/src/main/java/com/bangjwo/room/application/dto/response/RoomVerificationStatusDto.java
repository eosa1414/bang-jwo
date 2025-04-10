package com.bangjwo.room.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "계약서 임대인/임차인 본인 인증 여부 응답 DTO")
public class RoomVerificationStatusDto {
	@Schema(description = "매물 본인인증 여부", example = "true")
	private boolean verified;

	@Schema(description = "매물 결제인증 여부", example = "true")
	private boolean registryPaid;
}
