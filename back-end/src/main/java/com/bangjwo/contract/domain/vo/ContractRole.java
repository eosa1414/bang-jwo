package com.bangjwo.contract.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "계약 참여자 역할", example = "LANDLORD", allowableValues = {"LANDLORD", "TENANT"})
public enum ContractRole {

	@Schema(description = "임대인 - 매물을 등록하고 계약을 진행하는 사용자")
	LANDLORD,

	@Schema(description = "임차인 - 매물을 보고 계약을 체결하는 사용자")
	TENANT
}
