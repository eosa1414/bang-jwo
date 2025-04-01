package com.bangjwo.register.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.global.common.page.PageResponse;
import com.bangjwo.register.application.dto.request.RegistryRequestDto;
import com.bangjwo.register.application.dto.response.RegistrySummaryDto;
import com.bangjwo.register.application.service.RegistryService;
import com.bangjwo.register.domain.entity.RegistryDocument;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "registry", description = "등기부 관련 API")
@RestController
@RequestMapping("/api/v1/registry")
@RequiredArgsConstructor
public class RegistryController {

	private final RegistryService registryService;

	@Operation(
		summary = "등기부 등록",
		description = "결제ID, 유저ID, 매물ID, S3 JSON URL, S3 PDF Image URL를 포함한 등기부 등록 정보를 받아 MongoDB에 저장합니다."
	)
	@PostMapping("/hyphen")
	public ResponseEntity<Void> parseAndStore(
		@Parameter(description = "등기부 등록 요청 DTO", required = true)
		@RequestBody RegistryRequestDto request) {
		registryService.parseAndSave(request);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Operation(
		summary = "특정 등기부 문서 조회",
		description = "주어진 등기부 문서 ID로 MongoDB에서 등기부 문서를 조회합니다."
	)
	@GetMapping("/{id}")
	public ResponseEntity<RegistryDocument> getRegistryDoc(
		@Parameter(description = "등기부 문서 ID", example = "67e6332b68f0cb1ff92bf31e", required = true)
		@PathVariable String id) {
		RegistryDocument doc = registryService.findById(id);
		if (doc == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(doc);
	}

	@Operation(
		summary = "회원별 등기부 문서 목록 조회",
		description = "특정 회원의 등기부 등록 정보(요약 정보)를 페이지네이션 형태로 조회합니다."
	)
	@GetMapping("/member/{memberId}")
	public ResponseEntity<PageResponse<RegistrySummaryDto>> getRegistriesByUser(
		@Parameter(description = "회원 ID", example = "1", required = true)
		@PathVariable Long memberId,
		@Parameter(description = "페이지 번호 (1부터 시작)", example = "1")
		@RequestParam(required = false) Integer page) {
		PageResponse<RegistrySummaryDto> list = registryService.getRegistrySummariesByMemberId(memberId, page);
		return ResponseEntity.ok(list);
	}
}
