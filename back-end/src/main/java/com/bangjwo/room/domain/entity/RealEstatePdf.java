package com.bangjwo.room.domain.entity;

import com.bangjwo.global.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
public class RealEstatePdf extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pdfId;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
	private Long roomId;

	@Column(nullable = false)
	private String pdfUrl;
}

