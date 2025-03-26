package com.bangjwo.chat.application.service;


import com.bangjwo.chat.application.dto.ChatRoomDto;
import com.bangjwo.chat.domain.entity.ChatRoom;

public interface ChatRoomService {
	ChatRoomDto.ResponseDto requestClass(ChatRoomDto.RequestDto requestDto);

	ChatRoom enterRoom(Long chatRoomId, Long userId);
}
