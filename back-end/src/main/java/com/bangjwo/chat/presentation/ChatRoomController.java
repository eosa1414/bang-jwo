package com.bangjwo.chat.presentation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.chat.application.dto.ChatMessageDto;
import com.bangjwo.chat.application.dto.ChatRoomDto;
import com.bangjwo.chat.application.service.ChatMessageService;
import com.bangjwo.chat.application.service.ChatRoomService;
import com.bangjwo.chat.application.service.RedisChatRoomService;
import com.bangjwo.chat.domain.entity.ChatMessage;
import com.bangjwo.chat.domain.entity.ChatRoom;
import com.bangjwo.chat.infrastructure.ChatRoomMemoryStore;
import com.bangjwo.chat.infrastructure.WebSocketSessionTracker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller()
@RequestMapping("/chat")
@Slf4j
@RequiredArgsConstructor
public class ChatRoomController {

	private final ChatRoomService chatroomService;
	private final ChatMessageService chatMessageService;
	private final RedisTemplate<String, String> redisTemplate;
	private final SimpMessagingTemplate messagingTemplate;
	private final ChatRoomService chatRoomService;
	private final WebSocketSessionTracker webSocketSessionTracker;
	private final ChatRoomMemoryStore chatRoomMemoryStore;
	private final RedisChatRoomService redisChatRoomService;

	/*
	* 채팅방 생성
	* */
	@PostMapping("/new-chat")
	public ResponseEntity<ChatRoomDto.ResponseDto> createChatRoom(@RequestBody ChatRoomDto.RequestDto requestDto) {
		/*
		* 채팅방이 생성되면 Redis에 새로 채팅방 추가.
		* */

		/*
		* 채팅방 생성되면 해당 엔드포인트 구독 -> 프론트에서 할 적업
		* 인메모리에 채팅정보 넣기
		* */
		return ResponseEntity.ok().body(chatroomService.requestClass(requestDto));
	}

	/*
	 * 채팅방 입장(채팅 내역 불러오기)
	 * MongoDB에 있는 채팅 중 read false 를 true 로 변경
	 * Redis에 있는 해당 채팅방 정보 업데이트 : 안읽은 메세지 0으로 업데이트
	 * */
	@GetMapping("/messages")
	public ResponseEntity<List<ChatMessageDto>> getChatMessages(
		@RequestParam Long chatRoomId,
		@RequestParam Long userId) {

		// 인메모리에 채팅방 정보 저장
		chatRoomService.enterRoom(chatRoomId, userId);

		// 해당 채팅방 채팅 읽음 처리
		chatMessageService.markMessagesAsRead(chatRoomId, userId);

		return ResponseEntity.ok(chatMessageService.getChatMessages(chatRoomId));
	}

	/*
	 * 채팅 전송
	 * Redis에 있는 해당 채팅방 정보 업데이트
	 * 	- 채팅 내역과 날짜 업데이트 + (상대방이 안들어왔다면(안읽음 +1) || 상대방이 들어와 있다면 안읽음 업데이트X )
	 * MongoDB에 채팅 내역 저장
	 * 상대방이 web-socket에 접속해 있다면 그냥 보내기
	 * web-socket에 접속해 있지 않다면, 알림
	 * */
	@MessageMapping("/message")
	public void sendMessage(ChatMessageDto chatMessageDto) {
		log.info("메시지 수신: {}", chatMessageDto);

		Long chatRoomId = chatMessageDto.chatRoomId();
		Long senderId = chatMessageDto.senderId();
		Long receiverId = chatMessageDto.receiverId();

		// 채팅 상대가 접속중인지 확인
		boolean isReceiverOnline = webSocketSessionTracker
			.isUserOnlineInRoom(chatRoomId, receiverId);

		// Redis에 채팅방 정보 업데이트
		redisChatRoomService.updateLastMessage(
			chatRoomId,
			senderId,
			receiverId,
			chatMessageDto.message(),
			chatMessageDto.sendAt(),
			isReceiverOnline
		);

		// 몽고 DB 채팅 저장
		chatMessageService.saveChatMessage(chatMessageDto);

		if(isReceiverOnline) {
			// 채팅 전송(상대방이 web-socket에 연결되어 있을 때)
			messagingTemplate.convertAndSend(
				"/sub/chat/room/" + chatRoomId, chatMessageDto);
		} else {
			// Todo : 알림
		}
	}

	/*
	* 채팅 리스트 조회 : Redis에 있는 목록 가져오기
	* */



	/*
	* 채팅방 나가기(web-socket 구독 해제)
	* 둘다 웹소켓 구독을 끊으면 인메모리 데이터(방정보) 삭제
	* */
	@PostMapping("/rooms/{roomId}/leave")
	public ResponseEntity<String> leaveChatRoom(
		@PathVariable Long roomId,
		@RequestParam Long userId
	) {
		webSocketSessionTracker.disconnect(roomId, userId);
		log.info("유저({})가 채팅방({})에서 나갔습니다.", userId, roomId);

		// 👇 아무도 없으면 캐시에서 채팅방 제거
		if (!webSocketSessionTracker.isAnyUserOnlineInRoom(roomId)) {
			chatRoomMemoryStore.evict(roomId);
			log.info("채팅방({})에 아무도 없어 인메모리에서 제거됨", roomId);
		}

		return ResponseEntity.ok("채팅방에서 나갔습니다.");
	}

}
