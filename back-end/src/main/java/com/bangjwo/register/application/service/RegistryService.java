package com.bangjwo.register.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bangjwo.global.common.page.PageResponse;
import com.bangjwo.global.common.page.PaginationRequest;
import com.bangjwo.register.application.convert.RegistryConverter;
import com.bangjwo.register.application.dto.RegistryHyphenDto;
import com.bangjwo.register.application.dto.request.RegistryRequestDto;
import com.bangjwo.register.application.dto.response.RegistryResponseDto;
import com.bangjwo.register.application.dto.response.RegistrySummaryDto;
import com.bangjwo.register.domain.entity.RegistryDocument;
import com.bangjwo.register.domain.repository.RegistryDocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistryService {

	private final HyphenParsingInterface hyphenParsing;
	private final RegistryDocumentRepository registryRepo;

	// S3 폴더 접두어 (JSON만 필요)
	private static final String JSON_FOLDER = "hyphen/";

	/**
	 * S3에서 JSON을 파싱한 후, RegistryRequestDto의 추가 정보를 반영하여
	 * RegistryDocument 엔티티로 변환(RegistryConverter 사용) 후 저장하고,
	 * RegistryResponseDto로 반환합니다.
	 *
	 * @param request 등기부 등록 요청 DTO (결제ID, 회원ID, 매물ID, JSON URL, PDF URL 포함)
	 * @return 변환된 RegistryResponseDto
	 */
	public RegistryResponseDto parseAndSave(RegistryRequestDto request) {
		// S3 JSON URL을 올바른 키로 변환
		String jsonKey = request.getJsonUrl();
		if (!jsonKey.startsWith(JSON_FOLDER)) {
			jsonKey = JSON_FOLDER + jsonKey;
		}
		RegistryHyphenDto dto = hyphenParsing.parseHyphenJson(jsonKey);

		// 변환 로직을 RegistryConverter로 위임
		RegistryDocument doc = RegistryConverter.convertToEntity(dto, request);
		RegistryDocument saved = registryRepo.save(doc);
		return RegistryConverter.convert(saved);
	}

	/**
	 * 주어진 ID로 RegistryDocument를 조회하고, RegistryResponseDto로 반환합니다.
	 *
	 * @param id 등기부 문서 ID
	 * @return RegistryResponseDto (문서가 없으면 null 반환)
	 */
	public RegistryResponseDto findById(String id) {
		RegistryDocument registry = registryRepo.findById(id).orElse(null);
		return RegistryConverter.convert(registry);
	}

	/**
	 * 특정 회원(memberId)의 등기부 등록 정보 요약을 페이지네이션 형태로 조회하여 반환합니다.
	 *
	 * @param memberId 회원 ID
	 * @param page     조회할 페이지 번호 (1부터 시작)
	 * @return PageResponse containing RegistrySummaryDto 리스트
	 */
	public PageResponse<RegistrySummaryDto> getRegistrySummariesByMemberId(Long memberId, Integer page) {
		Pageable pageable = PaginationRequest.toPageable(page);
		Page<RegistryDocument> docs = registryRepo.findByServerDataMemberId(memberId, pageable);
		List<RegistrySummaryDto> dtos = docs.getContent().stream()
			.map(RegistryConverter::convertSummary)
			.collect(Collectors.toList());
		return new PageResponse<>((int)docs.getTotalElements(), docs.getNumber() + 1, docs.getSize(), dtos);
	}
}
