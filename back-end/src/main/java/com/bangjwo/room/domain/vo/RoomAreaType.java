package com.bangjwo.room.domain.vo;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "매물 평수")
public enum RoomAreaType {
	@Schema(description = "모든 평수 조회")
	ALL(null, null),

	@Schema(description = "10평 미만")
	UNDER_TEN(BigDecimal.ZERO, new BigDecimal("10")),

	@Schema(description = "10 ~ 20평")
	TEN_RANGE(new BigDecimal("10"), new BigDecimal("20")),

	@Schema(description = "20 ~ 30평")
	TWENTY_RANGE(new BigDecimal("20"), new BigDecimal("30")),

	@Schema(description = "30 ~ 40평")
	THIRTY_RANGE(new BigDecimal("30"), new BigDecimal("40")),

	@Schema(description = "40 ~ 50평")
	FORTY_RANGE(new BigDecimal("40"), new BigDecimal("50")),

	@Schema(description = "50평 이상")
	OVER_FIFTY(new BigDecimal("50"), null);

	private final BigDecimal min;
	private final BigDecimal max;
}