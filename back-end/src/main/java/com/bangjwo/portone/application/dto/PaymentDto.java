package com.bangjwo.portone.application.dto;

import java.time.LocalDateTime;

import com.bangjwo.portone.domain.entity.PaymentStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public record PaymentDto() {

	@Schema(description = "사전 정보 입력 요청 DTO")
	public record RequestDto(
		@Schema(description = "유저 ID", example = "1")
		Long memberId,

		@Schema(description = "매물 ID", example = "1")
		Long roomId
	) {
	}

	@Schema(description = "사전 정보 입력 반환 DTO")
	public record ResponseDto(
		@Schema(description = "포트원 imp_uid", example = "imp_342dea79")
		String impUid,

		@Schema(description = "주문번호 (merchant_uid)", example = "ORD1744113414535")
		String merchantUid,

		@Schema(description = "유저 ID", example = "1")
		Long memberId,

		@Schema(description = "매물 ID", example = "1")
		Long roomId,

		@Schema(description = "거래 상태", example = "READY")
		PaymentStatus status,

		@Schema(description = "등기부등본 PDF 상대 경로", example = "registry.pdf")
		String pdfUrl,

		@Schema(description = "등기부등본 JSON 상대 경로", example = "hyphen.json")
		String jsonUrl,

		@Schema(description = "사전정보 입력 날짜", example = "2025-03-31T15:45:00")
		LocalDateTime createdAt,

		@Schema(description = "결제 완료 날짜", example = "2025-03-31T15:45:00")
		LocalDateTime updatedAt
	) {
	}
}
