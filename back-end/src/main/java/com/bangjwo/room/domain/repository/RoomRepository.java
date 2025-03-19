package com.bangjwo.room.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangjwo.room.domain.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
