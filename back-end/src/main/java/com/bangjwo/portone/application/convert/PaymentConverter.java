package com.bangjwo.portone.application.convert;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bangjwo.portone.application.dto.PaymentDto;
import com.bangjwo.portone.domain.entity.Payments;
import com.bangjwo.portone.domain.entity.PaymentStatus;

@Component
public class PaymentConverter {
	public static Payments toEntity(PaymentDto.RequestDto dto) {
		return Payments.builder()
			.impUid(UUID.randomUUID().toString().substring(0, 10))
			.memberId(dto.memberId())
			.roomId(dto.roomId())
			.status(PaymentStatus.READY)
			.build();
	}

	public static PaymentDto.ResponseDto toDto(Payments entity) {
		return new PaymentDto.ResponseDto(
			entity.getImpUid(),
			entity.getMemberId(),
			entity.getRoomId(),
			entity.getStatus(),
			entity.getCreatedAt(),
			entity.getUpdatedAt()
		);
	}
}