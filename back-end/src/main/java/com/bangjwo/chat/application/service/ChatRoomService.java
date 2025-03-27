package com.bangjwo.chat.application.service;


import com.bangjwo.chat.application.dto.ChatRoomDto;
import com.bangjwo.chat.domain.entity.ChatRoom;

public interface ChatRoomService {
	ChatRoomDto.ResponseDto requestClass(ChatRoomDto.RequestDto requestDto);

	Long getOtherId(Long chatRoomId, Long userId);

	Long getChatRoomId(Long roomId, Long tenantId);

	ChatRoom enterRoom(Long chatRoomId, Long userId);
}
