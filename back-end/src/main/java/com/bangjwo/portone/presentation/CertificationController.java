package com.bangjwo.portone.presentation;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.portone.application.dto.CertificationDto;
import com.bangjwo.portone.application.service.CertificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/certificate")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Certification", description = "인증 관련 API")
public class CertificationController {

	private final CertificationService certificationService;

	@Operation(
		summary = "사용자 검증",
		description = "사용자 검증 이후 계약의 이름, 생년월일, 전화번호를 반환합니다",
		responses = {
			@ApiResponse(responseCode = "200", description = "사용자 인증 성공"),
			@ApiResponse(responseCode = "400", description = "요청 데이터 오류", content = @Content),
			@ApiResponse(responseCode = "404", description = "계약서 또는 사용자 정보 없음", content = @Content)
		}
	)
	@PostMapping("/contract")
	public ResponseEntity<CertificationDto> certificationContract(
		@RequestBody CertificationDto dto) {
		Long userId = 1L;
		return ResponseEntity.ok(certificationService.certificateContract(userId, dto));
	}

}
