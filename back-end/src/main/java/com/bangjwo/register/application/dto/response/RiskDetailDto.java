package com.bangjwo.register.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RiskDetailDto {
	// 분석한 항목의 이름 (예: 가처분, 가압류, 근저당권 등)
	private String category;

	// 해당 항목에 포함된 구체적인 정보 (예: "민간임대주택등기 1건")
	private String info;

	// 위험 수준 (예: 낮음, 중간, 높음)
	private String riskLevel;

	// 추가적인 설명
	private String description;
}
