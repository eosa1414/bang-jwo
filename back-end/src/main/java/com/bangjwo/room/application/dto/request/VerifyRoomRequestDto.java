package com.bangjwo.room.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "매물 본인인증 요청 DTO")
public class VerifyRoomRequestDto {
	@Schema(description = "매물 ID", example = "1")
	Long roomId;

	@Schema(description = "포트원 본인인증 ID", example = "iv-1234567890")
	String identityVerificationId;
}
