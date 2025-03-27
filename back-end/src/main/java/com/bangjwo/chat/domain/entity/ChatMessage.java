package com.bangjwo.chat.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Document(collection = "messages")
public class ChatMessage {

	@Id
	private String id;

	private Long chatRoomId;

	@Indexed
	private Long roomId; // 매물 아이디

	private Long receiverId;

	private Long senderId;

	private String senderNickname;

	private String message;

	@Indexed
	private String sendAt;

	private boolean read;
}
