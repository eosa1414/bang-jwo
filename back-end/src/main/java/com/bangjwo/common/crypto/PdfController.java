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
