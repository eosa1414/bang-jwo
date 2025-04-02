package com.bangjwo.chat.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bangjwo.chat.application.service.ChatAlertService;
import com.bangjwo.chat.domain.entity.ChatAlert;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Alert", description = "알림 관련 API")
@Controller()
@RequestMapping("/api/v1/alert")
@Slf4j
@RequiredArgsConstructor
public class AlertController {

	private final ChatAlertService chatAlertService;

	/*
	 * 알림 가져오기
	 * */
	@Operation(
		summary = "알림 조회",
		description = "해당 유저의 채팅 알림을 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "알림 조회 성공"),
			@ApiResponse(responseCode = "400", description = "요청 데이터 오류")
		}
	)
	@GetMapping("/alert/{userId}")
	public ResponseEntity<List<ChatAlert>> getAlerts(
		@PathVariable("userId") Long userId) {

		return ResponseEntity.ok().body(chatAlertService.getAlerts(userId));
	}

	/*
	 * 알림 읽음 처리
	 * */
	@Operation(
		summary = "알림 읽음 처리",
		description = "해당 채팅방의 알림을 읽음 처리합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "알림 읽음 처리 성공"),
			@ApiResponse(responseCode = "400", description = "요청 데이터 오류")
		}
	)
	@PutMapping("/{userId}/alert/{chatRoomId}")
	public ResponseEntity<String> readAlert(
		@PathVariable("userId") Long userId,
		@PathVariable("chatRoomId") Long chatRoomId){
		chatAlertService.markAlertAsRead(chatRoomId, userId);

		return ResponseEntity.ok().body("읽음 처리");
	}

}
