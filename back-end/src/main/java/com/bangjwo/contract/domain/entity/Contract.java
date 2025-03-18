package com.bangjwo.contract.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CONTRACT")
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contractId;

	@Column(nullable = false)
	private Long landlordId;

	@Column(nullable = false)
	private Long tenantId;

	@Column(nullable = false)
	private Long roomId;

	@Column(nullable = false)
	private Long tenantInfoId;

	@Column(nullable = false)
	private Long landloadInfoId;

	@Column(nullable = false)
	private String ipfsKey; // 암호화

	// ENUM('DONE','UNDONE') → String
	@Column(nullable = false)
	private String status;

	@Column(nullable = false)
	private Boolean landlordAuth;

	@Column(nullable = false)
	private Boolean tenantAuth;
}
