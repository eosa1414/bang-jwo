package com.bangjwo.chat.infrastructure;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.bangjwo.chat.domain.entity.ChatRoom;

@Component
public class ChatRoomMemoryStore {

	// 인메모리 캐시: 채팅방 ID를 키(key)로, 채팅방(ChatRoom) 객체를 값(value)으로 저장하는 ConcurrentHashMap.
	// ConcurrentHashMap은 멀티스레드 환경에서도 안전하게 사용할 수 있습니다.
	private final Map<Long, ChatRoom> roomCache = new ConcurrentHashMap<>();

	/**
	 * 채팅방 정보를 인메모리 캐시에 저장합니다.
	 *
	 * @param room 저장할 채팅방 객체 (ChatRoom)
	 */
	public void cache(ChatRoom room) {
		// 채팅방 객체의 ID(room.getId())를 키로 하여, 해당 채팅방 정보를 캐시에 저장합니다.
		roomCache.put(room.getId(), room);
	}

	/**
	 * 인메모리 캐시에서 특정 채팅방 정보를 조회합니다.
	 *
	 * @param roomId 조회할 채팅방의 ID
	 * @return 해당 채팅방 객체를 Optional로 반환 (없으면 Optional.empty() 반환)
	 */
	public Optional<ChatRoom> get(Long roomId) {
		// roomId에 해당하는 채팅방 객체를 가져와, null일 경우 Optional.empty()로 감싸 반환합니다.
		return Optional.ofNullable(roomCache.get(roomId));
	}

	/**
	 * 인메모리 캐시에서 특정 채팅방 정보를 제거합니다.
	 *
	 * @param roomId 제거할 채팅방의 ID
	 */
	public void evict(Long roomId) {
		// 주어진 roomId를 키로 하는 채팅방 정보를 캐시에서 삭제합니다.
		roomCache.remove(roomId);
	}

	/**
	 * 인메모리 캐시의 모든 채팅방 정보를 초기화합니다.
	 */
	public void clearAll() {
		// 캐시에 저장된 모든 채팅방 정보를 제거하여, 캐시를 비웁니다.
		roomCache.clear();
	}
}


