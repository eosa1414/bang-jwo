package com.bangjwo.contract.application.dto.response;

import com.bangjwo.contract.domain.vo.ContractStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "계약서 상태 응답 DTO")
public class ContractStatusResponseDto {

	@Schema(description = "계약 ID", example = "1")
	private Long contractId;

	@Schema(description = "계약 상태", example = "DRAFT", allowableValues = {"DRAFT", "COMPLETED", "SIGNED", "CANCELLED"})
	private ContractStatus status;
}
