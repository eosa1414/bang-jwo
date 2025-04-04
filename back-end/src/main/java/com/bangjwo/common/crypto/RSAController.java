package com.bangjwo.common.crypto;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rsa")
@RequiredArgsConstructor
public class RSAController {
	private final RSAService rsaService;

	static String encrypted;

	@PostMapping("/encrypt/{plainText}")
	public String encrypt(@PathVariable String plainText) {
		encrypted = rsaService.encrypt(plainText);
		return rsaService.encrypt(plainText);
	}

	@PostMapping("/decrypt")
	public String decrypt() {
		return rsaService.decrypt(encrypted);
	}
}
