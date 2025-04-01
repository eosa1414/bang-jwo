package com.bangjwo.contract.application.convert;

import com.bangjwo.contract.application.dto.request.UpdateTenantInfoDto;
import com.bangjwo.contract.application.dto.response.TenantInfoResponseDto;
import com.bangjwo.contract.domain.entity.TenantInfo;
import com.bangjwo.global.common.error.contract.ContractErrorCode;
import com.bangjwo.global.common.exception.BusinessException;

public class TenantInfoConverter {

	public static void updateDraft(TenantInfo entity, UpdateTenantInfoDto dto) {
		if (dto.getName() != null)
			entity.setName(dto.getName());
		if (dto.getPhone() != null)
			entity.setPhone(dto.getPhone());
		if (dto.getAddress() != null)
			entity.setAddress(dto.getAddress());
		if (dto.getResidentRegistrationNumber() != null)
			entity.setResidentRegistrationNumber(dto.getResidentRegistrationNumber());
		if (dto.getMoveInDate() != null)
			entity.setMoveInDate(dto.getMoveInDate());
	}

	public static void updateFinal(TenantInfo entity, UpdateTenantInfoDto dto) {
		try {
			entity.setName(dto.getName());
			entity.setPhone(dto.getPhone());
			entity.setAddress(dto.getAddress());
			entity.setResidentRegistrationNumber(dto.getResidentRegistrationNumber());
			entity.setMoveInDate(dto.getMoveInDate());
		} catch (NullPointerException e) {
			throw new BusinessException(ContractErrorCode.INVALID_TENANT_INFO_FOR_FINAL_SAVE);
		}
	}

	public static TenantInfoResponseDto toResponseDto(TenantInfo info) {
		return TenantInfoResponseDto.builder()
			.name(info.getName())
			.phone(info.getPhone())
			.address(info.getAddress())
			.residentRegistrationNumber(info.getResidentRegistrationNumber())
			.moveInDate(info.getMoveInDate())
			.tenantSignatureUrl(info.getTenantSignatureUrl())
			.build();
	}
}
