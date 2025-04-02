package com.bangjwo.register.application.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AnalysisResultDto {
	// 단순 key-value 정보 외에도,
	// 각 항목에 대한 자세한 위험 정보를 리스트로 저장할 수 있음
	private Map<String, String> details;
	private List<RiskDetailDto> riskDetails = new ArrayList<>();

	public AnalysisResultDto() {
	}

	public void addRiskDetail(RiskDetailDto riskDetail) {
		riskDetails.add(riskDetail);
	}
}