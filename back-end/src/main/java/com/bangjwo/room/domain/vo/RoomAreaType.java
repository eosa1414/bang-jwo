package com.bangjwo.room.domain.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomAreaType {
	ALL(null, null),
	UNDER_TEN(BigDecimal.ZERO, new BigDecimal("10")), // 10평 미만
	TEN_RANGE(new BigDecimal("10"), new BigDecimal("20")),
	TWENTY_RANGE(new BigDecimal("20"), new BigDecimal("30")),
	THIRTY_RANGE(new BigDecimal("30"), new BigDecimal("40")),
	FORTY_RANGE(new BigDecimal("40"), new BigDecimal("50")),
	OVER_FIFTY(new BigDecimal("50"), null); // 50평 이상

	private final BigDecimal min;
	private final BigDecimal max;
}