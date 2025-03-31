package com.bangjwo.chat.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bangjwo.chat.application.dto.ChatRoomDto;
import com.bangjwo.chat.domain.entity.ChatRoom;
import com.bangjwo.chat.domain.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomServiceImpl implements ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;

	@Override
	public ChatRoomDto.ResponseDto requestClass(ChatRoomDto.RequestDto requestDto) {

		ChatRoom chatRoom;
		Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findByRoomIdAndTenantId(requestDto.roomId(), requestDto.tenantId());

		chatRoom = chatRoomOptional.orElseGet(() -> chatRoomRepository.saveAndFlush(ChatRoom.builder()
			.landlordId(requestDto.landlordId())
			.tenantId(requestDto.tenantId())
			.roomId(requestDto.roomId())
			.landloadUnreadCount(0L)
			.tenantUnreadCount(0L)
			.createdAt(LocalDateTime.now()).build()));

		return ChatRoomDto.ResponseDto.builder()
			.landlordId(chatRoom.getLandlordId())
			.tenantId(chatRoom.getTenantId())
			.roomId(chatRoom.getRoomId())
			.chatRoomId(chatRoom.getId()).build();
	}

}
