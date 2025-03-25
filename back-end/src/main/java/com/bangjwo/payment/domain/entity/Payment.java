package com.bangjwo.payment.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PAYMENT")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
	private Long amount;

	@Column(length = 50)
	private String paymentNo;

	@Column(length = 50)
	private String apprNo;

	@CreatedDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdAt;
}
