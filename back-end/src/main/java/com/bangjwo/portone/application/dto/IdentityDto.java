package com.bangjwo.portone.application.dto;

public record IdentityDto() {
	public record IdentityRequest(
		String identityVerificationId
	){
	}

	public record IdentityResponse(
		String name,
		String birthdate,
		String phone
	) {
	}
}
