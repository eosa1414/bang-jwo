package com.bangjwo.register.application.service;

import com.bangjwo.register.application.dto.RegistryHyphenDto;

public interface HyphenParsingInterface {
	/**
	 * S3 JSON (하이픈) -> RegistryHyphenDto
	 */
	RegistryHyphenDto parseHyphenJson(String s3Key);
}
