package com.bangjwo.chat.application.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bangjwo.chat.application.dto.ChatAlertDto;
import com.bangjwo.chat.application.dto.ChatMessageDto;
import com.bangjwo.chat.application.dto.ChatRoomSummary;
import com.bangjwo.chat.domain.entity.ChatAlert;
import com.bangjwo.chat.domain.entity.ChatMessage;
import com.bangjwo.chat.domain.repository.AlertMongoRepository;
import com.bangjwo.chat.domain.repository.ChatMongoRepository;
import com.mongodb.client.result.UpdateResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageServiceImpl implements ChatMessageService {

	private final ChatMongoRepository chatMongoRepository;
	private final MongoTemplate mongoTemplate;
	private final RedisChatRoomService redisChatRoomService;

	@Override
	public void saveChatMessage(ChatMessageDto dto, boolean isReceiverOnline) {
		ChatRoomSummary info = redisChatRoomService.getChatRoomInfo(dto.senderId(), dto.chatRoomId());

		log.info("받는사람 : {} , 메세지 내용 : {}", info.getOtherId(), dto.message());

		chatMongoRepository.save(ChatMessage.builder()
			.chatRoomId(dto.chatRoomId())
			.roomId(info.getRoomId())
			.receiverId(info.getOtherId())
			.senderId(dto.senderId())
			.senderNickname(info.getNickname())
			.message(dto.message())
			.sendAt(dto.sendAt())
			.read(isReceiverOnline)
			.build());
	}

	@Override
	public void markMessagesAsRead(Long chatRoomId, Long userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("chatRoomId").is(chatRoomId)
			.and("senderId").ne(userId)
			.and("read").is(false));

		Update update = new Update().set("read", true);

		UpdateResult result = mongoTemplate.updateMulti(query, update, ChatMessage.class);

		log.info("{}개의 메시지를 읽음 처리함", result.getModifiedCount());
	}

	@Override
	public List<ChatMessageDto> getChatMessages(Long chatRoomId) {
		return chatMongoRepository.findByChatRoomIdOrderBySendAtAsc(chatRoomId);
	}
}
