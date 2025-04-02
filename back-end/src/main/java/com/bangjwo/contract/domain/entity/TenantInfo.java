package com.bangjwo.contract.domain.entity;

import java.time.LocalDate;

import com.bangjwo.global.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table
public class TenantInfo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tenantInfoId;

	@Column(length = 20)
	private String name;

	@Column(length = 20)
	private String phone;

	@Column(columnDefinition = "TEXT")
	private String address;

	@Column
	private String residentRegistrationNumber; // 암호화

	@Column
	private LocalDate moveInDate;

	@Column
	private String tenantSignatureUrl;
}

