package com.bangjwo.payment.application.service;

import java.util.List;

import com.bangjwo.payment.application.dto.PaymentDto;
import com.bangjwo.payment.domain.entity.Status;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

public interface PaymentService {

	PaymentDto.ResponseDto savePayment(PaymentDto.RequestDto dto);

	PaymentDto.ResponseDto completePayment(String impUid, Status status);

	PaymentDto.ResponseDto getPaymentResult(Long paymentId);

	List<PaymentDto.ResponseDto> getPaymentResults(Long userId);

	IamportResponse<Payment> validateIamport(String impUid);
}
