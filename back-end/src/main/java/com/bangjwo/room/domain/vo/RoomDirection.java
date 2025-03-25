package com.bangjwo.room.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomDirection {
	EAST("동향"),
	WEST("서향"),
	SOUTH("남향"),
	NORTH("북향"),
	NORTHWEST("북서향"),
	NORTHEAST("북동향"),
	SOUTHWEST("남서향"),
	SOUTHEAST("남동향");

	private final String meaning;
}
