package com.bangjwo.contract.domain.entity;

import com.bangjwo.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SPECIAL_CLAUSE")
public class SpecialClause extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long specialClauseId;

	@Column(nullable = false)
	private Long landlordInfoId;

	@Column(nullable = false)
	private LocalDate moveInRegistrationDate;

	private Integer unpaidAmount;

	@Column(nullable = false)
	private Boolean disputeResolution;

	@Column(nullable = false)
	private Boolean isHousingReconstructionPlanned;

	private LocalDate constructionPeriod;

	private Integer estimatedConstructionDuration;

	@Column(nullable = false)
	private Boolean isDetailedAddressConsentGiven;

	@Column
	private String etc;
}
