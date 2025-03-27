package com.bangjwo.room.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MaintenanceIncludeName {
	WATER("수도료"),
	ELECTRICITY("전기료"),
	INTERNET("인터넷비"),
	GAS("가스비"),
	CLEANING("청소비"),
	CABLE_TV("유선 TV"),
	PARKING("주차비"),
	HEATING("난방비"),
	ELEVATOR_MAINTENANCE("승강기 유지비");

	private final String description;
}
