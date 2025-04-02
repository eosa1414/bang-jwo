package com.bangjwo.portone.application.service;

import java.util.List;

import com.bangjwo.portone.application.dto.PaymentDto;
import com.bangjwo.portone.domain.entity.PaymentStatus;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

public interface PaymentService {

	PaymentDto.ResponseDto savePayment(PaymentDto.RequestDto dto);

	PaymentDto.ResponseDto completePayment(String impUid, PaymentStatus status);

	PaymentDto.ResponseDto getPaymentResult(Long paymentId);

	List<PaymentDto.ResponseDto> getPaymentResults(Long userId);

	IamportResponse<Payment> validateIamport(String impUid);
}
