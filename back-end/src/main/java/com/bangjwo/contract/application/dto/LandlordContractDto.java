package com.bangjwo.contract.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LandlordContractDto {
	private Long roomId;           // 매물 ID
	private Integer deposit;       // 보증금
	private Integer monthlyRent;   // 월세
	private LocalDate availableFrom; // 입주 가능일 (예시)
	private LocalDate permissionDate; // 준공 승인일 (예시)

	// Address 기반
	private String postalCode;
	private String fullAddress;    // (province + city + district + addressDetail 등 합친 문자열)
	private BigDecimal lat;
	private BigDecimal lng;

	// 예: 건물 구조, 방 개수, etc. Room 필드를 가져와서 저장 가능
}
