package com.bangjwo.room.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomOption {
	ELEVATOR("엘리베이터"),
	ROOFTOP("옥탑"),
	AIR_CONDITIONER("에어컨"),
	WASHING_MACHINE("세탁기"),
	REFRIGERATOR("냉장고"),
	MICROWAVE("전자렌지"),
	GAS_RANGE("가스렌지"),
	INDUCTION("인덕션"),
	BED("침대");

	private final String meaning;
}
