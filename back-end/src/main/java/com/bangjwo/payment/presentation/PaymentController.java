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
import io.swagger.v3.oas.annotations.media.Schema;
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
	* id : 결제 아이디
	* merchantUid
	* userId : 유저 아이디
	* roomId : 매물 아이디
	* status : 결제 상태
	* */

	/*
	* 사전정보 저장
	* 프론트에서 받은 정보들을 저장, 프론트에 반환하여 결제 진행
	* */
	@Operation(
		summary = "사전 정보 입력",
		description = "결제 전 사용자의 결제 정보를 저장합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "계약 생성 성공",
				content = @Content(schema = @Schema(implementation = Long.class))),
			@ApiResponse(responseCode = "400", description = "요청 데이터 오류", content = @Content),
			@ApiResponse(responseCode = "404", description = "해당 방을 찾을 수 없음", content = @Content)
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
	@PutMapping("/result/{impUid}/{status}")
	public ResponseEntity<PaymentDto.ResponseDto> savePayment(
		@PathVariable String impUid,
		@PathVariable Status status) {
		return ResponseEntity.ok().body(paymentService.completePayment(impUid, status));
	}

	/*
	 * 결제 내역 상세조회
	 * */
	@GetMapping("/result/{paymentId}")
	public ResponseEntity<PaymentDto.ResponseDto> getPayment(
		@PathVariable Long paymentId) {
		return ResponseEntity.ok().body(paymentService.getPaymentResult(paymentId));
	}

	/*
	* 결제 목록 조회
	* */
	@GetMapping("/results")
	public ResponseEntity<List<PaymentDto.ResponseDto>> getPayments() {
		Long userId = 1L;
		return ResponseEntity.ok().body(paymentService.getPaymentResults(userId));
	}
}
