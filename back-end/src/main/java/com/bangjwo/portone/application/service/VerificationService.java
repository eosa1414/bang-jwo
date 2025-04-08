package com.bangjwo.portone.application.service;

import com.bangjwo.portone.application.dto.IdentityDto;

public interface VerificationService {

	IdentityDto.IdentityResponse getVerification(String identityVerificationId);
}
