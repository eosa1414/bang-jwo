package com.bangjwo.room.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "매물 방향")
public enum RoomDirection {
	@Schema(description = "동향")
	EAST("동향"),

	@Schema(description = "서향")
	WEST("서향"),

	@Schema(description = "남향")
	SOUTH("남향"),

	@Schema(description = "북향")
	NORTH("북향"),

	@Schema(description = "북서향")
	NORTHWEST("북서향"),

	@Schema(description = "북동향")
	NORTHEAST("북동향"),

	@Schema(description = "남서향")
	SOUTHWEST("남서향"),

	@Schema(description = "남동향")
	SOUTHEAST("남동향");

	private final String meaning;
}
