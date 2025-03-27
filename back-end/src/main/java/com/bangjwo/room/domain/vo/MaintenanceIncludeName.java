package com.bangjwo.room.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "매물 관리비 포함 목록")
public enum MaintenanceIncludeName {
	@Schema(description = "수도료")
	WATER("수도료"),

	@Schema(description = "전기료")
	ELECTRICITY("전기료"),

	@Schema(description = "인터넷비")
	INTERNET("인터넷비"),

	@Schema(description = "가스비")
	GAS("가스비"),

	@Schema(description = "청소비")
	CLEANING("청소비"),

	@Schema(description = "유선 TV")
	CABLE_TV("유선 TV"),

	@Schema(description = "주차비")
	PARKING("주차비"),

	@Schema(description = "난방비")
	HEATING("난방비"),

	@Schema(description = "승강기 유지비")
	ELEVATOR_MAINTENANCE("승강기 유지비");

	private final String description;
}
