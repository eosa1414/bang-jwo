package com.bangjwo.common.crypto;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AESRSAService {
	static public class Res {
		String encryptedAesKey;
		byte[] encryptedPdf;
	}

	private final RSAService rsaService;
	private final AESService aesService;

	/**
	 * PDF 파일을 AES로 암호화하고, AES 키를 RSA로 암호화하여 IPFS에 저장
	 */
	public Res encryptAndStorePdfToIpfs(byte[] pdfBytes) throws Exception {
		// 1. AES 키 생성
		SecretKey aesKey = aesService.generateAESKey();

		// 2. PDF 파일을 AES로 암호화
		// byte[] pdfBytes = Files.readAllBytes(pdfFile.toPath()); // PDF 파일 읽기
		byte[] encryptedPdf = aesService.encrypt(aesKey, pdfBytes);

		// 3. AES 키를 RSA로 암호화
		String encryptedAesKey = rsaService.encryptAesKey(aesKey);

		Res result = new Res();
		result.encryptedAesKey = encryptedAesKey;
		result.encryptedPdf = encryptedPdf;

		return result;
	}

	/**
	 * 암호화된 PDF 파일을 복호화 후 PDF 파일로 저장
	 */
	public byte[] decryptAndSavePdfFromIpfs(Res result) throws Exception {

		String encryptedAesKey = result.encryptedAesKey;
		byte[] encryptedPdf = result.encryptedPdf;

		SecretKey aesKey = rsaService.decryptAesKey(encryptedAesKey);

		return aesService.decrypt(aesKey, encryptedPdf);
	}
}
