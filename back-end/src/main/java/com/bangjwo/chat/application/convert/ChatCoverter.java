package com.bangjwo.chat.application.convert;

import com.bangjwo.chat.application.dto.ChatMessageDto;
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
			Long chatRoomId, Long roomId, String message,
			Long otherId, String otherImage, String otherNickname, String sendAt,
			int deposit, int monthly, String role, String roomImage
	) {
		return ChatRoomSummary.builder()
				.chatRoomId(chatRoomId)
				.roomId(roomId)
				.lastMessage(message)
				.profileImage(otherImage)
				.nickname(otherNickname)
				.sendAt(sendAt)
				.unreadCount(0)
				.deposit(deposit)
				.monthly(monthly)
				.otherId(otherId)
				.role(role)
				.roomImage(roomImage)
				.build();
	}


	/**
	 * 집주인(수신자)를 위한 채팅방 요약 생성 (읽지 않은 메시지 1)
	 */
	public static ChatRoomSummary createReceiverSummary(
			Long chatRoomId, Long roomId, String message,
			Long otherId, String otherImage, String otherNickname, String sendAt,
			int deposit, int monthly, String role, String roomImage
	) {
		return ChatRoomSummary.builder()
				.chatRoomId(chatRoomId)
				.roomId(roomId)
				.lastMessage(message)
				.profileImage(otherImage)
				.nickname(otherNickname)
				.sendAt(sendAt)
				.unreadCount(1) // 최초 메시지는 읽지 않음 상태
				.otherId(otherId)
				.role(role)
				.deposit(deposit)
				.monthly(monthly)
				.roomImage(roomImage)
				.build();
	}

	public static ChatRoomSummary updateAsRead(ChatRoomSummary existing) {
		return ChatRoomSummary.builder()
				.chatRoomId(existing.getChatRoomId())
				.roomId(existing.getRoomId())
				.lastMessage(existing.getLastMessage())
				.profileImage(existing.getProfileImage())
				.nickname(existing.getNickname())
				.sendAt(existing.getSendAt())
				.unreadCount(0)
				.otherId(existing.getOtherId())
				.role(existing.getRole())
				.deposit(existing.getDeposit())
				.monthly(existing.getMonthly())
				.build();
	}


	public static ChatRoomSummary updateSenderSummary(
			Long chatRoomId, ChatRoomSummary existing, String message, String sendAt
	) {
		return ChatRoomSummary.builder()
				.chatRoomId(chatRoomId)
				.roomId(existing.getRoomId())
				.lastMessage(message)
				.profileImage(existing.getProfileImage())
				.nickname(existing.getNickname())
				.sendAt(sendAt)
				.unreadCount(existing.getUnreadCount()) // 보낸 사람은 unread 그대로
				.otherId(existing.getOtherId())
				.role(existing.getRole())
				.deposit(existing.getDeposit())
				.monthly(existing.getMonthly())
				.build();
	}


	public static ChatRoomSummary updateReceiverSummary(
			Long chatRoomId, ChatRoomSummary existing, String message, String sendAt, boolean isOnline
	) {
		int updatedUnread = isOnline ? existing.getUnreadCount() : existing.getUnreadCount() + 1;

		return ChatRoomSummary.builder()
				.chatRoomId(chatRoomId)
				.roomId(existing.getRoomId())
				.lastMessage(message)
				.profileImage(existing.getProfileImage())
				.nickname(existing.getNickname())
				.sendAt(sendAt)
				.unreadCount(updatedUnread)
				.otherId(existing.getOtherId())
				.role(existing.getRole())
				.deposit(existing.getDeposit())
				.monthly(existing.getMonthly())
				.build();
	}

}
