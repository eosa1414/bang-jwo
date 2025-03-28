package com.bangjwo.contract.application.service;

import org.springframework.stereotype.Service;

import com.bangjwo.contract.application.convert.ContractConverter;
import com.bangjwo.contract.application.dto.request.CreateContractRequestDto;
import com.bangjwo.contract.domain.entity.Contract;
import com.bangjwo.contract.domain.entity.LandlordInfo;
import com.bangjwo.contract.domain.entity.TenantInfo;
import com.bangjwo.contract.domain.repository.ContractRepository;
import com.bangjwo.contract.domain.repository.LandlordInfoRepository;
import com.bangjwo.contract.domain.repository.TenantInfoRepository;
import com.bangjwo.room.application.service.RoomService;
import com.bangjwo.room.domain.entity.Room;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {
	private final RoomService roomService;
	private final LandlordInfoRepository landlordInfoRepository;
	private final TenantInfoRepository tenantInfoRepository;
	private final ContractRepository contractRepository;

	public Long createContract(CreateContractRequestDto requestDto) {
		Room room = roomService.findRoom(requestDto.getRoomId());
		LandlordInfo landlordInfo = createEmptyLandlordInfo(room, requestDto.getLandlordId());
		landlordInfo = landlordInfoRepository.save(landlordInfo);

		TenantInfo tenantInfo = createEmptyTenantInfo(requestDto.getTenantId());
		tenantInfo = tenantInfoRepository.save(tenantInfo);

		String ipfsKey = "SAMPLE_IPFS_KEY";
		Contract contract = ContractConverter.convert(requestDto.getRoomId(), landlordInfo, tenantInfo, ipfsKey);
		Contract saved = contractRepository.save(contract);

		return saved.getContractId();
	}

	private LandlordInfo createEmptyLandlordInfo(Room room, Long landlordId) {
		return ContractConverter.createLandlordInfo(room, landlordId);
	}

	private TenantInfo createEmptyTenantInfo(Long tenantId) {
		return ContractConverter.createTenantInfo(tenantId);
	}
}
