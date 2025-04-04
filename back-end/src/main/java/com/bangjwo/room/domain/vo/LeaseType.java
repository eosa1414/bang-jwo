package com.bangjwo.room.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "임대 유형 (보증금 있는 월세, 월세)")
public enum LeaseType {
	@Schema(description = "보증금 있는 월세")
	MONTHLY_WITH_DEPOSIT("보증금 있는 월세"),

	@Schema(description = "월세")
	PURE_MONTHLY("월세");

	private final String description;
}
