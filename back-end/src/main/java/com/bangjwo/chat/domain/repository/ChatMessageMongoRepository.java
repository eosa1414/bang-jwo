package com.bangjwo.chat.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bangjwo.chat.application.dto.ChatMessageDto;
import com.bangjwo.chat.domain.entity.ChatMessage;

public interface ChatMessageMongoRepository extends MongoRepository<ChatMessage, String> {

	List<ChatMessageDto> findByChatRoomIdOrderBySendAtAsc(Long chatRoomId);
}
