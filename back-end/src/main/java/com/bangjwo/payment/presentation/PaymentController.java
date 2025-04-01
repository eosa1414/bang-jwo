package com.bangjwo.payment.presentation;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.payment.application.dto.PaymentDto;
import com.bangjwo.payment.application.service.PaymentService;
import com.bangjwo.payment.domain.entity.Status;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/payment")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Payment", description = "결제 관련 API")
public class PaymentController {

	private final PaymentService paymentService;
	/*
	* 사전정보 저장
	* 프론트에서 받은 정보들을 저장, 프론트에 반환하여 결제 진행
	* */
	@Operation(
		summary = "사전 정보 입력",
		description = "결제 전 사용자의 결제 정보를 저장합니다.",
		responses  = {
			@ApiResponse(responseCode = "200", description = "사전 정보 저장 성공"),
			@ApiResponse(responseCode = "400", description = "잘못된 요청 데이터", content = @Content),
			@ApiResponse(responseCode = "500", description = "서버 오류", content = @Content)
		}
	)
	@PostMapping("/prepayment")
	public ResponseEntity<PaymentDto.ResponseDto> prepayment(
		@RequestBody PaymentDto.RequestDto dto) {

		return ResponseEntity.ok().body(paymentService.savePayment(dto));
	}

	/*
	* 결제 검증
	* 프론트에서 결제 완료를 하고 나서 나온 UUID와
	* 포트원에 결제 단건 조회 API 요청해서 나온 응답값 비교
	* */
	@Operation(
		summary = "결제 검증",
		description = "포트원에 결제 내역을 검증을 요청합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "결제 검증 성공"),
			@ApiResponse(responseCode = "404", description = "해당 결제 내역 없음", content = @Content),
			@ApiResponse(responseCode = "500", description = "포트원 응답 오류", content = @Content)
		}
	)
	@PostMapping("/validation/{impUid}")
	public IamportResponse<Payment> validateIamport(
		@PathVariable String impUid) throws
		IamportResponseException, IOException {
		log.info("impUid: {}", impUid);
		log.info("validateIamport");

		return paymentService.validateIamport(impUid);
	}

	/*
	 * 프론트에서 결제 검증 이후 호출
	 * 결제 결과 저장
	 * */
	@Operation(
		summary = "결제 정보 입력",
		description = "완료된 결제 내역을 저장합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "결제 저장 성공"),
			@ApiResponse(responseCode = "400", description = "요청 데이터 오류", content = @Content),
			@ApiResponse(responseCode = "404", description = "해당 결제 정보 없음", content = @Content)
		}
	)
	@PutMapping("/result/{impUid}/{status}")
	public ResponseEntity<PaymentDto.ResponseDto> savePayment(
		@PathVariable String impUid,
		@PathVariable Status status) {

		return ResponseEntity.ok().body(paymentService.completePayment(impUid, status));
	}

	/*
	 * 결제 내역 상세조회
	 * */
	@Operation(
		summary = "결제 상세 조회",
		description = "결제 상세 내역을 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "결제 조회 성공"),
			@ApiResponse(responseCode = "404", description = "결제 ID에 해당하는 내역 없음", content = @Content)
		}
	)
	@GetMapping("/result/{paymentId}")
	public ResponseEntity<PaymentDto.ResponseDto> getPayment(
		@PathVariable Long paymentId) {

		return ResponseEntity.ok().body(paymentService.getPaymentResult(paymentId));
	}

	/*
	* 결제 목록 조회
	* */
	@Operation(
		summary = "결제 목록 조회",
		description = "결제 목록을 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "결제 목록 조회 성공"),
			@ApiResponse(responseCode = "404", description = "해당 유저의 결제 내역 없음", content = @Content)
		}
	)
	@GetMapping("/results")
	public ResponseEntity<List<PaymentDto.ResponseDto>> getPayments() {
		Long userId = 1L;

		return ResponseEntity.ok().body(paymentService.getPaymentResults(userId));
	}
}
