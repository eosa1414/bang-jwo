package com.bangjwo.contract.application.dto.request;

import com.bangjwo.contract.domain.vo.ContractRole;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "임대인/임차인 계약서 본인인증 요청 DTO")
public record VerifyContractMemberDto(
	@Schema(description = "계약 ID", example = "1")
	Long contractId,

	@Schema(description = "포트원 본인인증 ID", example = "iv-1234567890")
	String identityVerificationId,

	@Schema(description = "역할 (LANDLORD OR TENANT)", example = "LANDLORD")
	ContractRole role
) {
}
