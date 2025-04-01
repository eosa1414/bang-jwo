package com.bangjwo.contract.presentation;

import java.math.BigInteger;

import javax.crypto.SecretKey;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bangjwo.blockchain.application.service.BlockchainService;
import com.bangjwo.common.crypto.AESService;
import com.bangjwo.common.crypto.RSAService;
import com.bangjwo.contract.application.dto.request.CompleteDto;
import com.bangjwo.contract.application.service.PinataStorageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/contract")
@RequiredArgsConstructor
public class ContractController2 {

	private final RSAService rsaService;
	private final AESService aesService;
	private final PinataStorageService pinataStorageService;
	private final BlockchainService blockchainService;

	String encryptedAesKey;    // 임시용 추후 계약 구현시 DB저장

	@PostMapping("/complete")
	public ResponseEntity<?> encryptAndUploadContract(@ModelAttribute CompleteDto completeDto) {
		try {
			// 1. AES 키 생성
			SecretKey aesKey = aesService.generateAESKey();

			// 2. AES 키를 RSA로 암호화
			encryptedAesKey = rsaService.encryptAesKey(aesKey);    // DB에 저장 구현

			// 3. PDF 파일을 AES로 암호화

			MultipartFile file = completeDto.getFile();
			byte[] pdfBytes = file.getBytes();
			byte[] encryptedPdf = aesService.encrypt(aesKey, pdfBytes);
			String cid = pinataStorageService.uploadEncryptedPdf(encryptedPdf,
				completeDto.getContractId());    // DB에 저장 구현

			// 블록체인에 CID 저장 구현
			// 4. 블록체인에 CID 저장 구현
			blockchainService.registerContract(BigInteger.valueOf(completeDto.getContractId()), cid,
				BigInteger.valueOf(completeDto.getLandlord()),
				BigInteger.valueOf(completeDto.getTenant()));

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
			headers.setContentDisposition(ContentDisposition.builder("inline")
				.filename("contract.pdf")
				.build());
			return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("IPFS에서 파일 불러오기 실패: " + e.getMessage());
		}
	}

	// 블록체인과 CID 비교 구현

}
