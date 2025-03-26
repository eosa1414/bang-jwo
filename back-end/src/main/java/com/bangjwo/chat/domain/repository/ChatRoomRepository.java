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


	List<ChatRoom> findByLandlordIdOrTenantId(Long landlordId, Long tenantId);

	boolean existsByRoomIdAndTenantId(Long roomId, Long tenantId);
}
