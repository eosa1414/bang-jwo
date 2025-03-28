package com.bangjwo.chat.presentation;

import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bangjwo.chat.application.dto.ChatAlertDto;
import com.bangjwo.chat.application.dto.ChatMessageDto;
import com.bangjwo.chat.application.dto.ChatRoomDto;
import com.bangjwo.chat.application.service.ChatAlertService;
import com.bangjwo.chat.application.service.ChatMessageService;
import com.bangjwo.chat.application.service.ChatRoomService;
import com.bangjwo.chat.application.service.RedisChatRoomService;
import com.bangjwo.chat.domain.entity.ChatAlert;
import com.bangjwo.chat.infrastructure.ChatRoomMemoryStore;
import com.bangjwo.chat.infrastructure.WebSocketSessionTracker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller()
@RequestMapping("/chat")
@Slf4j
@RequiredArgsConstructor
public class ChatRoomController {

	private final ChatRoomService chatroomService;
	private final ChatMessageService chatMessageService;
	private final ChatAlertService chatAlertService;
	private final RedisChatRoomService redisChatRoomService;
	private final SimpMessagingTemplate messagingTemplate;
	private final WebSocketSessionTracker webSocketSessionTracker;
	private final RedisTemplate<String, String> redisTemplate;
	private final ChatRoomMemoryStore chatRoomMemoryStore;


	/*
	* 채팅방 생성
	* 세입자만 채팅방을 생성할 수 있음
	* */
	@PostMapping("/new-chat")
	public ResponseEntity<ChatRoomDto.ResponseDto> createChatRoom(@RequestBody ChatRoomDto.RequestDto requestDto) {

		ChatRoomDto.ResponseDto response = chatroomService.requestClass(requestDto);
		/*
		* 채팅방이 생성되면 Redis에 새로 채팅방 추가.
		* */
		redisChatRoomService.createChatRoom(requestDto);
		/*
		* 채팅방 생성되면 프론트는 해당 엔드포인트 구독
		* */
		return ResponseEntity.ok().body(response);
	}

	/*
	 * 채팅방 입장(채팅 내역 불러오기)
	 * MongoDB에 있는 채팅 중 read false 를 true 로 변경
	 * Redis에 있는 해당 채팅방 정보 업데이트 : 안읽은 메세지 0으로 업데이트
	 * */
	@GetMapping("/{userId}/enter/{chatRoomId}")
	public ResponseEntity<List<ChatMessageDto>> getChatMessages(
		@PathVariable("userId") Long userId,
		@PathVariable("chatRoomId") Long chatRoomId) {

		// 이 로직확인
		// webSocketSessionTracker.roomConnect(chatRoomId, userId);
		// log.info("유저({})가 채팅방({})에 입장했습니다.", userId, chatRoomId);

		// 인메모리에 채팅방 정보 저장
		// chatRoomService.enterRoom(chatRoomId, userId);

		// 몽고 DB에 해당 채팅방 채팅 읽음 처리
		chatMessageService.markMessagesAsRead(chatRoomId, userId);

		// 몽고 DB에 해당 채팅방 알림 읽음 처리
		chatAlertService.markAlertAsRead(chatRoomId, userId);

		// Redis에 해당 채팅방 읽음 처리
		redisChatRoomService.updateChatRoom(chatRoomId, userId);

		return ResponseEntity.ok(chatMessageService.getChatMessages(chatRoomId));
	}

