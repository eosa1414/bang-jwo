package com.bangjwo.chat.application.service;

import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations;

import com.bangjwo.chat.application.dto.ChatRoomSummary;

public interface RedisChatRoomService {

	void updateLastMessage(Long roomId, Long senderId, Long reciverId, String message, String sendAt, boolean isReceiverOnline);

	Set<ZSetOperations.TypedTuple<String>> getRoomList(Long userId);

	void resetUnreadCount(Long userId, Long roomId);
}
