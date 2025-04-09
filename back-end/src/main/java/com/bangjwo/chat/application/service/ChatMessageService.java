package com.bangjwo.chat.application.service;

import java.util.List;

import com.bangjwo.chat.application.dto.ChatMessageDto;
import com.bangjwo.chat.application.dto.ChatMessageResponseDto;

public interface ChatMessageService {
	void saveChatMessage(ChatMessageDto dto, boolean isReceiverOnline);

	void markMessagesAsRead(Long chatRoomId, Long userId);

	List<ChatMessageResponseDto> getChatMessages(Long chatRoomId);

}
