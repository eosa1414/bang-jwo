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

	// }**
	//  * PDF 파일을 AES로 암호화하고, AES 키를 RSA로 암호화하여 IPFS에 저장
	//  */
	// public byte[] encryptAndStorePdfToIpfs(File pdfFile) throws Exception {
	// 	// 1. AES 키 생성
	// 	SecretKey aesKey = aesService.generateAESKey();
	//
	// 	// 2. PDF 파일을 AES로 암호화
	// 	byte[] pdfBytes = Files.readAllBytes(pdfFile.toPath()); // PDF 파일 읽기
	// 	byte[] encryptedPdf = aesService.encrypt(aesKey, pdfBytes);
	//
	// 	// 3. AES 키를 RSA로 암호화
	// 	String encryptedAesKey = rsaService.encryptAesKey(aesKey);
	//
	// 	// 4. 암호화된 AES 키와 PDF를 하나로 합침
	// 	byte[] encryptedPdfPackage = createEncryptedPdfPackage(encryptedAesKey, encryptedPdf);
	//
	// 	// 5. 암호화된 PDF를 IPFS에 저장하고 CID 반환
	// 	return ipfsService.storeFileOnIpfs(encryptedPdfPackage);
	// 	return encryptedPdf;
	// }
	//
	// /**
	//  * 암호화된 PDF 파일을 IPFS에서 불러와 복호화 후 PDF 파일로 저장
	//  */
	// public void decryptAndSavePdfFromIpfs(String cid, String outputFilePath) throws Exception {
	// 	// 1. IPFS에서 암호화된 파일 가져오기
	// 	byte[] encryptedData = ipfsService.getFileFromIpfs(cid);
	//
	// 	// 2. 암호화된 AES 키 추출
	// 	byte[] encryptedAesKey = extractEncryptedAesKey(encryptedData);
	//
	// 	// 3. RSA로 AES 키 복호화
	// 	SecretKey aesKey = rsaService.decryptAesKey(new String(encryptedAesKey));
	//
	// 	// 4. 암호화된 PDF 파일 추출
	// 	byte[] encryptedPdf = extractEncryptedPdf(encryptedData, encryptedAesKey.length);
	//
	// 	// 5. AES로 복호화된 PDF 파일 반환
	// 	byte[] decryptedPdf = aesService.decrypt(aesKey, encryptedPdf);
	//
	// 	// 6. PDF 파일 저장
	// 	Files.write(new File(outputFilePath).toPath(), decryptedPdf);
	// }

}

// package com.bangjwo.common.crypto;
//
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.security.NoSuchAlgorithmException;
// import java.security.PrivateKey;
// import java.security.PublicKey;
// import java.security.SecureRandom;
//
// import javax.crypto.Cipher;
// import javax.crypto.KeyGenerator;
// import javax.crypto.SecretKey;
// import javax.crypto.spec.IvParameterSpec;
// import javax.crypto.spec.SecretKeySpec;
//
// import org.springframework.stereotype.Service;
//
// @Service
// public class AESRSAService {
// 	private final PublicKey rsaPublicKey;
// 	private final PrivateKey rsaPrivateKey;
//
// 	public AESRSAService(RSAService rsaService) throws Exception {
// 		this.rsaPublicKey = RSAService.getPublicKeyFromBase64();
// 		this.rsaPrivateKey = RSAService.getPrivateKeyFromBase64();
// 	}
//
// 	/**
// 	 * AES 키 생성
// 	 */
// 	public SecretKey generateAESKey() throws NoSuchAlgorithmException {
// 		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
// 		keyGenerator.init(256);
// 		return keyGenerator.generateKey();
// 	}
//
// 	/**
// 	 * AES 키를 RSA 공개키로 암호화
// 	 */
// 	public byte[] encryptAESKey(SecretKey aesKey) throws Exception {
// 		Cipher cipher = Cipher.getInstance("RSA");
// 		cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
// 		return cipher.doFinal(aesKey.getEncoded());
// 	}
//
// 	/**
// 	 * AES 키를 RSA 개인키로 복호화
// 	 */
// 	public SecretKey decryptAESKey(byte[] encryptedAESKey) throws Exception {
// 		Cipher cipher = Cipher.getInstance("RSA");
// 		cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
// 		byte[] decryptedKey = cipher.doFinal(encryptedAESKey);
// 		return new SecretKeySpec(decryptedKey, "AES");
// 	}
//
// 	/**
// 	 * PDF 파일을 AES로 암호화하고 AES 키를 RSA로 암호화하여 저장
// 	 */
// 	public void encryptPDF(File inputFile, File outputFile, File keyFile) throws Exception {
// 		SecretKey aesKey = generateAESKey();
// 		byte[] encryptedAESKey = encryptAESKey(aesKey);
//
// 		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
// 		byte[] iv = new byte[16];
// 		new SecureRandom().nextBytes(iv);
// 		IvParameterSpec ivSpec = new IvParameterSpec(iv);
//
// 		cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivSpec);
//
// 		try (FileOutputStream fosKey = new FileOutputStream(keyFile);
// 			 FileInputStream fis = new FileInputStream(inputFile);
// 			 FileOutputStream fos = new FileOutputStream(outputFile)) {
//
// 			// AES 키 암호화된 값 저장
// 			fosKey.write(encryptedAESKey);
//
// 			// IV 저장
// 			fos.write(iv);
//
// 			byte[] buffer = new byte[4096];
// 			int bytesRead;
// 			while ((bytesRead = fis.read(buffer)) != -1) {
// 				byte[] encrypted = cipher.update(buffer, 0, bytesRead);
// 				if (encrypted != null)
// 					fos.write(encrypted);
// 			}
// 			byte[] finalBytes = cipher.doFinal();
// 			if (finalBytes != null)
// 				fos.write(finalBytes);
// 		}
// 	}
//
// 	/**
// 	 * 암호화된 PDF 복호화
// 	 */
// 	public void decryptPDF(File encryptedFile, File keyFile, File outputFile) throws Exception {
// 		byte[] encryptedAESKey = new byte[256];
// 		try (FileInputStream fisKey = new FileInputStream(keyFile)) {
// 			fisKey.read(encryptedAESKey);
// 		}
//
// 		SecretKey aesKey = decryptAESKey(encryptedAESKey);
//
// 		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
// 		try (FileInputStream fis = new FileInputStream(encryptedFile);
// 			 FileOutputStream fos = new FileOutputStream(outputFile)) {
//
// 			byte[] iv = new byte[16];
// 			fis.read(iv);
// 			IvParameterSpec ivSpec = new IvParameterSpec(iv);
//
// 			cipher.init(Cipher.DECRYPT_MODE, aesKey, ivSpec);
//
// 			byte[] buffer = new byte[4096];
// 			int bytesRead;
// 			while ((bytesRead = fis.read(buffer)) != -1) {
// 				byte[] decrypted = cipher.update(buffer, 0, bytesRead);
// 				if (decrypted != null)
// 					fos.write(decrypted);
// 			}
// 			byte[] finalBytes = cipher.doFinal();
// 			if (finalBytes != null)
// 				fos.write(finalBytes);
// 		}
// 	}
// }
