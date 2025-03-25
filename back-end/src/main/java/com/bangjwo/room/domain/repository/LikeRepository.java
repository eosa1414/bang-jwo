package com.bangjwo.room.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangjwo.room.domain.entity.Likes;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
	Optional<Likes> findByRoomIdAndMemberId(Long roomId, Long memberId);
}
