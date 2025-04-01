package com.bangjwo.payment.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bangjwo.payment.application.convert.PaymentConverter;
import com.bangjwo.payment.application.dto.PaymentDto;
import com.bangjwo.payment.domain.entity.Payments;
import com.bangjwo.payment.domain.entity.Status;
import com.bangjwo.payment.domain.repository.PaymentRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;
	private final IamportClient iamportClient;

	@Override
	public PaymentDto.ResponseDto savePayment(PaymentDto.RequestDto dto) {

		Payments result = PaymentConverter.toEntity(dto);

		paymentRepository.save(result);

		return PaymentConverter.toDto(result);
	}

	@Override
	public IamportResponse<Payment> validateIamport(String impUid) {
		try {
			IamportResponse<Payment> payment = iamportClient.paymentByImpUid(impUid);
			log.info("결제 요청 응답. 결제 내역 - 주문 번호: {}", payment.getResponse());
			return payment;
		} catch (Exception e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public PaymentDto.ResponseDto completePayment(String impUid, Status status) {

		Payments result = paymentRepository.findByImpUid(impUid)
			.orElseThrow(() -> new IllegalArgumentException("해당 결제 내역이 존재하지 않습니다."));

		result.changeStatus(status, LocalDateTime.now());

		return PaymentConverter.toDto(result);
	}

	@Override
	public PaymentDto.ResponseDto getPaymentResult(Long paymentId) {

		Payments payment = paymentRepository.findById(paymentId)
			.orElseThrow(() -> new IllegalArgumentException("해당 결제 내역이 존재하지 않습니다."));

		return PaymentConverter.toDto(payment);
	}

	@Override
	public List<PaymentDto.ResponseDto> getPaymentResults(Long userId) {

		List<Payments> list = paymentRepository.findAllByMemberIdOrderByUpdatedAtAsc(userId);

		return list.stream().map(PaymentConverter::toDto).toList();
	}
}
