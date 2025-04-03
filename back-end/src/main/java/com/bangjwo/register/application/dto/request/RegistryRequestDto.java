package com.bangjwo.register.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 등기부 등록 시, 결제ID, 유저ID, 매물ID, S3 JSON URL, S3 PDF Image URL를 담는 요청 DTO
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@Schema(description = "등기부 등록 요청 DTO - 결제ID, 유저ID, 매물ID, S3 JSON URL, S3 PDF Image URL 포함")
public class RegistryRequestDto {

	@Schema(description = "결제 ID", example = "12")
	private String paymentId;

	@Schema(description = "매물 ID", example = "1")
	private Long roomId;

	@Schema(description = "S3 JSON 파일 상대 경로", example = "hyphen.json")
	private String jsonUrl;

	@Schema(description = "S3 PDF Image 파일 상대 경로", example = "registry.pdf")
	private String pdfUrl;
}
