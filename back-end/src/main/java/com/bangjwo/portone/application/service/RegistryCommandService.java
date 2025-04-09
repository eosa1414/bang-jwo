package com.bangjwo.portone.application.service;

import org.springframework.stereotype.Service;

import com.bangjwo.register.application.convert.RegistryConverter;
import com.bangjwo.register.application.dto.RegistryHyphenDto;
import com.bangjwo.register.application.dto.request.RegistryRequestDto;
import com.bangjwo.register.application.service.HyphenParsingInterface;
import com.bangjwo.register.domain.entity.RegistryDocument;
import com.bangjwo.register.domain.repository.RegistryDocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistryCommandService {

	private final HyphenParsingInterface hyphenParsing;
	private final RegistryDocumentRepository registryRepo;

	private static final String JSON_FOLDER = "hyphen/";

	public void parseAndSave(RegistryRequestDto request, Long memberId) {
		String jsonKey = request.getJsonUrl();
		if (!jsonKey.startsWith(JSON_FOLDER)) {
			jsonKey = JSON_FOLDER + jsonKey;
		}
		RegistryHyphenDto dto = hyphenParsing.parseHyphenJson(jsonKey);
		RegistryDocument doc = RegistryConverter.convertToEntity(dto, request, memberId);
		registryRepo.save(doc);
	}
}

