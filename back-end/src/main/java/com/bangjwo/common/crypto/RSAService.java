package com.bangjwo.common.crypto;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.stereotype.Service;

@Service
public class RSAService {
	private final PublicKey publicKey;
	private final PrivateKey privateKey;

	public RSAService() throws Exception {
		this.publicKey = getPublicKeyFromBase64();
		this.privateKey = getPrivateKeyFromBase64();
	}

	/**
	 * RSA 암호화
	 */
	public String encrypt(String plainText) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
			throw new RuntimeException("암호화 실패", e);
		}
	}

	/**
	 * RSA 복호화
	 */
	public String decrypt(String encryptedText) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
			return new String(decryptedBytes, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException("복호화 실패", e);
		}
	}

	public static PublicKey getPublicKeyFromBase64() throws Exception {
		String base64PublicKey = System.getenv("RSA_PUBLIC_KEY");
		byte[] decodedKey = Base64.getDecoder().decode(base64PublicKey);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(spec);
	}

	public static PrivateKey getPrivateKeyFromBase64() throws Exception {
		String base64PrivateKey = System.getenv("RSA_PRIVATE_KEY");
		byte[] decodedKey = Base64.getDecoder().decode(base64PrivateKey);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(spec);
	}
}
