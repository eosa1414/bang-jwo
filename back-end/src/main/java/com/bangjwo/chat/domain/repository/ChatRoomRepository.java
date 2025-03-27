package com.bangjwo.chat.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bangjwo.chat.domain.entity.ChatRoom;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	Optional<ChatRoom> findByRoomIdAndTenantId(Long roomId, Long tenantId);

	@Query("SELECT r FROM ChatRoom r WHERE r.id = :chatRoomId AND (r.landlordId = :userId OR r.tenantId = :userId)")
	ChatRoom findByChatRoomIdAndUserId(@Param("chatRoomId") Long chatRoomId, @Param("userId") Long userId);

	@Query("SELECT CASE WHEN cr.landlordId = :userId THEN cr.tenantId ELSE cr.landlordId END " +
		"FROM ChatRoom cr WHERE cr.id = :chatRoomId AND (:userId = cr.landlordId OR :userId = cr.tenantId)")
	Long findOtherUserId(@Param("chatRoomId") Long chatRoomId, @Param("userId") Long userId);

	@Query("SELECT cr.id FROM ChatRoom cr WHERE cr.roomId = :roomId AND (cr.landlordId = :userId OR cr.tenantId = :userId)")
	Long findChatRoomIdByRoomIdAndUserId(@Param("roomId") Long roomId, @Param("userId") Long userId);


	boolean existsByRoomIdAndTenantId(Long roomId, Long tenantId);
}
