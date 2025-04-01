package com.bangjwo.payment.application.dto;

import java.time.LocalDateTime;

import com.bangjwo.payment.domain.entity.Status;

public record PaymentDto() {
	public record RequestDto(
		Long memberId,
		Long roomId,
		Status status,
		LocalDateTime createdAt,
		LocalDateTime updatedAt
	) {}

	public record ResponseDto(
		String impUid,
		Long memberId,
		Long roomId,
		Status status,
		LocalDateTime createdAt,
		LocalDateTime updatedAt
	) {}
}
