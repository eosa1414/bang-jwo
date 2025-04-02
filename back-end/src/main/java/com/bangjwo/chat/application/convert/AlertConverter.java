package com.bangjwo.chat.application.convert;

import java.time.LocalDateTime;

import com.bangjwo.chat.application.dto.ChatMessageDto;
import com.bangjwo.chat.domain.entity.ChatAlert;

public class AlertConverter {
	public static ChatAlert createAlert(ChatMessageDto dto, Long receiverId,String senderImage) {
		return ChatAlert.builder()
			.chatRoomId(dto.chatRoomId())
			.receiverId(receiverId)  // 또는 receiverId가 따로 있다면 그 값을 사용
			.senderId(dto.senderId())
			.senderImage(senderImage)
			.senderNickname(dto.senderNickname())
			.message(dto.message())
			.sendAt(dto.sendAt())
			.build();
	}
}
