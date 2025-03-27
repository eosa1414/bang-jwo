package com.bangjwo.room.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "매물 건물 유형")
public enum RoomBuildingType {
	@Schema(description = "원룸/투룸")
	ONEROOM_TWOROOM("원룸/투룸"),

	@Schema(description = "아파트")
	APARTMENT("아파트"),

	@Schema(description = "빌라/주택")
	VILLA_HOUSE("빌라/주택"),

	@Schema(description = "오피스텔")
	OFFICETEL("오피스텔");

	private final String meaning;
}

