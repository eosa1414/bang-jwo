package com.bangjwo.portone.application.service;

import org.springframework.stereotype.Service;

import com.bangjwo.contract.application.service.ContractService;
import com.bangjwo.contract.domain.repository.ContractRepository;
import com.bangjwo.global.common.error.portone.PaymentErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.portone.application.dto.CertificationDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificationServiceImpl implements CertificationService {

	private final ContractRepository contractRepository;
	private final ContractService contractService;

	@Override
	public CertificationDto certificateContract(Long userId, CertificationDto dto) {
		String name = ""; String birthDate = ""; String phone = "";

		if(!name.equals(dto.name()) || !birthDate.equals(dto.birthDate()) || !phone.equals(dto.phone()))
			throw new BusinessException(PaymentErrorCode.USER_INFO_MISMATCH);

		return dto;
	}
}
