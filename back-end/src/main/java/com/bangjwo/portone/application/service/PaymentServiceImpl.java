package com.bangjwo.portone.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.global.common.error.portone.PortoneErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.portone.application.convert.PaymentConverter;
import com.bangjwo.portone.application.dto.PaymentDto;
import com.bangjwo.portone.domain.entity.Payments;
import com.bangjwo.portone.domain.entity.PaymentStatus;
import com.bangjwo.portone.domain.repository.PaymentRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
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
	@Transactional
	public PaymentDto.ResponseDto savePayment(PaymentDto.RequestDto dto) {
		try {
			Payments result = PaymentConverter.toEntity(dto);
			paymentRepository.save(result);
			return PaymentConverter.toDto(result);
		} catch (Exception e) {
			log.error("사전 결제 정보 저장 실패", e);
			throw new BusinessException(PortoneErrorCode.PREPAYMENT_SAVE_FAILED);
		}
	}

	@Override
	public IamportResponse<Payment> validateIamport(String impUid) {
		try {
			IamportResponse<Payment> payment = iamportClient.paymentByImpUid(impUid);
			if (payment == null || payment.getResponse() == null) {
				throw new BusinessException(PortoneErrorCode.IMP_UID_NOT_FOUND);
			}
			log.info("결제 요청 응답. 결제 내역 - 주문 번호: {}", payment.getResponse().getMerchantUid());
			return payment;
		} catch (IamportResponseException e) {
			log.error("Iamport 응답 예외", e);
			throw new BusinessException(PortoneErrorCode.IMP_PAYMENT_VERIFICATION_FAILED);
		} catch (Exception e) {
			log.error("결제 검증 중 예외", e);
			throw new BusinessException(PortoneErrorCode.PAYMENT_INTERNAL_ERROR);
		}
	}

	@Override
	@Transactional
	public PaymentDto.ResponseDto completePayment(String impUid, PaymentStatus status) {
		Payments result = paymentRepository.findByImpUid(impUid)
			.orElseThrow(() -> new BusinessException(PortoneErrorCode.PAYMENT_NOT_FOUND));

		// 이미 결제 완료된 건 처리 방지
		if (result.getStatus() == PaymentStatus.PAID) {
			throw new BusinessException(PortoneErrorCode.PAYMENT_ALREADY_COMPLETED);
		}

		result.changeStatus(status);
		return PaymentConverter.toDto(result);
	}

	@Override
	public PaymentDto.ResponseDto getPaymentResult(Long paymentId) {
		Payments payment = paymentRepository.findById(paymentId)
			.orElseThrow(() -> new BusinessException(PortoneErrorCode.PAYMENT_NOT_FOUND));
		return PaymentConverter.toDto(payment);
	}

	@Override
	public List<PaymentDto.ResponseDto> getPaymentResults(Long userId) {
		List<Payments> list = paymentRepository.findAllByMemberIdOrderByUpdatedAtAsc(userId);
		return list.stream().map(PaymentConverter::toDto).toList();
	}
}
