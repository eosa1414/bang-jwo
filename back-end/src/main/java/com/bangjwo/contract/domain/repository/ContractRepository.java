package com.bangjwo.contract.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangjwo.contract.domain.entity.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
	Optional<Contract> findByContractId(Long contractId);
}
