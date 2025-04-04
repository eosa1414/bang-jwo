package com.bangjwo.contract.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Schema(description = "계약 완료 요청 DTO")
public class CompleteDto {

	@Schema(
		description = "계약 완료 문서 파일 (PDF 등)",
		requiredMode = Schema.RequiredMode.REQUIRED,
		type = "string",
		format = "binary"
	)
	@NotNull
	private MultipartFile file;

	@Schema(
		description = "계약 ID",
		example = "123",
		requiredMode = Schema.RequiredMode.REQUIRED
	)
	@NotNull
	private long contractId;

	@Schema(
		description = "임대인 ID",
		example = "101",
		requiredMode = Schema.RequiredMode.REQUIRED
	)
	@NotNull
	private long landlord;

	@Schema(
		description = "임차인 ID",
		example = "202",
		requiredMode = Schema.RequiredMode.REQUIRED
	)
	@NotNull
	private long tenant;
}
