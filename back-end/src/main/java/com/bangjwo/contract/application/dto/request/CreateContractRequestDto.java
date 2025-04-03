package com.bangjwo.contract.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "계약서 생성 요청 DTO")
public class CreateContractRequestDto {

	@Schema(description = "방 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull
	@Min(1)
	private Long roomId;

	@Schema(description = "임차인(유저) ID", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull
	@Min(1)
	private Long tenantId;
}
