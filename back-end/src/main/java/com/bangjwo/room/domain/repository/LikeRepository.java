package com.bangjwo.room.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bangjwo.room.domain.entity.Likes;
import com.bangjwo.room.domain.entity.Room;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {
	Optional<Likes> findByRoomAndMemberId(Room room, Long memberId);

	@Query("""
		SELECT l
		FROM Likes l
		WHERE l.room IN :rooms AND l.memberId = :memberId
		""")
	List<Likes> findByRoomInAndMemberId(@Param("rooms") List<Room> rooms, @Param("memberId") Long memberId);

	Page<Likes> findAllByMemberIdAndFlagTrue(Long memberId, Pageable pageable);
}
