package com.bangjwo.room.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomStatus {
	UNDER_VERIFICATION("집주인 인증 중"),
	ON_SALE("판매 중"),
	SOLD_OUT("판매 완료");

	private final String meaning;
}
