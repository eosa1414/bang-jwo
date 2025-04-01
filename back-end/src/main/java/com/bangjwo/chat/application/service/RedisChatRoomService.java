package com.bangjwo.chat.application.service;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations;

import com.bangjwo.chat.application.dto.ChatMessageDto;
import com.bangjwo.chat.application.dto.ChatRoomDto;
import com.bangjwo.chat.application.dto.ChatRoomSummary;

public interface RedisChatRoomService {

	void createChatRoom(ChatRoomDto.ResponseDto dto);

	void updateChatRoom(Long chatRoomId, Long userId);

	Set<ZSetOperations.TypedTuple<String>> getRoomList(Long userId);

	ChatRoomSummary getChatRoomInfo(Long userId, Long chatRoomId);

	void updateLastMessages(ChatMessageDto dto, boolean isReceiverOnline);
}
