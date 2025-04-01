package com.bangjwo.payment.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
public class Payments {

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

	@CreatedDate
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;


	public void changeStatus(Status newStatus, LocalDateTime newUpdatedAt) {
		this.status = newStatus;
		this.updatedAt = newUpdatedAt;
	}

}
