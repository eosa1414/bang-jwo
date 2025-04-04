package com.bangjwo.contract.application.dto.request;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "특약사항 DTO")
public class SpecialClauseDto {
	@Schema(description = "입주 신고일자", example = "2025-05-01")
	private LocalDate moveInRegistrationDate;

	@Schema(description = "미납금", example = "1000000")
	private Integer unpaidAmount;

	@Schema(description = "분쟁조정 동의 여부", example = "true")
	private Boolean disputeResolution;

	@Schema(description = "재건축 예정 여부", example = "false")
	private Boolean isHousingReconstructionPlanned;

	@Schema(description = "공사기간", example = "2025-06-01")
	private LocalDate constructionPeriod;

	@Schema(description = "예상 공사기간 (일)", example = "60")
	private Integer estimatedConstructionDuration;

	@Schema(description = "상세주소 제공 동의 여부", example = "true")
	private Boolean isDetailedAddressConsentGiven;

	@Schema(description = "기타 특약사항", example = "기타 특약사항 내용 작성")
	private List<String> etc;
}

