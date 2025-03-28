package com.bangjwo.contract.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContractRequestDto {
	@NotNull
	@Min(1)
	private Long roomId;

	@NotNull
	@Min(1)
	private Long landlordId;

	@NotNull
	@Min(1)
	private Long tenantId;
}
