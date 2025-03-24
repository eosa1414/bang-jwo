package com.bangjwo.room.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangjwo.room.domain.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
