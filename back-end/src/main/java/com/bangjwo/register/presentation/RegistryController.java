package com.bangjwo.register.presentation;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.auth.resolver.MemberHeader;
import com.bangjwo.global.common.page.PageResponse;
import com.bangjwo.register.application.dto.response.AnalysisResultDto;
import com.bangjwo.register.application.dto.response.RegistrySummaryDto;
import com.bangjwo.register.application.service.RegistryFileService;
import com.bangjwo.register.application.service.RegistryService;
import com.bangjwo.register.domain.entity.RegistryDocument;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "registry", description = "등기부 관련 API")
@RestController
@RequestMapping("/api/v1/registry")
@RequiredArgsConstructor
public class RegistryController {

	private final RegistryService registryService;
	private final RegistryFileService registryFileService;

	// @Operation(
	// 	summary = "등기부 등록",
	// 	description = "결제ID, 유저ID, 매물ID, S3 JSON URL, S3 PDF Image URL를 포함한 등기부 등록 정보를 받아 MongoDB에 저장합니다.",
	// 	security = @SecurityRequirement(name = "JWT")
	// )
	// @PostMapping("/hyphen")
	// public ResponseEntity<Void> parseAndStore(
	// 	@Parameter(description = "등기부 등록 요청 DTO", required = true)
	// 	@RequestBody RegistryRequestDto request,
	// 	@MemberHeader Long memberId
	// ) {
	// 	registryService.parseAndSave(request, memberId);
	//
	// 	return ResponseEntity.status(HttpStatus.CREATED).build();
	// }

	@Operation(
		summary = "특정 등기부 문서 조회",
		description = "주어진 등기부 문서 ID로 MongoDB에서 등기부 문서를 조회합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@GetMapping("/{id}")
	public ResponseEntity<RegistryDocument> getRegistryDoc(
		@Parameter(description = "등기부 문서 ID", example = "67e6332b68f0cb1ff92bf31e", required = true)
		@PathVariable String id,
		@MemberHeader Long memberId) {
		RegistryDocument doc = registryService.findById(id, memberId);
		if (doc == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(doc);
	}

	@Operation(
		summary = "결제 ID 기반 위험도 분석",
		description = "결제 ID를 기반으로 결제 내역에 연결된 hyphen JSON을 분석하여 위험도 정보를 반환합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@PostMapping("/risk/payment/{paymentId}")
	public ResponseEntity<AnalysisResultDto> analyzeByPaymentId(
		@PathVariable Long paymentId,
		@MemberHeader Long memberId
	) {
		AnalysisResultDto result = registryService.analyzeByPaymentId(paymentId, memberId);
		return ResponseEntity.ok(result);
	}

	@Operation(
		summary = "회원별 등기부 문서 목록 조회",
		description = "특정 회원의 등기부 등록 정보(요약 정보)를 페이지네이션 형태로 조회합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@GetMapping("/member")
	public ResponseEntity<PageResponse<RegistrySummaryDto>> getRegistriesByUser(
		@MemberHeader Long memberId,
		@Parameter(description = "페이지 번호 (1부터 시작)", example = "1")
		@RequestParam(required = false) Integer page) {
		PageResponse<RegistrySummaryDto> list = registryService.getRegistrySummariesByMemberId(memberId, page);
		return ResponseEntity.ok(list);
	}

	@Operation(
		summary = "등기부 PDF 미리보기",
		description = "결제 ID에 해당하는 등기부 PDF 파일을 반환하며, 브라우저에서 바로 미리보기 형태로 렌더링됩니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@GetMapping("/pdf/{paymentId}")
	public ResponseEntity<byte[]> getRegistryPdf(
		@PathVariable Long paymentId,
		@MemberHeader Long memberId
	) {
		byte[] pdfBytes = registryFileService.getPdfBytesByPayment(paymentId, memberId);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDisposition(ContentDisposition.inline().filename("registry.pdf").build());

		return ResponseEntity.ok().headers(headers).body(pdfBytes);
	}
}
