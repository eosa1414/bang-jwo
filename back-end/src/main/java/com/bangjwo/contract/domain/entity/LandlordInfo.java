package com.bangjwo.contract.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bangjwo.global.common.entity.BaseEntity;
import com.bangjwo.room.domain.vo.ContractType;
import com.bangjwo.room.domain.vo.LeaseType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class LandlordInfo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long landlordInfoId;

	@Column(length = 20)
	private String name;

	@Column(length = 20)
	private String phoneNumber;

	@Column(columnDefinition = "TEXT")
	private String address;

	@Column
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

	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private ContractType contractType;

	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private LeaseType leaseType;

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

