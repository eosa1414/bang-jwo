package com.bangjwo.room.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.room.application.dto.request.CreateRoomRequestDto;
import com.bangjwo.room.application.dto.request.UpdateRoomRequestDto;
import com.bangjwo.room.application.dto.response.SearchRoomResponseDto;
import com.bangjwo.room.application.service.RoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {
	private final RoomService roomService;

	@GetMapping("/{roomId}")
	public ResponseEntity<SearchRoomResponseDto> searchRoomDetail(@PathVariable Long roomId) {
		var searchRoom = roomService.searchRoom(roomId);

		return new ResponseEntity<>(searchRoom, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> createRoom(@ModelAttribute CreateRoomRequestDto requestDto) {
		roomService.createRoom(requestDto);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping(value = "/{roomId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> updateRoom(@PathVariable Long roomId, @ModelAttribute UpdateRoomRequestDto requestDto) {
		roomService.updateRoom(roomId, requestDto);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{roomId}")
	public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
		roomService.deleteRoom(roomId);    // 이후 로그인 토큰과 roomId 조회 후 다르면 유효성 검사 예외 출력 예정

		return ResponseEntity.noContent().build();
	}
}
