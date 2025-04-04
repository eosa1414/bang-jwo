package com.bangjwo.room.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "계약 유형 (신규 계약, 합의에 의한 재계약, 갱신계약)")
public enum ContractType {
	@Schema(description = "신규 계약")
	NEW("신규 계약"),

	@Schema(description = "합의에 의한 재계약")
	RENEW_BY_AGREEMENT("합의에 의한 재계약"),

	@Schema(description = "갱신계약")
	EXTENSION("갱신계약");

	private final String description;
}
