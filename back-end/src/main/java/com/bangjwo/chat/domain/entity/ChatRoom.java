package com.bangjwo.chat.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "chat_room")
public class ChatRoom {

	@Id
	@Column(name = "chat_room_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "landlord_id", nullable = false)
	private Long landlordId;

	@Column(name = "tenant_id", nullable = false)
	private Long tenantId;

	@Column(name = "room_id", nullable = false)
	private Long roomId;

	@Column(name = "landload_unread_count", nullable = false)
	private Long landloadUnreadCount;

	@Column(name = "tenant_unread_count", nullable = false)
	private Long tenantUnreadCount;

	@CreatedDate
	@Column(name = "created_at", updatable = false, nullable = false)
	private LocalDateTime createdAt;
}

