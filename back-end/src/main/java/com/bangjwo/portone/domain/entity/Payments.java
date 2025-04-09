package com.bangjwo.portone.domain.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bangjwo.global.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment")
public class Payments extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	@Column(name = "merchant_uid", nullable = false)
	private String merchantUid;

	@Column(name = "imp_uid")
	private String impUid;

	@Column(name = "member_id", nullable = false)
	private Long memberId;

	@Column(name = "room_id", nullable = false)
	private Long roomId;

	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus status = PaymentStatus.READY;

	@Column(name = "pdf_url")
	private String pdfUrl;

	@Column(name = "json_url")
	private String jsonUrl;

	public void changeStatus(PaymentStatus newStatus) {
		this.status = newStatus;
	}

}
