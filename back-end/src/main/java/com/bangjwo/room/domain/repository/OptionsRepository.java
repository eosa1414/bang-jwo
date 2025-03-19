package com.bangjwo.room.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bangjwo.room.domain.entity.Options;

public interface OptionsRepository extends JpaRepository<Options, Integer> {
}
