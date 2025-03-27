package com.bangjwo.chat.application.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomSummary implements Serializable {

	private Long chatRoomId;
	private Long roomId;
	private String lastMessage;
	private String profileImage;
	private String nickname;
	private String sendAt; // ISO8601 문자열 (예: 2025-03-26T00:52:49.475Z)
	private int unreadCount;
}