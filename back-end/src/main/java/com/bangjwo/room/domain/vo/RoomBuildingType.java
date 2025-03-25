package com.bangjwo.room.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomBuildingType {
	ONEROOM_TWOROOM("원룸/투룸"),
	APARTMENT("아파트"),
	VILLA_HOUSE("빌라/주택"),
	OFFICETEL("오피스텔");

	private final String meaning;
}

