package com.bangjwo.common.crypto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

	AESRSAService.Res res;

	private final AESRSAService aesRsaService;

	public PdfController(AESRSAService aesRsaService) {
		this.aesRsaService = aesRsaService;
	}

	/**
	 * PDF 파일을 암호화하고 IPFS에 저장
	 * @param file PDF 파일
	 * @return 성공 메시지
	 */
	@PostMapping("/encrypt")
	public ResponseEntity<String> encryptPdf(@RequestParam("file") MultipartFile file) throws Exception {
		// 파일을 메모리에서 바로 처리
		byte[] pdfBytes = file.getBytes();

		// PDF 파일을 암호화하고 IPFS에 저장
		res = aesRsaService.encryptAndStorePdfToIpfs(pdfBytes);

		return ResponseEntity.status(HttpStatus.OK).body("PDF file encrypted and stored successfully.");
	}

	@PostMapping("/decrypt")
	public ResponseEntity<byte[]> decryptPdf() throws
		Exception {
		byte[] decryptedPdf = aesRsaService.decryptAndSavePdfFromIpfs(res);

		return ResponseEntity.status(HttpStatus.OK)
			.header("Content-Disposition", "attachment; filename=decrypted.pdf")
			.header("Content-Type", "application/pdf")
			.body(decryptedPdf);
	}
}

// package com.bangjwo.common.crypto;
//
// import java.io.File;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;
//
// @RestController
// @RequestMapping("/pdf")
// @CrossOrigin(origins = "http://127.0.0.1:5500") // 클라이언트 주소 허용
// public class PDFController {
//
// 	@Autowired
// 	private AESRSAService aesrsaService;
//
//
// 	@PostMapping("/encrypt")
// 	public ResponseEntity<byte[]> encryptPDF(@RequestParam("file") MultipartFile file) {
// 		try {
// 			// 임시 파일 생성
// 			File inputFile = File.createTempFile("input", ".pdf");
// 			file.transferTo(inputFile);
// 			File encryptedFile = new File(inputFile.getParent(), "encrypted.pdf");
// 			File keyFile = new File(inputFile.getParent(), "aes_key.enc");
//
// 			// 암호화 수행
// 			aesrsaService.encryptPDF(inputFile, encryptedFile, keyFile);
//
// 			// 암호화된 키 파일을 바이트 배열로 읽어오기
// 			byte[] keyFileBytes = java.nio.file.Files.readAllBytes(keyFile.toPath());
//
// 			// 파일 다운로드 응답 반환
// 			return ResponseEntity.ok()
// 				.header("Content-Disposition", "attachment; filename=\"" + keyFile.getName() + "\"")
// 				.body(keyFileBytes);
// 		} catch (Exception e) {
// 			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
// 				.body(("파일 암호화 실패: " + e.getMessage()).getBytes());
// 		}
// 	}
//
// 	@PostMapping("/decrypt")
// 	public ResponseEntity<String> decryptPDF(@RequestParam("encryptedFile") MultipartFile encryptedFile,
// 		@RequestParam("keyFile") MultipartFile keyFile) {
// 		try {
// 			File encFile = File.createTempFile("encrypted", ".pdf");
// 			encryptedFile.transferTo(encFile);
// 			File keyEncFile = File.createTempFile("aes_key", ".enc");
// 			keyFile.transferTo(keyEncFile);
// 			File decryptedFile = new File(encFile.getParent(), "decrypted.pdf");
//
// 			aesrsaService.decryptPDF(encFile, keyEncFile, decryptedFile);
//
// 			return ResponseEntity.ok("파일이 복호화됨: " + decryptedFile.getAbsolutePath());
// 		} catch (Exception e) {
// 			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
// 				.body("파일 복호화 실패: " + e.getMessage());
// 		}
// 	}
// }
