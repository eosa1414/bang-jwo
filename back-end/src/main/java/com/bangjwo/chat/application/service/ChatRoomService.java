package com.bangjwo.chat.application.service;


import com.bangjwo.chat.application.dto.ChatRoomDto;

public interface ChatRoomService {
	ChatRoomDto.ChatResponseDto requestClass(ChatRoomDto.ChatRequestDto chatRequestDto);
}
