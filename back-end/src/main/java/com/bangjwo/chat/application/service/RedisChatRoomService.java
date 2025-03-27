package com.bangjwo.chat.application.service;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations;

import com.bangjwo.chat.application.dto.ChatMessageDto;
import com.bangjwo.chat.application.dto.ChatRoomDto;
import com.bangjwo.chat.application.dto.ChatRoomSummary;

public interface RedisChatRoomService {

	void createChatRoom(ChatRoomDto.RequestDto requestDto);

	void updateChatRoom(Long chatRoomId, Long userId);

	void updateLastMessage(ChatMessageDto dto);

	Set<ZSetOperations.TypedTuple<String>> getRoomList(Long userId);

	void resetUnreadCount(Long userId, Long roomId);
}
