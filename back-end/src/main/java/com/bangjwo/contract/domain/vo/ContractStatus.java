package com.bangjwo.contract.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractStatus {
	BEFORE_WRITE("계약서 작성 전"),
	LANDLORD_COMPLETED("임대인 작성 완료"),
	TENANT_COMPLETED("임차인 작성 완료"),
	COMPLETED("계약 완료");

	private final String description;
}
