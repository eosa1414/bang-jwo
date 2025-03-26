package com.bangjwo.chat.application.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bangjwo.chat.application.dto.ChatRoomDto;
import com.bangjwo.chat.application.dto.ChatRoomListDto;
import com.bangjwo.chat.domain.entity.ChatRoom;
import com.bangjwo.chat.domain.repository.ChatRoomRepository;
import com.bangjwo.chat.infrastructure.ChatRoomMemoryStore;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomServiceImpl implements ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;
	private final ChatRoomMemoryStore chatRoomMemoryStore;

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

		// 인메모리에 저장
		enterRoom(chatRoom.getId(), chatRoom.getTenantId());
		////여기에 채팅방 생성 메세지 전송 로직////
		System.out.println(chatRoom.getRoomId());

		return ChatRoomDto.ResponseDto.builder()
			.landlordId(chatRoom.getLandlordId())
			.tenantId(chatRoom.getTenantId())
			.roomId(chatRoom.getRoomId())
			.chatRoomId(chatRoom.getId()).build();
	}

	@Override
	public ChatRoom enterRoom(Long chatRoomId, Long userId) {
		return chatRoomMemoryStore.get(chatRoomId)
			.orElseGet(() -> {
				ChatRoom room = chatRoomRepository.findByChatRoomIdAndUserId(chatRoomId,userId);
				chatRoomMemoryStore.cache(room);
				return room;
			});
	}

}
