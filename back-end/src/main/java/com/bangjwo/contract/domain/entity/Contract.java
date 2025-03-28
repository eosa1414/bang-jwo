package com.bangjwo.contract.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.bangjwo.contract.domain.vo.ContractStatus;
import com.bangjwo.global.common.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "CONTRACT")
public class Contract extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contractId;

	@Column(nullable = false, unique = true)
	private Long roomId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "landlord_info_id", nullable = false)
	private LandlordInfo landlordInfo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tenant_info_id", nullable = false)
	private TenantInfo tenantInfo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "contract_id")
	private List<SpecialClause> specialClauses = new ArrayList<>();

	@Column(nullable = false)
	private String ipfsKey; // 암호화 -> 이게 뭐고 ㅋㅋ

	// ENUM('DONE','UNDONE') → String
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ContractStatus contractStatus;

	@Column(nullable = false)
	private Boolean landlordAuth;

	@Column(nullable = false)
	private Boolean tenantAuth;

	public Contract(Long roomId, LandlordInfo landlordInfo, TenantInfo tenantInfo, String ipfsKey) {
		this.roomId = roomId;
		this.landlordInfo = landlordInfo;
		this.tenantInfo = tenantInfo;
		this.ipfsKey = ipfsKey;    // 해당 부분 서버에서 생성해야되면 수정
		this.contractStatus = ContractStatus.BEFORE_WRITE;
		this.landlordAuth = false;
		this.tenantAuth = false;
	}

	// 특약 조건 여러개가 들어갈 수 있으므로 메서드 따로 구현
	public void addSpecialClause(SpecialClause clause) {
		this.specialClauses.add(clause);
	}
}
