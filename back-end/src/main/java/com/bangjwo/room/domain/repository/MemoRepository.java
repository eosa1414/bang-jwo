package com.bangjwo.room.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangjwo.room.domain.entity.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
	Optional<Memo> findByRoomIdAndMemberId(Long roomId, Long memberId);
}
