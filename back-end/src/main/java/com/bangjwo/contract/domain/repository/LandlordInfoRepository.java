package com.bangjwo.contract.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.bangjwo.contract.domain.entity.LandlordInfo;

public interface LandlordInfoRepository extends CrudRepository<LandlordInfo, Long> {
}
