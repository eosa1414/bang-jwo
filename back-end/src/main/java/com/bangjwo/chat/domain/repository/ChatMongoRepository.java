package com.bangjwo.chat.domain.repository;

import java.util.List;

import com.bangjwo.chat.application.dto.ChatMessageResponseDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bangjwo.chat.domain.entity.ChatMessage;

public interface ChatMongoRepository extends MongoRepository<ChatMessage, String> {

	List<ChatMessageResponseDto> findByChatRoomIdOrderBySendAtAsc(Long chatRoomId);
}
