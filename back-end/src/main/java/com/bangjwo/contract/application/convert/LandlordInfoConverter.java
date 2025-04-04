package com.bangjwo.contract.application.convert;

import com.bangjwo.contract.application.dto.request.UpdateLandlordInfoDto;
import com.bangjwo.contract.application.dto.response.LandlordInfoResponseDto;
import com.bangjwo.contract.domain.entity.LandlordInfo;

public class LandlordInfoConverter {

	// 임시저장: 전달된 값만 업데이트 (partial update)
	public static void updateDraft(LandlordInfo entity, UpdateLandlordInfoDto dto) {
		if (dto.getName() != null)
			entity.setName(dto.getName());
		if (dto.getPhoneNumber() != null)
			entity.setPhoneNumber(dto.getPhoneNumber());
		if (dto.getAddress() != null)
			entity.setAddress(dto.getAddress());
		if (dto.getResidentRegistrationNumber() != null)
			entity.setResidentRegistrationNumber(dto.getResidentRegistrationNumber());
		if (dto.getRentalPropertyAddress() != null)
			entity.setRentalPropertyAddress(dto.getRentalPropertyAddress());
		if (dto.getPropertyStructure() != null)
			entity.setPropertyStructure(dto.getPropertyStructure());
		if (dto.getPropertyPurpose() != null)
			entity.setPropertyPurpose(dto.getPropertyPurpose());
		if (dto.getPropertyArea() != null)
			entity.setPropertyArea(dto.getPropertyArea());
		if (dto.getRepairResponsibility() != null)
			entity.setRepairResponsibility(dto.getRepairResponsibility());
		if (dto.getPriorityConfirmedDateYn() != null)
			entity.setPriorityConfirmedDateYn(dto.getPriorityConfirmedDateYn());
		if (dto.getTaxArrears() != null)
			entity.setTaxArrears(dto.getTaxArrears());
		if (dto.getLocationOfRentalHousing() != null)
			entity.setLocationOfRentalHousing(dto.getLocationOfRentalHousing());
		if (dto.getRentalHousingLandType() != null)
			entity.setRentalHousingLandType(dto.getRentalHousingLandType());
		if (dto.getRentalHousingLandArea() != null)
			entity.setRentalHousingLandArea(dto.getRentalHousingLandArea());
		if (dto.getRentalHousingUsage() != null)
			entity.setRentalHousingUsage(dto.getRentalHousingUsage());
		if (dto.getRentalHousingArea() != null)
			entity.setRentalHousingArea(dto.getRentalHousingArea());
		if (dto.getRentalPartAddress() != null)
			entity.setRentalPartAddress(dto.getRentalPartAddress());
		if (dto.getRentalPartArea() != null)
			entity.setRentalPartArea(dto.getRentalPartArea());
		if (dto.getContractType() != null)
			entity.setContractType(dto.getContractType());
		if (dto.getLeaseType() != null)
			entity.setLeaseType(dto.getLeaseType());
		if (dto.getDepositAmount() != null)
			entity.setDepositAmount(dto.getDepositAmount());
		if (dto.getMonthlyRent() != null)
			entity.setMonthlyRent(dto.getMonthlyRent());
		if (dto.getLeaseStartDate() != null)
			entity.setLeaseStartDate(dto.getLeaseStartDate());
		if (dto.getLeaseEndDate() != null)
			entity.setLeaseEndDate(dto.getLeaseEndDate());
		if (dto.getContractFee() != null)
			entity.setContractFee(dto.getContractFee());
		if (dto.getMiddleFee() != null)
			entity.setMiddleFee(dto.getMiddleFee());
		if (dto.getDownPaymentDate() != null)
			entity.setDownPaymentDate(dto.getDownPaymentDate());
		if (dto.getInterimPaymentDate() != null)
			entity.setInterimPaymentDate(dto.getInterimPaymentDate());
		if (dto.getBalance() != null)
			entity.setBalance(dto.getBalance());
		if (dto.getBalancePaymentDate() != null)
			entity.setBalancePaymentDate(dto.getBalancePaymentDate());
		if (dto.getMonthlyRentPaymentDate() != null)
			entity.setMonthlyRentPaymentDate(dto.getMonthlyRentPaymentDate());
		if (dto.getFixedManagementFee() != null)
			entity.setFixedManagementFee(dto.getFixedManagementFee());
		if (dto.getUnfixedManagementFee() != null)
			entity.setUnfixedManagementFee(dto.getUnfixedManagementFee());
		if (dto.getMonthlyRentAccountBank() != null)
			entity.setMonthlyRentAccountBank(dto.getMonthlyRentAccountBank());
		if (dto.getMonthlyRentAccountNumber() != null)
			entity.setMonthlyRentAccountNumber(dto.getMonthlyRentAccountNumber());
		if (dto.getFacilitiesRepairStatus() != null)
			entity.setFacilitiesRepairStatus(dto.getFacilitiesRepairStatus());
		if (dto.getFacilitiesRepairContent() != null)
			entity.setFacilitiesRepairContent(dto.getFacilitiesRepairContent());
		if (dto.getLandlordBurden() != null)
			entity.setLandlordBurden(dto.getLandlordBurden());
		if (dto.getTenantBurden() != null)
			entity.setTenantBurden(dto.getTenantBurden());
	}

