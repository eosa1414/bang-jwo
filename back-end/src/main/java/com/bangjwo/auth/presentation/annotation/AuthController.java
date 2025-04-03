package com.bangjwo.auth.presentation.annotation;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.auth.application.dto.KakaoAuthResponseDto;
import com.bangjwo.auth.application.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "소셜 로그인 API")
public class AuthController {

	private final AuthService authService;

	@Operation(
		summary = "카카오 로그인",
		description = "프론트에서 받은 Kakao OAuth 인가 코드를 통해 백엔드에서 토큰 요청 및 사용자 정보를 반환합니다.",
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			description = "Kakao 인가 코드",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(example = "{ \"code\": \"kakao_authorization_code_here\" }")
			)
		)
	)
	@PostMapping("/login")
	public ResponseEntity<KakaoAuthResponseDto> login(@RequestBody Map<String, String> body) {
		String authCode = body.get("code");
		KakaoAuthResponseDto response = authService.loginWithKakao(authCode);

		return ResponseEntity.ok(response);
	}
}
