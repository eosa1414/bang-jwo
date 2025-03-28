package com.bangjwo.room.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangjwo.room.domain.entity.Memo;
import com.bangjwo.room.domain.entity.Room;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
	Optional<Memo> findByRoomAndMemberId(Room room, Long memberId);
}
