package com.bangjwo.contract.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bangjwo.contract.domain.vo.MonthlyRentType;
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
	private String residentRegistrationNumber;

	@Column(columnDefinition = "TEXT")
	private String rentalPropertyAddress;

	@Column
	private String propertyStructure;

	@Column
	private String propertyPurpose;

	@Column(precision = 10, scale = 2)
	private BigDecimal propertyArea;

	@Column(length = 50)
	private String rentalPartAddress;

	@Column(length = 50)
	private String rentalPartDetailAddress;

	@Column
	private String rentalHousingLandType;

	@Column(precision = 10, scale = 2)
	private BigDecimal rentalHousingLandArea;

	@Column(precision = 10, scale = 2)
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

	@Column
	private Integer contractFee;

	@Column
	private Integer middleFee;

	@Column
	private LocalDate interimPaymentDate;

	@Column
	private Integer balance;

	@Column
	private LocalDate balancePaymentDate;

	@Column(length = 2)
	private String monthlyRentPaymentDate;

	@Column
	@Enumerated(EnumType.STRING)
	private MonthlyRentType monthlyRentType;

	@Column
	private Integer fixedManagementFee;

	@Column(columnDefinition = "TEXT")
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
	private LocalDate repairCompletionByBalanceDate;

	@Column
	private String repairCompletionEtc;

	@Column
	private LocalDate notRepairedByBalanceDate;

	@Column
	private String notRepairedEtc;

	@Column
	private Boolean priorityConfirmedDateYn;

	@Column
	private Boolean taxArrears;

	@Column
	private String landlordBurden;

	@Column
	private String tenantBurden;

	@Column
	private LocalDate previousLeaseStartDate;

	@Column
	private LocalDate previousLeaseEndDate;

	@Column
	private Long previousDepositAmount;

	@Column
	private Long previousMonthlyRent;

	@Column
	private LocalDate contractWrittenDate;

	@Column(name = "landlord_signature_url_1")
	private String landlordSignatureUrl1;

	@Column(name = "landlord_signature_url_2")
	private String landlordSignatureUrl2;

	@Column(name = "landlord_signature_url_3")
	private String landlordSignatureUrl3;

	@Column(name = "landlord_signature_url_4")
	private String landlordSignatureUrl4;
}
