package com.bangjwo.portone.application.convert;

import org.springframework.stereotype.Component;

import com.bangjwo.portone.application.dto.PaymentDto;
import com.bangjwo.portone.domain.entity.PaymentStatus;
import com.bangjwo.portone.domain.entity.Payments;

@Component
public class PaymentConverter {
	public static Payments toEntity(PaymentDto.RequestDto dto, String merchantUid) {
		return Payments.builder()
			.impUid(null)
			.merchantUid(merchantUid)
			.memberId(dto.memberId())
			.roomId(dto.roomId())
			.status(PaymentStatus.READY)
			.pdfUrl(null)
			.jsonUrl(null)
			.build();
	}

	public static PaymentDto.ResponseDto toDto(Payments entity) {
		return new PaymentDto.ResponseDto(
			entity.getPaymentId(),
			entity.getImpUid(),
			entity.getMerchantUid(),
			entity.getMemberId(),
			entity.getRoomId(),
			entity.getStatus(),
			entity.getPdfUrl(),
			entity.getJsonUrl(),
			entity.getCreatedAt(),
			entity.getUpdatedAt()
		);
	}
}