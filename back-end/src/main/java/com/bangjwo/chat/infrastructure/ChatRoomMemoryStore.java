package com.bangjwo.chat.infrastructure;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.bangjwo.chat.domain.entity.ChatRoom;

@Component
public class ChatRoomMemoryStore {

	private final Map<Long, ChatRoom> roomCache = new ConcurrentHashMap<>();

	public void cache(ChatRoom room) {
		roomCache.put(room.getId(), room);
	}

	public Optional<ChatRoom> get(Long roomId) {
		return Optional.ofNullable(roomCache.get(roomId));
	}

	public void evict(Long roomId) {
		roomCache.remove(roomId);
	}

	public void clearAll() {
		roomCache.clear();
	}
}

