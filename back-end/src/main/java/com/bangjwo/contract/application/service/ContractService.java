package com.bangjwo.contract.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.contract.application.convert.ContractConverter;
import com.bangjwo.contract.application.convert.ContractDetailConverter;
import com.bangjwo.contract.application.convert.LandlordInfoConverter;
import com.bangjwo.contract.application.convert.SpecialClauseConverter;
import com.bangjwo.contract.application.convert.TenantInfoConverter;
import com.bangjwo.contract.application.dto.request.CreateContractRequestDto;
import com.bangjwo.contract.application.dto.request.LandlordSignatureUpdateRequestDto;
import com.bangjwo.contract.application.dto.request.TenantSignatureUpdateRequestDto;
import com.bangjwo.contract.application.dto.request.UpdateLandlordInfoDto;
import com.bangjwo.contract.application.dto.request.UpdateTenantInfoDto;
import com.bangjwo.contract.application.dto.response.ContractDetailResponseDto;
import com.bangjwo.contract.application.dto.response.ContractStatusResponseDto;
import com.bangjwo.contract.application.dto.response.LandlordInfoResponseDto;
import com.bangjwo.contract.application.dto.response.TenantInfoResponseDto;
import com.bangjwo.contract.domain.entity.Contract;
import com.bangjwo.contract.domain.entity.LandlordInfo;
import com.bangjwo.contract.domain.entity.SpecialClause;
import com.bangjwo.contract.domain.entity.TenantInfo;
import com.bangjwo.contract.domain.repository.ContractRepository;
import com.bangjwo.contract.domain.vo.ContractStatus;
import com.bangjwo.global.common.error.contract.ContractErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.global.common.exception.RoomException;
import com.bangjwo.room.application.service.RoomService;
import com.bangjwo.room.domain.entity.Room;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {
	private final RoomService roomService;
	private final ContractImageService contractImageService;
	private final ContractRepository contractRepository;

	private Contract validateLandlordDraftContract(Long contractId, Long landlordId) {
		Contract contract = findContract(contractId);
		Optional.of(contract.getLandlordId())
			.filter(id -> id.equals(landlordId))
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_LANDLORD_ACCESS));
		Optional.of(contract.getContractStatus())
			.filter(ContractStatus::canLandlordDraft)
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_CONTRACT_STATUS_FOR_LANDLORD_DRAFT));
		return contract;
	}

	private Contract validateLandlordFinalContract(Long contractId, Long landlordId) {
		Contract contract = findContract(contractId);
		Optional.of(contract.getLandlordId())
			.filter(id -> id.equals(landlordId))
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_LANDLORD_ACCESS));
		Optional.of(contract.getContractStatus())
			.filter(ContractStatus::canLandlordFinalUpdate)
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_CONTRACT_STATUS_FOR_LANDLORD_UPDATE));
		return contract;
	}

	private Contract validateTenantDraftContract(Long contractId, Long tenantId) {
		Contract contract = findContract(contractId);
		Optional.of(contract.getTenantId())
			.filter(id -> id.equals(tenantId))
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_TENANT_ACCESS));
		Optional.of(contract.getContractStatus())
			.filter(ContractStatus::canTenantDraft)
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_CONTRACT_STATUS_FOR_TENANT_DRAFT));
		return contract;
	}

	private Contract validateTenantFinalContract(Long contractId, Long tenantId) {
		Contract contract = findContract(contractId);
		Optional.of(contract.getTenantId())
			.filter(id -> id.equals(tenantId))
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_TENANT_ACCESS));
		Optional.of(contract.getContractStatus())
			.filter(ContractStatus::canTenantFinalUpdate)
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_CONTRACT_STATUS_FOR_TENANT_UPDATE));
		return contract;
	}

	public Long createContract(CreateContractRequestDto requestDto) {
		Room room = roomService.findRoom(requestDto.getRoomId());
		// 예시 IPFS key 사용
		String ipfsKey = "SAMPLE_IPFS_KEY";
		Contract contract = ContractConverter.convert(room, requestDto, ipfsKey);
		Contract saved = contractRepository.save(contract);
		return saved.getContractId();
	}

	@Transactional
	public void draftLandlordInfo(UpdateLandlordInfoDto requestDto) {
		Contract contract = validateLandlordDraftContract(requestDto.getContractId(), requestDto.getLandlordId());
		LandlordInfo landlordInfo = contract.getLandlordInfo();
		SpecialClause specialClause = contract.getSpecialClause();
		LandlordInfoConverter.updateDraft(landlordInfo, requestDto);
		SpecialClauseConverter.updateDraft(specialClause, requestDto);
	}

	@Transactional
	public void finalLandlordInfo(UpdateLandlordInfoDto requestDto) {
		Contract contract = validateLandlordFinalContract(requestDto.getContractId(), requestDto.getLandlordId());
		LandlordInfo landlordInfo = contract.getLandlordInfo();
		SpecialClause specialClause = contract.getSpecialClause();
		LandlordInfoConverter.updateFinal(landlordInfo, requestDto);
		SpecialClauseConverter.updateFinal(specialClause, requestDto);
		contract.updateContractStatus(ContractStatus.LANDLORD_COMPLETED);
	}

	@Transactional(readOnly = true)
	public LandlordInfoResponseDto getLandlordInfo(Long contractId, Long landlordId) {
		Contract contract = findContract(contractId);
		Optional.of(contract.getLandlordId())
			.filter(id -> id.equals(landlordId))
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_LANDLORD_ACCESS));
		return LandlordInfoConverter.toResponseDto(contract.getLandlordInfo());
	}

	@Transactional
	public void draftTenantInfo(UpdateTenantInfoDto requestDto) {
		Contract contract = validateTenantDraftContract(requestDto.getContractId(), requestDto.getTenantId());
		TenantInfo tenantInfo = contract.getTenantInfo();
		TenantInfoConverter.updateDraft(tenantInfo, requestDto);
	}

	@Transactional
	public void finalTenantInfo(UpdateTenantInfoDto requestDto) {
		Contract contract = validateTenantFinalContract(requestDto.getContractId(), requestDto.getTenantId());
		TenantInfo tenantInfo = contract.getTenantInfo();
		TenantInfoConverter.updateFinal(tenantInfo, requestDto);
		contract.updateContractStatus(ContractStatus.TENANT_COMPLETED);
	}

	@Transactional(readOnly = true)
	public TenantInfoResponseDto getTenantInfo(Long contractId, Long tenantId) {
		Contract contract = findContract(contractId);
		Optional.of(contract.getTenantId())
			.filter(id -> id.equals(tenantId))
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_TENANT_ACCESS));
		return TenantInfoConverter.toResponseDto(contract.getTenantInfo());
	}

	@Transactional(readOnly = true)
	public ContractStatusResponseDto getContractStatus(Long contractId, Long userId) {
		Contract contract = findContract(contractId);
		validateUserAccess(contract, userId);
		return ContractStatusResponseDto.builder()
			.contractId(contract.getContractId())
			.status(contract.getContractStatus())
			.build();
	}

	private void validateUserAccess(Contract contract, Long userId) {
		Optional.of(contract.getLandlordId())
			.filter(id -> id.equals(userId))
			.or(() -> Optional.of(contract.getTenantId())
				.filter(id -> id.equals(userId)))
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_CONTRACT_ACCESS));
	}

	@Transactional(readOnly = true)
	public ContractDetailResponseDto getContractDetail(Long contractId, Long userId) {
		Contract contract = findContract(contractId);
		validateUserAccess(contract, userId);
		return ContractDetailConverter.toDto(contract);
	}

	@Transactional
	public void updateLandlordSignatures(LandlordSignatureUpdateRequestDto dto) {
		Contract contract = findContract(dto.getContractId());
		Optional.of(contract.getLandlordId())
			.filter(id -> id.equals(dto.getLandlordId()))
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_CONTRACT_ACCESS));
		Optional.of(contract.getContractStatus())
			.filter(status -> status.equals(ContractStatus.TENANT_SIGNED))
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_CONTRACT_STATUS_FOR_LANDLORD_SIGNATURE));
		LandlordInfo landlordInfo = contract.getLandlordInfo();
		contractImageService.updateLandlordSignatures(landlordInfo, dto);
		contract.updateContractStatus(ContractStatus.COMPLETED);
	}

	@Transactional
	public void updateTenantSignature(TenantSignatureUpdateRequestDto dto) {
		Contract contract = findContract(dto.getContractId());
		Optional.of(contract.getTenantId())
			.filter(id -> id.equals(dto.getTenantId()))
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_CONTRACT_ACCESS));
		Optional.of(contract.getContractStatus())
			.filter(status -> status.equals(ContractStatus.TENANT_COMPLETED))
			.orElseThrow(() -> new BusinessException(ContractErrorCode.INVALID_CONTRACT_STATUS_FOR_TENANT_SIGNATURE));
		TenantInfo tenantInfo = contract.getTenantInfo();
		contractImageService.updateTenantSignature(tenantInfo, dto);
		contract.updateContractStatus(ContractStatus.TENANT_SIGNED);
	}

	@Transactional(readOnly = true)
	public Contract findContract(Long contractId) {
		return contractRepository.findByContractId(contractId)
			.orElseThrow(() -> new RoomException(ContractErrorCode.NOT_FOUND_CONTRACT));
	}
}
