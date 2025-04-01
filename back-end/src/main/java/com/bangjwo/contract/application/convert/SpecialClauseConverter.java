package com.bangjwo.contract.application.convert;

import com.bangjwo.contract.application.dto.request.UpdateLandlordInfoDto;
import com.bangjwo.contract.domain.entity.SpecialClause;

public class SpecialClauseConverter {

	public static void updateDraft(SpecialClause specialClause, UpdateLandlordInfoDto dto) {
		if (dto.getMoveInRegistrationDate() != null)
			specialClause.setMoveInRegistrationDate(dto.getMoveInRegistrationDate());
		if (dto.getUnpaidAmount() != null)
			specialClause.setUnpaidAmount(dto.getUnpaidAmount());
		if (dto.getDisputeResolution() != null)
			specialClause.setDisputeResolution(dto.getDisputeResolution());
		if (dto.getIsHousingReconstructionPlanned() != null)
			specialClause.setIsHousingReconstructionPlanned(dto.getIsHousingReconstructionPlanned());
		if (dto.getConstructionPeriod() != null)
			specialClause.setConstructionPeriod(dto.getConstructionPeriod());
		if (dto.getEstimatedConstructionDuration() != null)
			specialClause.setEstimatedConstructionDuration(dto.getEstimatedConstructionDuration());
		if (dto.getIsDetailedAddressConsentGiven() != null)
			specialClause.setIsDetailedAddressConsentGiven(dto.getIsDetailedAddressConsentGiven());
		if (dto.getEtc() != null)
			specialClause.setEtc(dto.getEtc());
	}

	public static void updateFinal(SpecialClause specialClause, UpdateLandlordInfoDto dto) {
		specialClause.setMoveInRegistrationDate(dto.getMoveInRegistrationDate());
		specialClause.setUnpaidAmount(dto.getUnpaidAmount());
		specialClause.setDisputeResolution(dto.getDisputeResolution());
		specialClause.setIsHousingReconstructionPlanned(dto.getIsHousingReconstructionPlanned());
		specialClause.setConstructionPeriod(dto.getConstructionPeriod());
		specialClause.setEstimatedConstructionDuration(dto.getEstimatedConstructionDuration());
		specialClause.setIsDetailedAddressConsentGiven(dto.getIsDetailedAddressConsentGiven());
		specialClause.setEtc(dto.getEtc());
	}
}

