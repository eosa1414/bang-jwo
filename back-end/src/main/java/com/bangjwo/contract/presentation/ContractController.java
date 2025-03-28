package com.bangjwo.contract.presentation;

import javax.crypto.SecretKey;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bangjwo.common.crypto.AESService;
import com.bangjwo.common.crypto.RSAService;
import com.bangjwo.contract.application.service.PinataStorageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {

	private final RSAService rsaService;
	private final AESService aesService;
	private final PinataStorageService pinataStorageService;

	String encryptedAesKey;    // 임시용 추후 계약 구현시 DB저장

	@PostMapping("/encrypt-upload")
	public ResponseEntity<?> encryptAndUploadContract(@RequestParam("file") MultipartFile file, long id) {
		try {
			// 1. AES 키 생성
			SecretKey aesKey = aesService.generateAESKey();

			// 2. AES 키를 RSA로 암호화
			encryptedAesKey = rsaService.encryptAesKey(aesKey);    // DB에 저장 구현

			// 3. PDF 파일을 AES로 암호화
			byte[] pdfBytes = file.getBytes();
			byte[] encryptedPdf = aesService.encrypt(aesKey, pdfBytes);
			String cid = pinataStorageService.uploadEncryptedPdf(encryptedPdf, id);    // DB에 저장 구현

			// 블록체인에 CID 저장 구현
			// 4. 블록체인에 CID 저장 구현

			return ResponseEntity.ok(cid);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("업로드 실패: " + e.getMessage());
		}
	}

	@GetMapping("/decrypt-download")
	public ResponseEntity<?> decryptAndDownloadContract(@RequestParam("cid") String cid) {
		try {
			byte[] encryptedFileData = pinataStorageService.getFileFromIpfs(cid);
			SecretKey aesKey = rsaService.decryptAesKey(encryptedAesKey);    // db에서 가져와야 함
			byte[] fileData = aesService.decrypt(aesKey, encryptedFileData);
			HttpHeaders headers = new HttpHeaders();
			// 파일 형식 PDF
			headers.setContentType(MediaType.APPLICATION_PDF);
			// 다운로드 형식으로 응답 (파일 이름은 필요에 따라 설정)
			// 필요하면 응답 형식 변경
			headers.setContentDisposition(ContentDisposition.builder("attachment")
				.filename("downloaded_encrypted_contract.pdf")
				.build());
			return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("IPFS에서 파일 불러오기 실패: " + e.getMessage());
		}
	}

	// 블록체인과 CID 비교 구현

}
