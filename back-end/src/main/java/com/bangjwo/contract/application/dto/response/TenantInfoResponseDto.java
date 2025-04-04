package com.bangjwo.contract.application.dto.response;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "임차인 계약 정보 응답 DTO")
public class TenantInfoResponseDto {

	@Schema(description = "임차인 이름", example = "김철수")
	private String name;

	@Schema(description = "임차인 전화번호", example = "010-1234-5678")
	private String phone;

	@Schema(description = "임차인 주소", example = "서울특별시 마포구 성산동")
	private String address;

	@Schema(description = "임차인 암호화된 주민등록번호", example = "123456-1234567")
	private String residentRegistrationNumber;

	@Schema(description = "입주일", example = "2025-05-01")
	private LocalDate moveInDate;

	@Schema(description = "서명 URL", example = "https://example.com/sign.png", nullable = true)
	private String tenantSignatureUrl;
}
