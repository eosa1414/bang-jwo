package com.bangjwo.room.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangjwo.room.domain.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	Optional<Room> findByRoomIdAndDeletedAtIsNull(Long roomId);

	Page<Room> findAllByMemberId(Long memberId, Pageable pageable);

	List<Room> findByRoomIdIn(List<Long> roomIds);

	Page<Room> findAll(Specification<Room> spec, Pageable pageable);
}
