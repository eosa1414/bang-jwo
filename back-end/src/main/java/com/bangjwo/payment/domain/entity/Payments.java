package com.bangjwo.payment.domain.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bangjwo.global.common.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "PAYMENT")
public class Payments extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	@Column(name = "imp_uid", nullable = false)
	private String impUid;

	@Column(name = "member_id",  nullable = false)
	private Long memberId;

	@Column(name = "room_id", nullable = false)
	private Long roomId;

	@Column(name = "status", nullable = false)
	private Status status = Status.READY;


	public void changeStatus(Status newStatus) {
		this.status = newStatus;
	}

}
