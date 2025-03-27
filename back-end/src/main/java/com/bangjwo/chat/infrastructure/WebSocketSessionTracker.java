package com.bangjwo.chat.infrastructure;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class WebSocketSessionTracker {

	private final Map<Long, Set<Long>> roomUserMap = new ConcurrentHashMap<>();

	public void connect(Long roomId, Long userId) {
		roomUserMap.computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet()).add(userId);
	}

	public void disconnect(Long roomId, Long userId) {
		Set<Long> users = roomUserMap.get(roomId);
		if (users != null) {
			users.remove(userId);
			if (users.isEmpty()) {
				roomUserMap.remove(roomId);
			}
		}
	}

	// 방에 유저가 아무도 없는지 확인
	public boolean isAnyUserOnlineInRoom(Long roomId) {
		Set<Long> users = roomUserMap.get(roomId);
		return users != null && !users.isEmpty();
	}

	// 상대 유저가 채팅방을 구독 중인지 확인
	public boolean isUserOnlineInRoom(Long chatRoomId, Long userId) {
		Set<Long> users = roomUserMap.get(chatRoomId);
		return users != null && users.contains(userId);
	}
}
