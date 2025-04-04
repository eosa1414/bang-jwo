package com.bangjwo.contract.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(
	description = "월세 납부 방식",
	example = "PREPAID",
	allowableValues = {"PREPAID", "POSTPAID"}
)
public enum MonthlyRentType {

	@Schema(description = "선불 (월세를 해당 월 시작 전에 지불)")
	PREPAID("선불"),

	@Schema(description = "후불 (월세를 해당 월이 지난 후에 지불)")
	POSTPAID("후불");

	private final String description;
}
