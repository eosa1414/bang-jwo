package com.bangjwo.payment.application.dto;

import java.time.LocalDateTime;

import com.bangjwo.payment.domain.entity.Status;

import io.swagger.v3.oas.annotations.media.Schema;

public record PaymentDto() {

	@Schema(description = "사전 정보 입력 요청 DTO")
	public record RequestDto(
		@Schema(description = "유저 ID", example = "1")
		Long memberId,

		@Schema(description = "매물 ID", example = "1")
		Long roomId
	) {}


	@Schema(description = "사전 정보 입력 반환 DTO")
	public record ResponseDto(
		@Schema(description = "결제 ID", example = "342dea79-d")
		String impUid,

		@Schema(description = "유저 ID", example = "1")
		Long memberId,

		@Schema(description = "매물 ID", example = "1")
		Long roomId,

		@Schema(description = "거래 상태", example = "READY")
		Status status,

		@Schema(description = "사전정보 입력 날짜", example = "2025-03-31T15:45:00")
		LocalDateTime createdAt,

		@Schema(description = "결제 완료 날짜", example = "2025-03-31T15:45:00")
		LocalDateTime updatedAt
	) {}
}