	public static void updateFinal(LandlordInfo entity, UpdateLandlordInfoDto dto) {
		entity.setName(dto.getName());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setAddress(dto.getAddress());
		entity.setResidentRegistrationNumber(dto.getResidentRegistrationNumber());
		entity.setRentalPropertyAddress(dto.getRentalPropertyAddress());
		entity.setPropertyStructure(dto.getPropertyStructure());
		entity.setPropertyPurpose(dto.getPropertyPurpose());
		entity.setPropertyArea(dto.getPropertyArea());
		entity.setRepairResponsibility(dto.getRepairResponsibility());
		entity.setPriorityConfirmedDateYn(dto.getPriorityConfirmedDateYn());
		entity.setTaxArrears(dto.getTaxArrears());
		entity.setLocationOfRentalHousing(dto.getLocationOfRentalHousing());
		entity.setRentalHousingLandType(dto.getRentalHousingLandType());
		entity.setRentalHousingLandArea(dto.getRentalHousingLandArea());
		entity.setRentalHousingUsage(dto.getRentalHousingUsage());
		entity.setRentalHousingArea(dto.getRentalHousingArea());
		entity.setRentalPartAddress(dto.getRentalPartAddress());
		entity.setRentalPartArea(dto.getRentalPartArea());
		entity.setContractType(dto.getContractType());
		entity.setLeaseType(dto.getLeaseType());
		entity.setDepositAmount(dto.getDepositAmount());
		entity.setMonthlyRent(dto.getMonthlyRent());
		entity.setLeaseStartDate(dto.getLeaseStartDate());
		entity.setLeaseEndDate(dto.getLeaseEndDate());
		entity.setContractFee(dto.getContractFee());
		entity.setMiddleFee(dto.getMiddleFee());
		entity.setDownPaymentDate(dto.getDownPaymentDate());
		entity.setInterimPaymentDate(dto.getInterimPaymentDate());
		entity.setBalance(dto.getBalance());
		entity.setBalancePaymentDate(dto.getBalancePaymentDate());
		entity.setMonthlyRentPaymentDate(dto.getMonthlyRentPaymentDate());
		entity.setFixedManagementFee(dto.getFixedManagementFee());
		entity.setUnfixedManagementFee(dto.getUnfixedManagementFee());
		entity.setMonthlyRentAccountBank(dto.getMonthlyRentAccountBank());
		entity.setMonthlyRentAccountNumber(dto.getMonthlyRentAccountNumber());
		entity.setFacilitiesRepairStatus(dto.getFacilitiesRepairStatus());
		entity.setFacilitiesRepairContent(dto.getFacilitiesRepairContent());
		entity.setLandlordBurden(dto.getLandlordBurden());
		entity.setTenantBurden(dto.getTenantBurden());
	}

	public static LandlordInfoResponseDto toResponseDto(LandlordInfo info) {
		return LandlordInfoResponseDto.builder()
			.name(info.getName())
			.phoneNumber(info.getPhoneNumber())
			.address(info.getAddress())
			.residentRegistrationNumber(info.getResidentRegistrationNumber())
			.rentalPropertyAddress(info.getRentalPropertyAddress())
			.propertyStructure(info.getPropertyStructure())
			.propertyPurpose(info.getPropertyPurpose())
			.propertyArea(info.getPropertyArea())
			.repairResponsibility(info.getRepairResponsibility())
			.priorityConfirmedDateYn(info.getPriorityConfirmedDateYn())
			.taxArrears(info.getTaxArrears())
			.locationOfRentalHousing(info.getLocationOfRentalHousing())
			.rentalHousingLandType(info.getRentalHousingLandType())
			.rentalHousingLandArea(info.getRentalHousingLandArea())
			.rentalHousingUsage(info.getRentalHousingUsage())
			.rentalHousingArea(info.getRentalHousingArea())
			.rentalPartAddress(info.getRentalPartAddress())
			.rentalPartArea(info.getRentalPartArea())
			.contractType(info.getContractType())
			.leaseType(info.getLeaseType())
			.depositAmount(info.getDepositAmount())
			.monthlyRent(info.getMonthlyRent())
			.leaseStartDate(info.getLeaseStartDate())
			.leaseEndDate(info.getLeaseEndDate())
			.contractFee(info.getContractFee())
			.middleFee(info.getMiddleFee())
			.downPaymentDate(info.getDownPaymentDate())
			.interimPaymentDate(info.getInterimPaymentDate())
			.balance(info.getBalance())
			.balancePaymentDate(info.getBalancePaymentDate())
			.monthlyRentPaymentDate(info.getMonthlyRentPaymentDate())
			.fixedManagementFee(info.getFixedManagementFee())
			.unfixedManagementFee(info.getUnfixedManagementFee())
			.monthlyRentAccountBank(info.getMonthlyRentAccountBank())
			.monthlyRentAccountNumber(info.getMonthlyRentAccountNumber())
			.facilitiesRepairStatus(info.getFacilitiesRepairStatus())
			.facilitiesRepairContent(info.getFacilitiesRepairContent())
			.landlordBurden(info.getLandlordBurden())
			.tenantBurden(info.getTenantBurden())
			.landlordSignatureUrl1(info.getLandlordSignatureUrl1())
			.landlordSignatureUrl2(info.getLandlordSignatureUrl2())
			.landlordSignatureUrl3(info.getLandlordSignatureUrl3())
			.landlordSignatureUrl4(info.getLandlordSignatureUrl4())
			.build();
	}

}
