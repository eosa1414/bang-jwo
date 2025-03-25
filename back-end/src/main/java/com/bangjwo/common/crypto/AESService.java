package com.bangjwo.common.crypto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.stereotype.Service;

@Service
public class AESService {
	/**
	 * AES 키 생성
	 */
	public SecretKey generateAESKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(256);
		return keyGenerator.generateKey();
	}

	/**
	 * AES 암호화
	 */
	public byte[] encrypt(SecretKey aesKey, byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);

		cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivSpec);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(iv);

		byte[] encryptedData = cipher.doFinal(data);
		baos.write(encryptedData);

		return baos.toByteArray();
	}

	/**
	 * AES 복호화
	 */
	public byte[] decrypt(SecretKey aesKey, byte[] encryptedData) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ByteArrayInputStream bais = new ByteArrayInputStream(encryptedData);

		byte[] iv = new byte[16];
		int bytesRead = bais.read(iv);
		if (bytesRead != 16) {
			throw new IOException("암호화된 데이터에서 IV를 제대로 읽지 못했습니다.");
		}
		IvParameterSpec ivSpec = new IvParameterSpec(iv);

		cipher.init(Cipher.DECRYPT_MODE, aesKey, ivSpec);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buffer = new byte[4096];
		while ((bytesRead = bais.read(buffer)) != -1) {
			baos.write(cipher.update(buffer, 0, bytesRead));
		}

		baos.write(cipher.doFinal());
		return baos.toByteArray();
	}
}
