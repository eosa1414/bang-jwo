package com.bangjwo.portone.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "IdentityDto", description = "포트원 본인인증 관련 DTO")
public record IdentityDto() {

	@Schema(name = "IdentityRequest", description = "본인인증 조회 요청 DTO")
	public record IdentityRequest(
		@Schema(description = "포트원에서 발급한 본인인증 ID", example = "iv-a23895fd-xxxx-xxxx-xxxx-xxxxxxxxxxxx")
		String identityVerificationId
	) {
	}

	@Schema(name = "IdentityResponse", description = "포트원 본인인증 응답 DTO")
	public record IdentityResponse(
		@Schema(description = "이름", example = "홍길동")
		String name,

		@Schema(description = "생년월일", example = "19900101")
		String birthDate,

		@Schema(description = "휴대폰 번호", example = "01012345678")
		String phone
	) {
	}
}
