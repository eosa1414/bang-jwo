package com.bangjwo.portone.application.service;

import java.util.List;

import com.bangjwo.portone.application.dto.PaymentDto;

public interface PaymentService {

	void registerPaymentPrepare(String merchantUid, int amount);

	PaymentDto.ResponseDto prePayment(PaymentDto.RequestDto dto, String merchantUid);

	PaymentDto.ResponseDto getPaymentResult(Long paymentId);

	List<PaymentDto.ResponseDto> getPaymentResults(Long userId);

	Long validateIamport(String impUid);
}
