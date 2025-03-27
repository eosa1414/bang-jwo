package com.bangjwo.chat.application.service;

import java.util.List;

import com.bangjwo.chat.application.dto.ChatMessageDto;

public interface ChatMessageService {
	void saveChatMessage(ChatMessageDto dto);

	void markMessagesAsRead(Long chatRoomId, Long userId);

	List<ChatMessageDto> getChatMessages(Long chatRoomId);
}
