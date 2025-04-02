package com.bangjwo.chat.application.convert;

import com.bangjwo.chat.application.dto.ChatMessageDto;
import com.bangjwo.chat.application.dto.ChatRoomDto;
import com.bangjwo.chat.application.dto.ChatRoomSummary;
import com.bangjwo.chat.domain.entity.ChatMessage;

public class ChatCoverter {

	public static ChatMessage create(ChatMessageDto dto, ChatRoomSummary summary, boolean isReceiverOnline) {
		return ChatMessage.builder()
			.chatRoomId(dto.chatRoomId())
			.roomId(summary.getRoomId())
			.receiverId(summary.getOtherId())
			.senderId(dto.senderId())
			.senderNickname(summary.getNickname())
			.message(dto.message())
			.sendAt(dto.sendAt())
			.read(isReceiverOnline)
			.build();
	}

	public static ChatRoomSummary createSenderSummary(
		Long chatRoomId,
		Long roomId,
		String lastMessage,
		Long receiverId,
		String receiverImage,
		String receiverNickname,
		String sendAt) {

		return ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(roomId)
			.lastMessage(lastMessage)
			.otherId(receiverId)
			.profileImage(receiverImage)
			.nickname(receiverNickname)
			.sendAt(sendAt)
			.unreadCount(0)
			.build();
	}

	/**
	 * 집주인(수신자)를 위한 채팅방 요약 생성 (읽지 않은 메시지 1)
	 */
	public static ChatRoomSummary createReceiverSummary(
		Long chatRoomId,
		Long roomId,
		String lastMessage,
		Long senderId,
		String senderImage,
		String senderNickname,
		String sendAt) {

		return ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(roomId)
			.lastMessage(lastMessage)
			.otherId(senderId)
			.profileImage(senderImage)
			.nickname(senderNickname)
			.sendAt(sendAt)
			.unreadCount(1)
			.build();
	}

	public static ChatRoomSummary updateAsRead(ChatRoomSummary original) {
		return ChatRoomSummary.builder()
			.chatRoomId(original.getChatRoomId())
			.roomId(original.getRoomId())
			.lastMessage(original.getLastMessage())
			.otherId(original.getOtherId())
			.profileImage(original.getProfileImage())
			.nickname(original.getNickname())
			.sendAt(original.getSendAt())
			.unreadCount(0)
			.build();
	}


	public static ChatRoomSummary updateSenderSummary(
		Long chatRoomId,
		ChatRoomSummary existing,
		String message,
		String sendAt) {

		return ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(existing.getRoomId())
			.lastMessage(message)
			.otherId(existing.getOtherId())
			.profileImage(existing.getProfileImage())
			.nickname(existing.getNickname())
			.sendAt(sendAt)
			.unreadCount(existing.getUnreadCount()) // sender는 unread count 유지
			.build();
	}


	public static ChatRoomSummary updateReceiverSummary(
		Long chatRoomId,
		ChatRoomSummary existing,
		String message,
		String sendAt,
		boolean isReceiverOnline) {

		return ChatRoomSummary.builder()
			.chatRoomId(chatRoomId)
			.roomId(existing.getRoomId())
			.lastMessage(message)
			.otherId(existing.getOtherId())
			.profileImage(existing.getProfileImage())
			.nickname(existing.getNickname())
			.sendAt(sendAt)
			.unreadCount(isReceiverOnline ? 0 : existing.getUnreadCount() + 1)
			.build();
	}

}
