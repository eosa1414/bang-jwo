package com.bangjwo.portone.application.service;

import com.bangjwo.portone.application.dto.CertificationDto;

public interface CertificationService {

	CertificationDto certificateContract(Long userId, CertificationDto dto);
}
