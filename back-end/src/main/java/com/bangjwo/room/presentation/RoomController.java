package com.bangjwo.room.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.room.application.dto.request.CreateRoomRequestDto;
import com.bangjwo.room.application.dto.response.CreateRoomResponseDto;
import com.bangjwo.room.application.service.RoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {
	private final RoomService roomService;

	@PostMapping
	public ResponseEntity<CreateRoomResponseDto> createRoom(@RequestBody CreateRoomRequestDto requestDto) {
		CreateRoomResponseDto responseDto = roomService.createRoom(requestDto);
		
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}
}