	/*
	 * 채팅 전송
	 * Redis에 있는 해당 채팅방 정보 업데이트
	 * 	- 채팅 내역과 날짜 업데이트 + (상대방이 안들어왔다면(안읽음 +1) || 상대방이 들어와 있다면 안읽음 업데이트X )
	 * MongoDB에 채팅 내역 저장
	 * 상대방이 web-socket에 접속해 있다면 그냥 보내기
	 * web-socket에 접속해 있지 않다면, 알림
	 *
	 * roomId, receiverId, senderNickname은 redis에서 꺼내서 사용하게 끔 변경
	 * ChatMessageDto 수정해야함
	 * */
	@MessageMapping("/message")
	public void sendMessage(ChatMessageDto dto) {
		log.info("메시지 수신: {}", dto);

		Long chatRoomId = dto.chatRoomId();
		Long senderId = dto.senderId();

		// redis에서 꺼내 쓰도록 변경//////////
		Long roomId = dto.roomId();
		Long receiverId = chatroomService.getOtherId(chatRoomId, senderId);
		String senderNickname = dto.senderNickname();
		/// ///////////////////////////////

		// 채팅 상대가 접속중인지 확인
		boolean isReceiverOnline = webSocketSessionTracker
			.isUserOnlineInRoom(chatRoomId, receiverId);

		log.info(String.valueOf(isReceiverOnline));

		// Todo:
		// 나중에 redis 꺼내온 데이터로 재작성
		// reids에서 꺼내는 작업은 서비스에서 진행
		ChatMessageDto chatdto = ChatMessageDto.builder()
			.chatRoomId(dto.chatRoomId())
			.roomId(roomId)
			.receiverId(receiverId)
			.senderId(dto.senderId())
			.senderNickname(senderNickname)
			.message(dto.message())
			.sendAt(dto.sendAt())
			.read(isReceiverOnline).build();

		// Redis에 채팅방 정보 업데이트
		redisChatRoomService.updateLastMessage(chatdto);

		// 몽고 DB 채팅 저장
		chatMessageService.saveChatMessage(chatdto);

		// 채팅 전송
		messagingTemplate.convertAndSend(
			"/sub/chat/room/" + chatRoomId, dto);

		/*
		* 상대방이 구독이 안되어있을 경우 알림 전송
		* 프론트는 회원이 로그인하자마자 웹소켓 구독을 해줘야함
		* "/sub/alert/" + userId
		* */
		if(!isReceiverOnline) {
			/// 레디스에서 꺼내쓸 예정///
			String senderImage = "senderImage";
			/// ////////////////////

			ChatAlert alert = ChatAlert.builder()
				.chatRoomId(dto.chatRoomId())
				.receiverId(dto.receiverId())
				.senderId(dto.senderId())
				.senderImage(senderImage)
				.senderNickname(dto.senderNickname())
				.message(dto.message())
				.sendAt(dto.sendAt()).build();

			boolean isReceiverLogin = webSocketSessionTracker.isUserOnlineInAlert(chatRoomId, receiverId);
			if(isReceiverLogin) {
				messagingTemplate.convertAndSend(
					"/sub/alert/" + receiverId,
					alert
				);
				log.info("알림 전송");
			} else {
				// 몽고디비에 저장
				chatAlertService.saveAlert(alert);
				log.info("몽고디비 알림 저장");
			}
		}
	}

	/*
	* 채팅 리스트 조회 : Redis에 있는 목록 가져오기
	* JSON 값을 파싱하여 사용
	* user값 토큰에서 꺼내는걸로 변경
	* */
	@GetMapping("/{userId}/chat-list")
	public ResponseEntity<Set<ZSetOperations.TypedTuple<String>>> getChatRooms(
		@PathVariable("userId") Long userId
	) {
		return ResponseEntity.ok(redisChatRoomService.getRoomList(userId));
	}

	/*
	* 채팅방 나가기(web-socket 구독 해제)
	* 둘다 웹소켓 구독을 끊으면 인메모리 데이터(방정보) 삭제
	* */
	@PostMapping("/{userId}/leave/{chatRoomId}")
	public ResponseEntity<String> leaveChatRoom(
		@PathVariable("userId") Long userId,
		@PathVariable("chatRoomId") Long chatRoomId
	) {
		webSocketSessionTracker.roomDisconnect(chatRoomId, userId);
		log.info("유저({})가 채팅방({})에서 나갔습니다.", userId, chatRoomId);

		// 아무도 없으면 캐시에서 채팅방 제거
		// if (!webSocketSessionTracker.isAnyUserOnlineInRoom(roomId)) {
		// 	chatRoomMemoryStore.evict(roomId);
		// 	log.info("채팅방({})에 아무도 없어 인메모리에서 제거됨", roomId);
		// }

		return ResponseEntity.ok("채팅방에서 나갔습니다.");
	}

	@GetMapping("/{userId}/alert/{chatRoomId}")
	public ResponseEntity<List<ChatAlert>> getAlerts(
		@PathVariable("userId") Long userId,
		@PathVariable("chatRoomId") Long chatRoomId
	) {
		return ResponseEntity.ok().body(chatAlertService.getAlerts(chatRoomId, userId));
	}

	@PutMapping("/{userId}/alert/{chatRoomId}")
	public ResponseEntity<String> readAlert(
		@PathVariable("userId") Long userId,
		@PathVariable("chatRoomId") Long chatRoomId
	){
		chatAlertService.markAlertAsRead(chatRoomId, userId);
		return ResponseEntity.ok().body("읽음 처리");
	}
}
