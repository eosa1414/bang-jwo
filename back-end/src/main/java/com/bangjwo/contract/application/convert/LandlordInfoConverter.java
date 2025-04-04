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
		if (dto.getRentalHousingLandType() != null)
			entity.setRentalHousingLandType(dto.getRentalHousingLandType());
		if (dto.getRentalHousingLandArea() != null)
			entity.setRentalHousingLandArea(dto.getRentalHousingLandArea());
		if (dto.getContractWrittenDate() != null)
			entity.setContractWrittenDate(dto.getContractWrittenDate());
		if (dto.getPropertyStructure() != null)
			entity.setPropertyStructure(dto.getPropertyStructure());
		if (dto.getPropertyPurpose() != null)
			entity.setPropertyPurpose(dto.getPropertyPurpose());
		if (dto.getPropertyArea() != null)
			entity.setPropertyArea(dto.getPropertyArea());
		if (dto.getPriorityConfirmedDateYn() != null)
			entity.setPriorityConfirmedDateYn(dto.getPriorityConfirmedDateYn());
		if (dto.getTaxArrears() != null)
			entity.setTaxArrears(dto.getTaxArrears());
		if (dto.getRentalPartAddress() != null)
			entity.setRentalPartAddress(dto.getRentalPartAddress());
		if (dto.getRentalPartDetailAddress() != null)
			entity.setRentalPartDetailAddress(dto.getRentalPartDetailAddress());
		if (dto.getRentalPartArea() != null)
			entity.setRentalPartArea(dto.getRentalPartArea());
		if (dto.getContractType() != null)
			entity.setContractType(dto.getContractType());
		if (dto.getPreviousLeaseStartDate() != null)
			entity.setPreviousLeaseStartDate(dto.getPreviousLeaseStartDate());
		if (dto.getPreviousLeaseEndDate() != null)
			entity.setPreviousLeaseEndDate(dto.getPreviousLeaseEndDate());
		if (dto.getPreviousDepositAmount() != null)
			entity.setPreviousDepositAmount(dto.getPreviousDepositAmount());
		if (dto.getPreviousMonthlyRent() != null)
			entity.setPreviousMonthlyRent(dto.getPreviousMonthlyRent());
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
		if (dto.getInterimPaymentDate() != null)
			entity.setInterimPaymentDate(dto.getInterimPaymentDate());
		if (dto.getBalance() != null)
			entity.setBalance(dto.getBalance());
		if (dto.getBalancePaymentDate() != null)
			entity.setBalancePaymentDate(dto.getBalancePaymentDate());
		if (dto.getMonthlyRentPaymentDate() != null)
			entity.setMonthlyRentPaymentDate(dto.getMonthlyRentPaymentDate());
		if (dto.getMonthlyRentType() != null)
			entity.setMonthlyRentType(dto.getMonthlyRentType());
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
		if (dto.getRepairCompletionByBalanceDate() != null)
			entity.setRepairCompletionByBalanceDate(dto.getRepairCompletionByBalanceDate());
		if (dto.getRepairCompletionEtc() != null)
			entity.setRepairCompletionEtc(dto.getRepairCompletionEtc());
		if (dto.getNotRepairedByBalanceDate() != null)
			entity.setNotRepairedByBalanceDate(dto.getNotRepairedByBalanceDate());
		if (dto.getNotRepairedEtc() != null)
			entity.setNotRepairedEtc(dto.getNotRepairedEtc());
		if (dto.getLandlordBurden() != null)
			entity.setLandlordBurden(dto.getLandlordBurden());
		if (dto.getTenantBurden() != null)
			entity.setTenantBurden(dto.getTenantBurden());
	}

	public static void updateFinal(LandlordInfo entity, UpdateLandlordInfoDto dto) {
		updateDraft(entity, dto);
	}

	public static LandlordInfoResponseDto toResponseDto(LandlordInfo info) {
		return LandlordInfoResponseDto.builder()
			.name(info.getName())
			.phoneNumber(info.getPhoneNumber())
			.address(info.getAddress())
			.residentRegistrationNumber(info.getResidentRegistrationNumber())
			.rentalPropertyAddress(info.getRentalPropertyAddress())
			.rentalHousingLandType(info.getRentalHousingLandType())
			.rentalHousingLandArea(info.getRentalHousingLandArea())
			.contractWrittenDate(info.getContractWrittenDate())
			.propertyStructure(info.getPropertyStructure())
			.propertyPurpose(info.getPropertyPurpose())
			.propertyArea(info.getPropertyArea())
			.priorityConfirmedDateYn(info.getPriorityConfirmedDateYn())
			.taxArrears(info.getTaxArrears())
			.rentalPartAddress(info.getRentalPartAddress())
			.rentalPartDetailAddress(info.getRentalPartDetailAddress())
			.rentalPartArea(info.getRentalPartArea())
			.contractType(info.getContractType())
			.previousLeaseStartDate(info.getPreviousLeaseStartDate())
			.previousLeaseEndDate(info.getPreviousLeaseEndDate())
			.previousDepositAmount(info.getPreviousDepositAmount())
			.previousMonthlyRent(info.getPreviousMonthlyRent())
			.leaseType(info.getLeaseType())
			.depositAmount(info.getDepositAmount())
			.monthlyRent(info.getMonthlyRent())
			.leaseStartDate(info.getLeaseStartDate())
			.leaseEndDate(info.getLeaseEndDate())
			.contractFee(info.getContractFee())
			.middleFee(info.getMiddleFee())
			.interimPaymentDate(info.getInterimPaymentDate())
			.balance(info.getBalance())
			.balancePaymentDate(info.getBalancePaymentDate())
			.monthlyRentPaymentDate(info.getMonthlyRentPaymentDate())
			.monthlyRentType(info.getMonthlyRentType())
			.fixedManagementFee(info.getFixedManagementFee())
			.unfixedManagementFee(info.getUnfixedManagementFee())
			.monthlyRentAccountBank(info.getMonthlyRentAccountBank())
			.monthlyRentAccountNumber(info.getMonthlyRentAccountNumber())
			.facilitiesRepairStatus(info.getFacilitiesRepairStatus())
			.facilitiesRepairContent(info.getFacilitiesRepairContent())
			.repairCompletionByBalanceDate(info.getRepairCompletionByBalanceDate())
			.repairCompletionEtc(info.getRepairCompletionEtc())
			.notRepairedByBalanceDate(info.getNotRepairedByBalanceDate())
			.notRepairedEtc(info.getNotRepairedEtc())
			.landlordBurden(info.getLandlordBurden())
			.tenantBurden(info.getTenantBurden())
			.landlordSignatureUrl1(info.getLandlordSignatureUrl1())
			.landlordSignatureUrl2(info.getLandlordSignatureUrl2())
			.landlordSignatureUrl3(info.getLandlordSignatureUrl3())
			.landlordSignatureUrl4(info.getLandlordSignatureUrl4())
			.build();
	}
}
