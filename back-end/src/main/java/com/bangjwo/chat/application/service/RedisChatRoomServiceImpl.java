package com.bangjwo.chat.application.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.chat.application.dto.ChatMessageDto;
import com.bangjwo.chat.application.dto.ChatRoomDto;
import com.bangjwo.chat.application.dto.ChatRoomSummary;
import com.bangjwo.chat.domain.entity.ChatRoom;
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
	private final ChatRoomService chatRoomService;

	private String getKey(Long userId) {
		return "chat:room:list:" + userId;
	}

	/*
	* 채팅방 정보 입력
	* */
	@Override
	@Transactional
	public void createChatRoom(ChatRoomDto.RequestDto requestDto) {

		log.info(" createChatRoom 호출됨");

		Long senderId = requestDto.tenantId(); // 세입자
		Long receiverId = requestDto.landlordId(); // 집주인
		Long roomId = requestDto.roomId(); // 매물

		String senderKey = getKey(senderId);
		String receiverKey = getKey(receiverId);

		// 채팅방 아이디, 유저 닉네임, 각각 사진
		///////// 추후 수정 //////////
		Long chatRoomId = chatRoomService.getChatRoomId(roomId, senderId);
		String senderImage = "senderImage";
		String receiverImage = "receiverImage";
		String senderNickname = "senderNickname";
		String receiverNickname = "receiverNickname";
		String message = "채팅방이 생성되었습니다.";
		String sendAt = Instant.now().toString();
		/// ////////////////////////////////

		ChatRoomSummary receiverSummary = ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(roomId)
			.lastMessage(message)
			.profileImage(receiverImage)
			.nickname(receiverNickname)
			.sendAt(sendAt)
			.unreadCount(0)
			.build();

		ChatRoomSummary senderSummary = ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(roomId)
			.lastMessage(message)
			.profileImage(senderImage)
			.nickname(senderNickname)
			.sendAt(sendAt)
			.unreadCount(1)
			.build();

		try {
			String senderJson = objectMapper.writeValueAsString(senderSummary);
			double senderScore = Instant.parse(sendAt).toEpochMilli();
			// redisTemplate.opsForZSet().add(senderKey, senderJson, senderScore); // 새로 추가

			Boolean result = redisTemplate.opsForZSet().add(senderKey, senderJson, senderScore);
			log.info("🔍 Redis ZSet add result = {}", result); // true or false


			log.info(senderJson);
			log.info(String.valueOf(senderScore));

			String receiverJson = objectMapper.writeValueAsString(receiverSummary);
			double receiverScore = Instant.parse(sendAt).toEpochMilli();
			redisTemplate.opsForZSet().add(receiverKey, receiverJson, receiverScore); // 새로 추가

		} catch (JsonProcessingException e) {
			log.error("Redis 직렬화 실패", e);
		}
	}

	@Override
	public void updateChatRoom(Long chatRoomId, Long userId) {
		String myKey = getKey(userId);
		ChatRoomSummary mySummary = getChatRoomSummary(myKey, chatRoomId);

		// 만약 생성된 채팅방 정보가 없다면 예외 처리 (또는 로깅 후 리턴)
		if (mySummary == null) {
			log.info("유저아이디 (userId: {})", userId);
			log.error("채팅방 생성 데이터가 존재하지 않습니다. (chatRoomId: {})", chatRoomId);
			return;
		}

		ChatRoomSummary myChatRoomUpdated = ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(mySummary.getRoomId())
			.lastMessage(mySummary.getLastMessage())
			.profileImage(mySummary.getProfileImage())
			.nickname(mySummary.getNickname())
			.sendAt(mySummary.getSendAt())
			.unreadCount(0)
			.build();

		try {
			String myChatRoomJson = objectMapper.writeValueAsString(myChatRoomUpdated);
			double myChatRoomScore = Instant.parse(mySummary.getSendAt()).toEpochMilli();
			redisTemplate.opsForZSet().remove(myKey, objectMapper.writeValueAsString(mySummary)); // 기존 제거
			redisTemplate.opsForZSet().add(myKey, myChatRoomJson, myChatRoomScore); // 새로 추가
		} catch (JsonProcessingException e) {
			log.error("Redis 직렬화 실패", e);
		}
	}

	/**
	 * 마지막 메시지 & 시간 업데이트 (새 메시지 도착 시)
	 */
	@Override
	public void updateLastMessage(ChatMessageDto dto) {
		Long chatRoomId = dto.chatRoomId();
		Long senderId = dto.senderId();
		Long receiverId = dto.receiverId();
		String message = dto.message();
		String sendAt = dto.sendAt();

		String senderKey = getKey(senderId);
		String receiverKey = getKey(receiverId);
		log.info(String.valueOf(senderId));
		log.info(String.valueOf(receiverId));
		log.info(senderKey);
		log.info(receiverKey);

		ChatRoomSummary senderExisting = getChatRoomSummary(senderKey, chatRoomId);
		ChatRoomSummary receiverExisting = getChatRoomSummary(receiverKey, chatRoomId);

		// 만약 생성된 채팅방 정보가 없다면 예외 처리 (또는 로깅 후 리턴)
		if (senderExisting == null || receiverExisting == null) {
			log.info(String.valueOf(senderExisting));
			log.info(String.valueOf(receiverExisting));
			log.error("채팅방 생성 데이터가 존재하지 않습니다. (chatRoomId: {})", chatRoomId);
			return;
		}

		ChatRoomSummary senderUpdated = ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(senderExisting.getRoomId())
			.lastMessage(message)
			.profileImage(senderExisting.getProfileImage())
			.nickname(senderExisting.getNickname())
			.sendAt(sendAt)
			.unreadCount(senderExisting.getUnreadCount())
			.build();

		ChatRoomSummary receiverUpdated = ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(receiverExisting.getRoomId())
			.lastMessage(message)
			.profileImage(receiverExisting.getProfileImage())
			.nickname(receiverExisting.getNickname())
			.sendAt(sendAt)
			.unreadCount(dto.read() ? 0 : receiverExisting.getUnreadCount() + 1)
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
	private ChatRoomSummary getChatRoomSummary(String key, Long chatRoomId) {
		Set<String> all = redisTemplate.opsForZSet().range(key, 0, -1);
		if (all == null) return null;

		for (String json : all) {
			try {
				ChatRoomSummary summary = objectMapper.readValue(json, ChatRoomSummary.class);
				if (summary.getChatRoomId().equals(chatRoomId)) {
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