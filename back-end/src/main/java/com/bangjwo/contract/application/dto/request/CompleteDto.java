package com.bangjwo.contract.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CompleteDto {
	@NotNull
	private MultipartFile file;

	@NotNull
	private long contractId;

	@NotNull
	private long landlord;

	@NotNull
	private long tenant;
}
