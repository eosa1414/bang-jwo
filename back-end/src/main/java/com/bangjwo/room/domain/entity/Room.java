package com.bangjwo.room.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bangjwo.global.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ROOM")
public class Room extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;

	@Column(nullable = false)
	private Long memberId;

	// ENUM('원룸투룸','아파트','빌라주택','오피스텔') → String으로 매핑
	@Column
	private String buildingType;

	// ENUM('집주인 인증 중','판매 중','판매 완료') → String으로 매핑
	@Column(nullable = false)
	private String status;

	@Column(length = 20, nullable = false)
	private String realEstateId;

	@Column(length = 20, nullable = false)
	private String roomNumber;

	@Column(nullable = false)
	private Integer deposit;

	@Column(nullable = false)
	private Integer monthlyRent;

	@Column(precision = 10, scale = 2)
	private BigDecimal exclusiveArea;

	@Column(precision = 10, scale = 2)
	private BigDecimal supplyArea;

	@Column
	private Integer totalUnits;

	@Column(length = 10)
	private String floor;

	@Column
	private Integer maxFloor;

	@Column(nullable = false)
	private Integer parkingSpaces;

	@Column(nullable = false)
	private LocalDate availableFrom;

	@Column(nullable = false)
	private LocalDate permitionDate;

	@Column
	private String simpleDescription;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	private Integer maintenanceCost;

	@Column
	private String maintenanceIncludedItems;

	@Column(nullable = false)
	private Byte roomCnt;

	@Column(nullable = false)
	private Byte bathroomCnt;

	// ENUM('북향','남향','동향','서향','남동향','북서향','북동향') → String으로 매핑
	@Column(nullable = false)
	private String direction;

	@Column(nullable = false)
	private Boolean verified;

	@Column(nullable = false)
	private Boolean registryPaid;
}

