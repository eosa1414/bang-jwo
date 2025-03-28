package com.bangjwo.chat.presentation;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.bangjwo.chat.infrastructure.WebSocketSessionTracker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class StompSessionEventListener {

	private final WebSocketSessionTracker tracker;

	@EventListener
	public void handleConnect(SessionConnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String userId = accessor.getFirstNativeHeader("userId");
		String roomId = accessor.getFirstNativeHeader("roomId");

		if (userId != null && roomId != null) {
			tracker.roomConnect(Long.valueOf(roomId), Long.valueOf(userId));
			log.info("✅ 연결됨: {} in {}", userId, roomId);
		}
	}

	@EventListener
	public void handleDisconnect(SessionDisconnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String userId = accessor.getFirstNativeHeader("userId");
		String roomId = accessor.getFirstNativeHeader("roomId");

		if (userId != null && roomId != null) {
			tracker.roomDisconnect(Long.valueOf(roomId), Long.valueOf(userId));
			log.info("❌ 연결 해제: {} from {}", userId, roomId);
		}
	}
}
