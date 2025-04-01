package com.bangjwo.contract.application.convert;

import java.math.BigDecimal;

import com.bangjwo.contract.application.dto.request.CreateContractRequestDto;
import com.bangjwo.contract.domain.entity.Contract;
import com.bangjwo.contract.domain.entity.LandlordInfo;
import com.bangjwo.contract.domain.entity.SpecialClause;
import com.bangjwo.contract.domain.entity.TenantInfo;
import com.bangjwo.contract.domain.vo.ContractStatus;
import com.bangjwo.global.common.error.contract.ContractErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.vo.RoomStatus;

public class ContractConverter {

	public static Contract convert(Room room, CreateContractRequestDto dto, String ipfsKey) {
		if (!room.getStatus().equals(RoomStatus.ON_SALE)) {
			throw new BusinessException(ContractErrorCode.INVALID_ROOM_STATUS_FOR_CONTRACT);
		}

		if (!room.getMemberId().equals(dto.getLandlordId())) {
			throw new BusinessException(ContractErrorCode.UNAUTHORIZED_LANDLORD_FOR_CONTRACT);
		}

		return Contract.builder()
			.room(room)
			.landlordId(dto.getLandlordId())
			.tenantId(dto.getTenantId())
			.landlordInfo(createLandlordInfo())
			.tenantInfo(createTenantInfo())
			.specialClause(createSpecialClause())
			.ipfsKey(ipfsKey)
			.contractStatus(ContractStatus.BEFORE_WRITE)
			.landlordAuth(false)
			.tenantAuth(false)
			.build();
	}

	private static TenantInfo createTenantInfo() {
		return TenantInfo.builder()
			.build();
	}

	/**
	 * 매물(Room) 정보와 RegistryDocument의 데이터를 사용하여 LandlordInfo 엔티티를 생성합니다.
	 * 의미가 같은 필드는 RegistryDocument의 데이터를 매핑하고, 나머지는 기본값(null, 0 등)으로 둡니다.
	 *
	 * @return 생성된 LandlordInfo 엔티티
	 */
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
			.repairResponsibility(null)
			.priorityConfirmedDateYn(false)
			.taxArrears(false)
			.locationOfRentalHousing(null)
			.rentalHousingLandType(null)
			.rentalHousingLandArea(BigDecimal.ZERO)
			.rentalHousingUsage(null)
			.rentalHousingArea(BigDecimal.ZERO)
			.rentalPartAddress(null)
			.rentalPartArea(BigDecimal.ZERO)
			.contractType(null)
			.leaseType(null)
			.depositAmount(0L)
			.monthlyRent(0L)
			.leaseStartDate(null)
			.leaseEndDate(null)
			.contractFee(0)
			.middleFee(0)
			.downPaymentDate(null)
			.interimPaymentDate(null)
			.balance(0)
			.balancePaymentDate(null)
			.monthlyRentPaymentDate("")
			.fixedManagementFee(0)
			.unfixedManagementFee(null)
			.monthlyRentAccountBank(null)
			.monthlyRentAccountNumber(null)
			.facilitiesRepairStatus(false)
			.facilitiesRepairContent(null)
			.landlordBurden(null)
			.tenantBurden(null)
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
			.build();
	}
}
