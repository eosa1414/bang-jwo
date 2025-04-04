package com.bangjwo.contract.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bangjwo.global.common.entity.BaseEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table
@Schema(description = "특약사항 엔티티")
public class SpecialClause extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "특약사항 ID", example = "1")
	private Long specialClauseId;

	@Column
	@Schema(description = "전입신고일자", example = "2025-05-01")
	private LocalDate moveInRegistrationDate;

	@Schema(description = "미납금", example = "200000")
	private Integer unpaidAmount;

	@Column
	@Schema(description = "분쟁 조정위원회 소정 여부", example = "true")
	private Boolean disputeResolution;

	@Column
	@Schema(description = "주택 철거 및 재건축 계획 여부", example = "false")
	private Boolean isHousingReconstructionPlanned;

	@Schema(description = "공사 시기", example = "2030-01-01")
	private LocalDate constructionPeriod;

	@Schema(description = "소요 기간", example = "12")
	private Integer estimatedConstructionDuration;

	@Column
	@Schema(description = "상세 주소 제공 동의 여부", example = "true")
	private Boolean isDetailedAddressConsentGiven;

	@ElementCollection
	@CollectionTable(
		name = "special_clause_etc",
		joinColumns = @JoinColumn(
			name = "special_clause_id",
			foreignKey = @ForeignKey(value = jakarta.persistence.ConstraintMode.NO_CONSTRAINT)
		)
	)
	@Column(name = "etc_value", columnDefinition = "TEXT")
	@Schema(description = "기타 특약사항 리스트", example = "[\"현 상태 유지 조건\", \"애완동물 금지\"]")
	private List<String> etc = new ArrayList<>();
}
