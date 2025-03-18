package com.bangjwo.contract.domain.entity;

import com.bangjwo.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TENANT_INFO")
public class TenantInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tenantInfoId;

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

