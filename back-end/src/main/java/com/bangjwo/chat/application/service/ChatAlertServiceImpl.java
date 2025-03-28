package com.bangjwo.chat.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bangjwo.chat.application.dto.ChatAlertDto;
import com.bangjwo.chat.domain.entity.ChatAlert;
import com.bangjwo.chat.domain.entity.ChatMessage;
import com.bangjwo.chat.domain.repository.AlertMongoRepository;
import com.mongodb.client.result.UpdateResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatAlertServiceImpl implements ChatAlertService {

	private final AlertMongoRepository alertMongoRepository;
	private final MongoTemplate mongoTemplate;

	@Override
	public void saveAlert(ChatAlert dto) {
		alertMongoRepository.save(dto);
	}

	@Override
	public void markAlertAsRead(Long chatRoomId, Long userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("chatRoomId").is(chatRoomId)
			.and("senderId").ne(userId)
			.and("read").is(false));

		Update update = new Update().set("read", true);

		UpdateResult result = mongoTemplate.updateMulti(query, update, ChatAlert.class);

		log.info("{}개의 알림을 읽음 처리함", result.getModifiedCount());
	}

	@Override
	public List<ChatAlert> getAlerts(Long chatRoomId, Long userId) {
		return alertMongoRepository.findByChatRoomIdAndReceiverIdAndReadFalseOrderBySendAtAsc(chatRoomId, userId);
	}
}
