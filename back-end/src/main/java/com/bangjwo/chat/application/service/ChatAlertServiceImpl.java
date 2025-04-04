package com.bangjwo.chat.application.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bangjwo.chat.domain.entity.ChatAlert;
import com.bangjwo.chat.domain.repository.AlertMongoRepository;
import com.bangjwo.global.common.error.chat.ChatErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
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
	public void saveAlert(ChatAlert alert) {
		try {
			alertMongoRepository.save(alert);
			log.info("알림 저장 성공");
		} catch (Exception e) {
			log.error("알림 저장 실패", e);
			throw new BusinessException(ChatErrorCode.CHAT_ALERT_SAVE_FAILED);
		}
	}

	@Override
	public void markAlertAsRead(Long chatRoomId, Long userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("chatRoomId").is(chatRoomId)
			.and("senderId").ne(userId)
			.and("read").is(false));

		Update update = new Update().set("read", true);

		try {
			UpdateResult result = mongoTemplate.updateMulti(query, update, ChatAlert.class);
			log.info("{}개의 알림을 읽음 처리함", result.getModifiedCount());
		} catch (Exception e) {
			log.error("알림 읽음 처리 실패", e);
			throw new BusinessException(ChatErrorCode.CHAT_ALERT_UPDATE_FAILED);
		}
	}

	@Override
	public List<ChatAlert> getAlerts(Long userId) {
		try {
			return alertMongoRepository.findByReceiverIdAndReadFalseOrderBySendAtDesc(userId);
		} catch (Exception e) {
			log.error("알림 조회 실패", e);
			throw new BusinessException(ChatErrorCode.CHAT_ALERT_RETRIEVE_FAILED);
		}
	}
}
