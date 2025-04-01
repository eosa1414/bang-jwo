package com.bangjwo.contract.application.convert;

import com.bangjwo.contract.application.dto.response.ContractDetailResponseDto;
import com.bangjwo.contract.domain.entity.Contract;
import com.bangjwo.contract.domain.entity.LandlordInfo;
import com.bangjwo.contract.domain.entity.SpecialClause;
import com.bangjwo.contract.domain.entity.TenantInfo;

public class ContractDetailConverter {

	public static ContractDetailResponseDto toDto(Contract contract) {
		LandlordInfo l = contract.getLandlordInfo();
		TenantInfo t = contract.getTenantInfo();
		SpecialClause sc = contract.getSpecialClause();

		return ContractDetailResponseDto.builder()
			.contractId(contract.getContractId())
			.contractStatus(contract.getContractStatus())
			.landlordAuth(contract.getLandlordAuth())
			.tenantAuth(contract.getTenantAuth())
			.createdAt(contract.getCreatedAt())

			.landlordName(l.getName())
			.landlordPhone(l.getPhoneNumber())
			.landlordAddress(l.getAddress())
			.residentRegistrationNumber(l.getResidentRegistrationNumber())
			.rentalPropertyAddress(l.getRentalPropertyAddress())
			.propertyStructure(l.getPropertyStructure())
			.propertyPurpose(l.getPropertyPurpose())
			.propertyArea(l.getPropertyArea())
			.repairResponsibility(l.getRepairResponsibility())
			.priorityConfirmedDateYn(l.getPriorityConfirmedDateYn())
			.taxArrears(l.getTaxArrears())
			.locationOfRentalHousing(l.getLocationOfRentalHousing())
			.rentalHousingLandType(l.getRentalHousingLandType())
			.rentalHousingLandArea(l.getRentalHousingLandArea())
			.rentalHousingUsage(l.getRentalHousingUsage())
			.rentalHousingArea(l.getRentalHousingArea())
			.rentalPartAddress(l.getRentalPartAddress())
			.rentalPartArea(l.getRentalPartArea())
			.contractType(l.getContractType())
			.leaseType(l.getLeaseType())
			.depositAmount(l.getDepositAmount())
			.monthlyRent(l.getMonthlyRent())
			.leaseStartDate(l.getLeaseStartDate())
			.leaseEndDate(l.getLeaseEndDate())
			.contractFee(l.getContractFee())
			.middleFee(l.getMiddleFee())
			.downPaymentDate(l.getDownPaymentDate())
			.interimPaymentDate(l.getInterimPaymentDate())
			.balance(l.getBalance())
			.balancePaymentDate(l.getBalancePaymentDate())
			.monthlyRentPaymentDate(l.getMonthlyRentPaymentDate())
			.fixedManagementFee(l.getFixedManagementFee())
			.unfixedManagementFee(l.getUnfixedManagementFee())
			.monthlyRentAccountBank(l.getMonthlyRentAccountBank())
			.monthlyRentAccountNumber(l.getMonthlyRentAccountNumber())
			.facilitiesRepairStatus(l.getFacilitiesRepairStatus())
			.facilitiesRepairContent(l.getFacilitiesRepairContent())
			.landlordBurden(l.getLandlordBurden())
			.tenantBurden(l.getTenantBurden())
			.landlordSignatureUrl1(l.getLandlordSignatureUrl1())
			.landlordSignatureUrl2(l.getLandlordSignatureUrl2())
			.landlordSignatureUrl3(l.getLandlordSignatureUrl3())
			.landlordSignatureUrl4(l.getLandlordSignatureUrl4())

			.moveInRegistrationDate(sc.getMoveInRegistrationDate())
			.unpaidAmount(sc.getUnpaidAmount())
			.disputeResolution(sc.getDisputeResolution())
			.isHousingReconstructionPlanned(sc.getIsHousingReconstructionPlanned())
			.constructionPeriod(sc.getConstructionPeriod())
			.estimatedConstructionDuration(sc.getEstimatedConstructionDuration())
			.isDetailedAddressConsentGiven(sc.getIsDetailedAddressConsentGiven())
			.etc(sc.getEtc())

			.tenantName(t.getName())
			.tenantPhone(t.getPhone())
			.tenantAddress(t.getAddress())
			.tenantResidentRegistrationNumber(t.getResidentRegistrationNumber())
			.moveInDate(t.getMoveInDate())
			.tenantSignatureUrl(t.getTenantSignatureUrl())
			.build();
	}
}
