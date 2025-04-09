package com.bangjwo.portone.application.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.global.common.error.portone.PortoneErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.portone.application.convert.PaymentConverter;
import com.bangjwo.portone.application.dto.PaymentDto;
import com.bangjwo.portone.domain.entity.PaymentStatus;
import com.bangjwo.portone.domain.entity.Payments;
import com.bangjwo.portone.domain.repository.PaymentRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.PrepareData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	@Value("${imp.secret.verificate}")
	private String secret;

	private final PaymentRepository paymentRepository;
	private final IamportClient iamportClient;

	@Override
	@Transactional
	public void registerPaymentPrepare(String merchantUid, int amount) {
		try {
			iamportClient.postPrepare(new PrepareData(merchantUid, BigDecimal.valueOf(amount))).getResponse();

		} catch (IamportResponseException | IOException e) {
			throw new RuntimeException("사전 결제 등록 실패", e);
		}
	}

	@Override
	@Transactional
	public PaymentDto.ResponseDto prePayment(PaymentDto.RequestDto dto, String merchantUid) {
		try {
			Payments result = PaymentConverter.toEntity(dto, merchantUid);

			paymentRepository.save(result);

			return PaymentConverter.toDto(result);
		} catch (Exception e) {
			log.error("사전 결제 정보 저장 실패", e);
			throw new BusinessException(PortoneErrorCode.PREPAYMENT_SAVE_FAILED);
		}
	}

	@Override
	@Transactional
	public IamportResponse<Payment> validateIamport(String impUid) {
		try {
			IamportResponse<Payment> payment = iamportClient.paymentByImpUid(impUid);

			if (payment == null || payment.getResponse() == null) {
				throw new BusinessException(PortoneErrorCode.IMP_UID_NOT_FOUND);
			}

			String merchantUid = payment.getResponse().getMerchantUid();
			Payments result = paymentRepository.findByMerchantUid(merchantUid)
				.orElseThrow(() -> new BusinessException(PortoneErrorCode.IMP_UID_NOT_FOUND));

			if (!"paid".equals(payment.getResponse().getStatus())) {
				result.changeStatus(PaymentStatus.FAILED);
				throw new BusinessException(PortoneErrorCode.PAYMENT_FAILED);
			}

			result.setImpUid(payment.getResponse().getImpUid());
			result.changeStatus(PaymentStatus.PAID);

			// ✅ 더미 데이터 연결 (roomId 기준)
			Long roomId = result.getRoomId();
			String pdfKey = roomId % 2 == 0 ? "registry2.pdf" : "registry.pdf";
			String jsonKey = roomId % 2 == 0 ? "hyphen2.json" : "hyphen.json";

			result.setPdfUrl(pdfKey);
			result.setJsonUrl(jsonKey);

			log.info("결제 요청 응답. 결제 내역 - 주문 번호: {}", payment.getResponse().getMerchantUid());
			return payment;
		} catch (IamportResponseException e) {
			log.error("Iamport 응답 예외", e);
			e.printStackTrace();
			throw new BusinessException(PortoneErrorCode.IMP_PAYMENT_VERIFICATION_FAILED);
		} catch (Exception e) {
			log.error("결제 검증 중 예외", e);
			throw new BusinessException(PortoneErrorCode.PAYMENT_INTERNAL_ERROR);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public PaymentDto.ResponseDto getPaymentResult(Long paymentId) {
		Payments payment = paymentRepository.findById(paymentId)
			.orElseThrow(() -> new BusinessException(PortoneErrorCode.PAYMENT_NOT_FOUND));
		return PaymentConverter.toDto(payment);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PaymentDto.ResponseDto> getPaymentResults(Long userId) {
		List<Payments> list = paymentRepository.findAllByMemberIdOrderByUpdatedAtAsc(userId);
		return list.stream().map(PaymentConverter::toDto).toList();
	}
}
