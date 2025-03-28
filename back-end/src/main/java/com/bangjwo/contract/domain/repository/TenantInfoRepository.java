package com.bangjwo.contract.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bangjwo.contract.domain.entity.TenantInfo;

public interface TenantInfoRepository extends JpaRepository<TenantInfo, Long> {
}
