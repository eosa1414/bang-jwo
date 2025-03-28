package com.bangjwo.contract.domain.entity;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "LANDLORD_INFO")
public class LandlordInfo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long landlordInfoId;

	@Column(nullable = false)
	private Long landlordId;

	@Column(length = 20)
	private String name;

	@Column(length = 20)
	private String phoneNumber;

	@Column(columnDefinition = "TEXT")
	private String address;

	@Column(nullable = false)
	private String residentRegistrationNumber; // 암호화

	@Column(columnDefinition = "TEXT")
	private String rentalPropertyAddress;

	@Column
	private String propertyStructure;

	@Column
	private String propertyPurpose;

	@Column(precision = 10, scale = 2)
	private BigDecimal propertyArea;

	@Column(columnDefinition = "TEXT")
	private String repairResponsibility;

	@Column
	private Boolean priorityConfirmedDateYn;

	@Column
	private Boolean taxArrears;

	@Column(length = 100)
	private String locationOfRentalHousing;

	@Column
	private String rentalHousingLandType;

	private BigDecimal rentalHousingLandArea;

	@Column(length = 50)
	private String rentalHousingUsage;

	private BigDecimal rentalHousingArea;

	@Column(length = 50)
	private String rentalPartAddress;

	private BigDecimal rentalPartArea;

	// ENUM('신규 계약','합의에 의한 재계약','갱신계약') → String
	@Column
	private String contractType;

	// ENUM('보증금 있는 월세','월세') → String
	@Column
	private String leaseType;

	@Column
	private Long depositAmount;

	@Column
	private Long monthlyRent;

	@Column
	private LocalDate leaseStartDate;

	@Column
	private LocalDate leaseEndDate;

	private Integer contractFee;
	private Integer middleFee;

	private LocalDate downPaymentDate;
	private LocalDate interimPaymentDate;
	private Integer balance;
	private LocalDate balancePaymentDate;

	@Column(length = 2)
	private String monthlyRentPaymentDate;

	@Column
	private Integer fixedManagementFee;

	@Column
	private String unfixedManagementFee;

	@Column(length = 20)
	private String monthlyRentAccountBank;

	@Column(length = 20)
	private String monthlyRentAccountNumber;

	@Column
	private Boolean facilitiesRepairStatus;

	@Column
	private String facilitiesRepairContent;

	@Column
	private String landlordBurden;

	@Column
	private String tenantBurden;

	@Column
	private String landlordSignatureUrl1;

	@Column
	private String landlordSignatureUrl2;

	@Column
	private String landlordSignatureUrl3;

	@Column
	private String landlordSignatureUrl4;
}

