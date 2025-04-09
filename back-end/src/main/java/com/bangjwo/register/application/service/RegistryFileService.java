package com.bangjwo.register.application.service;

import org.springframework.stereotype.Service;

import com.bangjwo.global.common.error.portone.PortoneErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.global.common.image.S3FileUploaderAdapter;
import com.bangjwo.portone.application.dto.PaymentDto;
import com.bangjwo.portone.application.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistryFileService {

	private final PaymentService paymentService;
	private final S3FileUploaderAdapter s3FileUploaderAdapter;

	public byte[] getPdfBytesByPayment(Long paymentId, Long memberId) {
		PaymentDto.ResponseDto payment = paymentService.getPaymentResult(paymentId);

		if (!payment.memberId().equals(memberId)) {
			throw new BusinessException(PortoneErrorCode.UNAUTHORIZED_USER_ACCESS);
		}

		String pdfPath = payment.pdfUrl();
		if (!pdfPath.startsWith("registry/")) {
			pdfPath = "pdf-img/" + pdfPath;
		}

		return s3FileUploaderAdapter.download(pdfPath);
	}
}

