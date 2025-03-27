package com.bangjwo.room.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "매물 상태")
public enum RoomStatus {
	@Schema(description = "집주인 인증 중")
	UNDER_VERIFICATION("집주인 인증 중"),

	@Schema(description = "판매 중")
	ON_SALE("판매 중"),

	@Schema(description = "판매 완료")
	SOLD_OUT("판매 완료");

	private final String meaning;
}
