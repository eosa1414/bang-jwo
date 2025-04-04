package com.bangjwo.room.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "매물 옵션 목록")
public enum RoomOption {
	@Schema(description = "엘리베이터")
	ELEVATOR("엘리베이터"),

	@Schema(description = "옥탑")
	ROOFTOP("옥탑"),

	@Schema(description = "에어컨")
	AIR_CONDITIONER("에어컨"),

	@Schema(description = "세탁기")
	WASHING_MACHINE("세탁기"),

	@Schema(description = "냉장고")
	REFRIGERATOR("냉장고"),

	@Schema(description = "전자렌지")
	MICROWAVE("전자렌지"),

	@Schema(description = "가스렌지")
	GAS_RANGE("가스렌지"),

	@Schema(description = "인덕션")
	INDUCTION("인덕션"),

	@Schema(description = "침대")
	BED("침대");

	private final String meaning;
}
