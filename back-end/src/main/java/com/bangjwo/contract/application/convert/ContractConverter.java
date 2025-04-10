package com.bangjwo.contract.application.convert;

import java.math.BigDecimal;

import com.bangjwo.contract.application.dto.request.CreateContractRequestDto;
import com.bangjwo.contract.application.dto.response.ContractVerificationStatusDto;
import com.bangjwo.contract.domain.entity.Contract;
import com.bangjwo.contract.domain.entity.LandlordInfo;
import com.bangjwo.contract.domain.entity.SpecialClause;
import com.bangjwo.contract.domain.entity.TenantInfo;
import com.bangjwo.contract.domain.vo.ContractStatus;
import com.bangjwo.contract.domain.vo.MonthlyRentType;
import com.bangjwo.global.common.error.contract.ContractErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.vo.RoomStatus;

public class ContractConverter {

	public static Contract convert(Room room, CreateContractRequestDto dto, Long memberId) {
		if (!room.getStatus().equals(RoomStatus.ON_SALE)) {
			throw new BusinessException(ContractErrorCode.INVALID_ROOM_STATUS_FOR_CONTRACT);
		}

		if (!room.getMemberId().equals(memberId)) {
			throw new BusinessException(ContractErrorCode.UNAUTHORIZED_LANDLORD_FOR_CONTRACT);
		}

		String ipfsKey = "SAMPLE_IPFS_KEY";
		String aesKey = "SAMPLE_AES_KEY";

		return Contract.builder()
			.room(room)
			.landlordId(memberId)
			.tenantId(dto.getTenantId())
			.landlordInfo(createLandlordInfo())
			.tenantInfo(createTenantInfo())
			.specialClause(createSpecialClause())
			.ipfsKey(ipfsKey)
			.aesKey(aesKey)
			.contractStatus(ContractStatus.BEFORE_WRITE)
			.landlordAuth(false)
			.tenantAuth(false)
			.build();
	}

	private static TenantInfo createTenantInfo() {
		return TenantInfo.builder().build();
	}

	private static LandlordInfo createLandlordInfo() {
		return LandlordInfo.builder()
			.name(null)
			.phoneNumber(null)
			.address(null)
			.residentRegistrationNumber(null)
			.rentalPropertyAddress(null)
			.propertyStructure(null)
			.propertyPurpose(null)
			.propertyArea(BigDecimal.ZERO)
			.rentalHousingLandType(null)
			.rentalHousingLandArea(BigDecimal.ZERO)
			.rentalPartAddress(null)
			.rentalPartDetailAddress(null)
			.rentalPartArea(BigDecimal.ZERO)
			.contractType(null)
			.previousLeaseStartDate(null)
			.previousLeaseEndDate(null)
			.previousDepositAmount(null)
			.previousMonthlyRent(null)
			.leaseType(null)
			.depositAmount(0L)
			.monthlyRent(0L)
			.leaseStartDate(null)
			.leaseEndDate(null)
			.contractFee(0)
			.middleFee(0)
			.interimPaymentDate(null)
			.balance(0)
			.balancePaymentDate(null)
			.monthlyRentPaymentDate("")
			.monthlyRentType(MonthlyRentType.POSTPAID)
			.fixedManagementFee(0)
			.unfixedManagementFee(null)
			.monthlyRentAccountBank(null)
			.monthlyRentAccountNumber(null)
			.facilitiesRepairStatus(false)
			.facilitiesRepairContent(null)
			.repairCompletionByBalanceDate(null)
			.repairCompletionEtc(null)
			.notRepairedByBalanceDate(null)
			.notRepairedEtc(null)
			.priorityConfirmedDateYn(false)
			.taxArrears(false)
			.landlordBurden(null)
			.tenantBurden(null)
			.contractWrittenDate(null)
			.landlordSignatureUrl1(null)
			.landlordSignatureUrl2(null)
			.landlordSignatureUrl3(null)
			.landlordSignatureUrl4(null)
			.build();
	}

	private static SpecialClause createSpecialClause() {
		return SpecialClause.builder()
			.moveInRegistrationDate(null)
			.unpaidAmount(null)
			.disputeResolution(null)
			.isHousingReconstructionPlanned(null)
			.constructionPeriod(null)
			.estimatedConstructionDuration(null)
			.isDetailedAddressConsentGiven(null)
			.etc(null)
			.build();
	}

	public static ContractVerificationStatusDto convertContractVerify(Contract contract) {
		return ContractVerificationStatusDto.builder()
			.landlordVerified(contract.getLandlordAuth())
			.tenantVerified(contract.getTenantAuth())
			.build();
	}
}