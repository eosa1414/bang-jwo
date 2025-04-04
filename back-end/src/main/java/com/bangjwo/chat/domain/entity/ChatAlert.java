package com.bangjwo.chat.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Document(collection = "alert")
public class ChatAlert {

	@Id
	private String id;

	private Long chatRoomId;

	@Indexed
	private Long receiverId;

	private Long senderId;

	private String senderImage;

	private String senderNickname;

	private String message;

	@Indexed
	private String sendAt;

	private boolean read;
}
