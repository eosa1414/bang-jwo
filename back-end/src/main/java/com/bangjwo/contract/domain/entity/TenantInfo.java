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

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "TENANT_INFO")
public class TenantInfo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tenantInfoId;

	@Column(nullable = false)
	private Long tenantId;

	@Column(length = 20, nullable = false)
	private String name;

	@Column(length = 20, nullable = false)
	private String phone;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String address;

	@Column(nullable = false)
	private String residentRegistrationNumber; // 암호화

	@Column(nullable = false)
	private LocalDate moveInDate;

	@Column
	private String tenantSignatureUrl;
}

