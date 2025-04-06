package com.bangjwo.portone.domain.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bangjwo.global.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table
public class Payments extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	@Column(name = "imp_uid", nullable = false)
	private String impUid;

	@Column(name = "member_id", nullable = false)
	private Long memberId;

	@Column(name = "room_id", nullable = false)
	private Long roomId;

	@Column(name = "payment_status")
	private PaymentStatus status = PaymentStatus.READY;

	public void changeStatus(PaymentStatus newStatus) {
		this.status = newStatus;
	}

}
