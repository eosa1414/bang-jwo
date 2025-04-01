package com.bangjwo.chat.application.service;

import java.time.Instant;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.chat.application.dto.ChatMessageDto;
import com.bangjwo.chat.application.dto.ChatRoomDto;
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

	/*
	* 채팅방 정보 입력
	* */
	@Override
	@Transactional
	public void createChatRoom(ChatRoomDto.ResponseDto dto) {

		log.info(" createChatRoom 호출됨");

		Long senderId = dto.tenantId(); // 세입자
		Long receiverId = dto.landlordId(); // 집주인
		Long roomId = dto.roomId(); // 매물
		Long chatRoomId = dto.chatRoomId(); // 채팅방 아이디

		String senderKey = getKey(senderId);
		String receiverKey = getKey(receiverId);

		// 채팅방 아이디, 유저 닉네임, 각각 사진
		///////// 추후 수정 ////////////////
		String senderImage = "senderImage";
		String receiverImage = "receiverImage";
		String senderNickname = "senderNickname";
		String receiverNickname = "receiverNickname";
		String message = "채팅방이 생성되었습니다.";
		String sendAt = Instant.now().toString();
		/// ////////////////////////////////

		ChatRoomSummary senderSummary = ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(roomId)
			.lastMessage(message)
			.otherId(receiverId)
			.profileImage(receiverImage)
			.nickname(receiverNickname)
			.sendAt(sendAt)
			.unreadCount(0)
			.build();

		ChatRoomSummary receiverSummary = ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(roomId)
			.lastMessage(message)
			.otherId(senderId)
			.profileImage(senderImage)
			.nickname(senderNickname)
			.sendAt(sendAt)
			.unreadCount(1)
			.build();

		try {
			String senderJson = objectMapper.writeValueAsString(senderSummary);
			double senderScore = Instant.parse(sendAt).toEpochMilli();
			redisTemplate.opsForZSet().add(senderKey, senderJson, senderScore); // 새로 추가

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
			.otherId(mySummary.getOtherId())
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

	@Override
	public void updateLastMessages(ChatMessageDto dto, boolean isReceiverOnline) {
		ChatRoomSummary chatRoomInfo = getChatRoomInfo(dto.senderId(), dto.chatRoomId());

		Long chatRoomId = dto.chatRoomId();
		Long senderId = dto.senderId();
		Long receiverId = chatRoomInfo.getOtherId();
		String message = dto.message();
		String sendAt = dto.sendAt();

		String senderKey = getKey(senderId);
		String receiverKey = getKey(receiverId);

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
			.otherId(senderExisting.getOtherId())
			.profileImage(senderExisting.getProfileImage())
			.nickname(senderExisting.getNickname())
			.sendAt(sendAt)
			.unreadCount(senderExisting.getUnreadCount())
			.build();

		ChatRoomSummary receiverUpdated = ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(receiverExisting.getRoomId())
			.lastMessage(message)
			.otherId(receiverExisting.getOtherId())
			.profileImage(receiverExisting.getProfileImage())
			.nickname(receiverExisting.getNickname())
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
			redisTemplate.opsForZSet().remove(receiverKey, objectMapper.writeValueAsString(receiverExisting));
			redisTemplate.opsForZSet().add(receiverKey, receiverJson, receiverScore);

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

	/*
	*  채팅방 정보
	* */
	@Override
	public ChatRoomSummary getChatRoomInfo(Long userId, Long chatRoomId) {
		String key = getKey(userId);

		return getChatRoomSummary(key, chatRoomId);
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
}