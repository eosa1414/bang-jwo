package com.bangjwo.chat.application.service;

import java.time.Instant;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.bangjwo.chat.application.dto.ChatRoomSummary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisChatRoomServiceImpl implements RedisChatRoomService {

	private final RedisTemplate<String, String> redisTemplate;
	private final ObjectMapper objectMapper;

	private String getKey(Long userId) {
		return "chat:room:list:" + userId;
	}

	/**
	 * 마지막 메시지 & 시간 업데이트 (새 메시지 도착 시)
	 */
	@Override
	public void updateLastMessage(Long roomId, Long senderId, Long receiverId, String message, String sendAt, boolean isReceiverOnline) {
		String senderKey = getKey(senderId);
		String receiverKey = getKey(receiverId);

		ChatRoomSummary senderExisting = getChatRoomSummary(senderKey, roomId);
		ChatRoomSummary receiverExisting = getChatRoomSummary(receiverKey, roomId);

		if (senderExisting == null) {
			// 멤버 서비스 생기면 추가
			// String otherNickname = userService.getNicknameOfReceiver(roomId, userId);
			String senderNickname = "senderNickname";
			senderExisting = ChatRoomSummary.builder()
				.roomId(roomId)
				.nickname(senderNickname)
				.unreadCount(0)
				.build();
		}
		if (receiverExisting == null) {
			String receiverNickname = "receiverNickname";
			receiverExisting = ChatRoomSummary.builder()
				.roomId(roomId)
				.nickname(receiverNickname)
				.unreadCount(0)
				.build();
		}

		ChatRoomSummary senderUpdated = ChatRoomSummary.builder()
			.roomId(roomId)
			.nickname(senderExisting.getNickname())
			.lastMessage(message)
			.sendAt(sendAt)
			.unreadCount(senderExisting.getUnreadCount())
			.build();

		ChatRoomSummary receiverUpdated = ChatRoomSummary.builder()
			.roomId(roomId)
			.nickname(receiverExisting.getNickname())
			.lastMessage(message)
			.sendAt(sendAt)
			.unreadCount(isReceiverOnline ? 0 : receiverExisting.getUnreadCount() + 1)
			.build();

		try {
			String senderJson = objectMapper.writeValueAsString(senderUpdated);
			double senderScore = Instant.parse(sendAt).toEpochMilli();
			redisTemplate.opsForZSet().remove(senderKey, objectMapper.writeValueAsString(senderExisting)); // 기존 제거
			redisTemplate.opsForZSet().add(senderKey, senderJson, senderScore); // 새로 추가

			String receiverJson = objectMapper.writeValueAsString(receiverUpdated);
			double receiverScore = Instant.parse(sendAt).toEpochMilli();
			redisTemplate.opsForZSet().remove(receiverKey, objectMapper.writeValueAsString(receiverExisting)); // 기존 제거
			redisTemplate.opsForZSet().add(receiverKey, receiverJson, receiverScore); // 새로 추가

		} catch (JsonProcessingException e) {
			log.error("Redis 직렬화 실패", e);
		}
	}


	/**
	 * 채팅방 목록 조회 (ZSet)
	 */
	@Override
	public Set<ZSetOperations.TypedTuple<String>> getRoomList(Long userId) {
		String key = getKey(userId);
		return redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, -1);
	}

	/**
	 * 특정 채팅방 정보 가져오기 (ZSet 내부 전체 순회 → 파싱)
	 */
	private ChatRoomSummary getChatRoomSummary(String key, Long roomId) {
		Set<String> all = redisTemplate.opsForZSet().range(key, 0, -1);
		if (all == null) return null;

		for (String json : all) {
			try {
				ChatRoomSummary summary = objectMapper.readValue(json, ChatRoomSummary.class);
				if (summary.getRoomId().equals(roomId)) {
					return summary;
				}
			} catch (JsonProcessingException e) {
				log.warn("Redis 역직렬화 실패", e);
			}
		}
		return null;
	}

	/**
	 * 안읽은 채팅 수 초기화
	 */
	public void resetUnreadCount(Long userId, Long roomId) {
		String key = getKey(userId);
		ChatRoomSummary existing = getChatRoomSummary(key, roomId);

		if (existing == null) return;

		ChatRoomSummary updated = ChatRoomSummary.builder()
			.roomId(existing.getRoomId())
			.nickname(existing.getNickname())
			.lastMessage(existing.getLastMessage())
			.sendAt(existing.getSendAt())
			.unreadCount(0)
			.build();

		try {
			String oldJson = objectMapper.writeValueAsString(existing);
			redisTemplate.opsForZSet().remove(key, oldJson);

			String updatedJson = objectMapper.writeValueAsString(updated);
			double score = Instant.parse(updated.getSendAt()).toEpochMilli();
			redisTemplate.opsForZSet().add(key, updatedJson, score);
		} catch (JsonProcessingException e) {
			log.error("Redis 직렬화 실패", e);
		}
	}


}
