package com.bangjwo.chat.infrastructure;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class WebSocketSessionTracker {

	private final Map<Long, Set<Long>> roomUserMap = new ConcurrentHashMap<>();
	private final Map<Long, Set<Long>> alertUserMap = new ConcurrentHashMap<>();

	public void roomConnect(Long chatRoomId, Long userId) {
		roomUserMap.computeIfAbsent(chatRoomId, k -> ConcurrentHashMap.newKeySet()).add(userId);
	}

	public void roomDisconnect(Long chatRoomId, Long userId) {
		Set<Long> users = roomUserMap.get(chatRoomId);
		if (users != null) {
			users.remove(userId);
			if (users.isEmpty()) {
				roomUserMap.remove(chatRoomId);
			}
		}
	}

	// 상대 유저가 채팅방을 구독 중인지 확인
	public boolean isUserOnlineInRoom(Long chatRoomId, Long userId) {
		Set<Long> users = roomUserMap.get(chatRoomId);
		return users != null && users.contains(userId);
	}

	public void alertConnect(Long chatRoomId, Long userId) {
		alertUserMap.computeIfAbsent(chatRoomId, k -> ConcurrentHashMap.newKeySet()).add(userId);
	}

	public void alertDisconnect(Long chatRoomId, Long userId) {
		Set<Long> users = alertUserMap.get(chatRoomId);
		if (users != null) {
			users.remove(userId);
			if (users.isEmpty()) {
				alertUserMap.remove(chatRoomId);
			}
		}
	}

	// 상대 유저가 알림을 구독 중인지 확인
	public boolean isUserOnlineInAlert(Long chatRoomId, Long userId) {
		Set<Long> users = alertUserMap.get(chatRoomId);
		return users != null && users.contains(userId);
	}

}
