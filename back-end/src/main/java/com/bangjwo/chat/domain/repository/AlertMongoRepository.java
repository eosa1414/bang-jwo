package com.bangjwo.chat.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bangjwo.chat.domain.entity.ChatAlert;

public interface AlertMongoRepository extends MongoRepository<ChatAlert,Long> {

	List<ChatAlert> findByChatRoomIdAndReceiverIdAndReadFalseOrderBySendAtAsc(
		Long chatRoomId,
		Long receiverId
	);

}
