package com.bangjwo.portone.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.portone.application.dto.IdentityDto;
import com.bangjwo.portone.application.service.VerificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/verificate")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Verification", description = "인증 관련 API")
public class VerificationController {

	private final VerificationService verificationService;

	@Operation(
		summary = "사용자 검증",
		description = "포트원에서 받은 identityVerificationId로 정보를 받아옵니다.",
		security = @SecurityRequirement(name = "JWT"),
		responses = {
			@ApiResponse(responseCode = "200", description = "사용자 인증 성공"),
			@ApiResponse(responseCode = "400", description = "요청 데이터 오류", content = @Content),
			@ApiResponse(responseCode = "404", description = "계약서 또는 사용자 정보 없음", content = @Content)
		}
	)
	@PostMapping()
	public ResponseEntity<IdentityDto.IdentityResponse> getVerification(
		@RequestBody IdentityDto.IdentityRequest dto) {

		return ResponseEntity.ok().body(verificationService.getVerification(dto.identityVerificationId()));
	}

	// @Operation(
	// 	summary = "계약서 사용자 검증",
	// 	description = "포트원에서 받은 정보와 실제 회원 간의 정보를 비교합니다.",
	// 	security = @SecurityRequirement(name = "JWT"),
	// 	responses = {
	// 		@ApiResponse(responseCode = "200", description = "사용자 인증 성공"),
	// 		@ApiResponse(responseCode = "400", description = "요청 데이터 오류", content = @Content),
	// 		@ApiResponse(responseCode = "404", description = "계약서 또는 사용자 정보 없음", content = @Content)
	// 	}
	// )
	// @PostMapping("/contract")
	// public ResponseEntity<VerificationDto> certificationContract(
	// 	@MemberHeader Long userId,
	// 	@RequestBody VerificationDto dto) {
	//
	// 	return ResponseEntity.ok(verificationService.vertificateContract(userId, dto));
	// }

}
