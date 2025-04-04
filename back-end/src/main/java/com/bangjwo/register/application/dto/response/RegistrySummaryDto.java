package com.bangjwo.register.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자별 등기부 등록 정보 요약 DTO.
 * RegistryDocument 엔티티의 BangJwoData 정보만 포함합니다.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@Schema(description = "사용자별 등기부 등록 정보 요약 DTO - 결제ID, 회원ID, 매물ID, JSON URL, PDF URL 포함")
public class RegistrySummaryDto {

	@Schema(description = "RegistryDocument의 MongoDB ID", example = "67e6332b68f0cb1ff92bf31e")
	private String id;

	@Schema(description = "결제 ID", example = "12")
	private String paymentId;

	@Schema(description = "회원 ID", example = "1")
	private Long memberId;

	@Schema(description = "매물 ID", example = "1")
	private Long roomId;

	@Schema(description = "풀 JSON URL (S3 접두어가 붙은 URL)", example = "https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/hyphen/hyphen.json")
	private String jsonUrl;

	@Schema(description = "풀 PDF URL (S3 접두어가 붙은 URL)", example = "https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/pdf-img/registry.pdf")
	private String pdfUrl;
}
