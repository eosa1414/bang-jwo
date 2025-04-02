package com.bangjwo.auth.presentation;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.auth.application.dto.KakaoAuthResponseDto;
import com.bangjwo.auth.application.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<KakaoAuthResponseDto> login(@RequestBody Map<String, String> body) {
		String authCode = body.get("authCode");
		KakaoAuthResponseDto response = authService.loginWithKakao(authCode);

		return ResponseEntity.ok(response);
	}
}
