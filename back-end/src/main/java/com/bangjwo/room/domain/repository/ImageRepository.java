package com.bangjwo.room.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bangjwo.room.domain.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
