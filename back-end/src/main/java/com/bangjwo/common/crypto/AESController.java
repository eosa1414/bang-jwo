package com.bangjwo.common.crypto;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/aes")
@RequiredArgsConstructor
public class AESController {
	private final AESService aesService;

	static String encrypted;
	static SecretKey secretKey;
	static byte[] encryptedData;

	@PostMapping("/encrypt/{plainText}")
	public String encrypt(@PathVariable String plainText) throws Exception {
		secretKey = aesService.generateAESKey();
		encryptedData = aesService.encrypt(secretKey, plainText.getBytes(StandardCharsets.UTF_8));
		encrypted = Base64.getEncoder().encodeToString(encryptedData);
		return encrypted;
	}

	@PostMapping("/decrypt")
	public String decrypt() throws Exception {
		byte[] decryptedData = aesService.decrypt(secretKey, encryptedData);
		return new String(decryptedData, StandardCharsets.UTF_8);
	}
}
