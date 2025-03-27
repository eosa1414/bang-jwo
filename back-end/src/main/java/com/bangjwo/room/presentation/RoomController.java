package com.bangjwo.room.presentation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.room.application.dto.request.CreateRoomRequestDto;
import com.bangjwo.room.application.dto.request.UpdateRoomMemoRequestDto;
import com.bangjwo.room.application.dto.request.UpdateRoomRequestDto;
import com.bangjwo.room.application.dto.response.IsRoomLikedResponseDto;
import com.bangjwo.room.application.dto.response.RoomListResponseDto;
import com.bangjwo.room.application.dto.response.SearchRoomMemoResponseDto;
import com.bangjwo.room.application.dto.response.SearchRoomResponseDto;
import com.bangjwo.room.application.service.RoomService;
import com.bangjwo.room.domain.vo.RoomAreaType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {
	private final RoomService roomService;

	@GetMapping
	public ResponseEntity<RoomListResponseDto> searchRooms(
		@RequestParam(required = false) Integer price,
		@RequestParam(required = false) List<RoomAreaType> areaTypes,
		@RequestParam BigDecimal lat,
		@RequestParam BigDecimal lng,
		@RequestParam(required = false) Integer zoom,
		@RequestParam(required = false) Integer page,
		@RequestBody Map<String, Long> body) {
		Long memberId = body.get("memberId");    // 이후 로그인 토큰의 memberId로 로직 확인하도록 변경 예정)
		RoomListResponseDto response = roomService.searchRooms(price, areaTypes, lat, lng, zoom, page, memberId);

		return ResponseEntity.ok(response);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> createRoom(@ModelAttribute CreateRoomRequestDto requestDto) {
		roomService.createRoom(requestDto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping(value = "/{roomId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> updateRoom(@PathVariable Long roomId, @ModelAttribute UpdateRoomRequestDto requestDto) {
		roomService.updateRoom(roomId, requestDto);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{roomId}")
	public ResponseEntity<SearchRoomResponseDto> searchRoomDetail(@PathVariable Long roomId,
		@RequestBody Map<String, Long> body) {
		Long memberId = body.get("memberId");    // 이후 로그인 토큰의 memberId로 로직 확인하도록 변경 예정
		var searchRoom = roomService.searchRoomDetail(roomId, memberId);

		return ResponseEntity.ok(searchRoom);
	}

	@DeleteMapping("/{roomId}")
	public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
		roomService.deleteRoom(roomId);    // 이후 로그인 토큰과 roomId 조회 후 다르면 유효성 검사 예외 출력 예정

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{roomId}/memo")
	public ResponseEntity<SearchRoomMemoResponseDto> searchMemo(@PathVariable Long roomId,
		@RequestBody Map<String, Long> body) {
		Long memberId = body.get("memberId");    // 이후 로그인 토큰의 memberId로 로직 확인하도록 변경 예정
		var response = roomService.searchRoomMemo(roomId, memberId);

		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{roomId}/memo")
	public ResponseEntity<Void> updateMemo(@PathVariable Long roomId,
		@RequestBody UpdateRoomMemoRequestDto requestDto) {
		roomService.updateRoomMemo(roomId, requestDto);    // 이후 로그인 토큰의 memberId로 로직 확인하도록 변경 예정

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{roomId}/memo")
	public ResponseEntity<Void> clearMemo(@PathVariable Long roomId, @RequestBody Map<String, Long> body) {
		Long memberId = body.get("memberId");    // 이후 로그인 토큰의 memberId로 로직 확인하도록 변경 예정
		roomService.clearMemo(roomId, memberId);

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{roomId}/like")
	public ResponseEntity<IsRoomLikedResponseDto> toggleLike(@PathVariable Long roomId,
		@RequestBody Map<String, Long> body) {
		Long memberId = body.get("memberId");    // 이후 로그인 토큰의 memberId로 로직 확인하도록 변경 예정
		var isLike = roomService.toggleLike(roomId, memberId);

		return ResponseEntity.ok(isLike);
	}

	@GetMapping("/me")
	public ResponseEntity<RoomListResponseDto> getMyListedRooms(@RequestParam(required = false) Integer page,
		@RequestParam(required = false) Integer size,
		@RequestBody Map<String, Long> body) {
		Long memberId = body.get("memberId");    // 이후 로그인 토큰의 memberId로 로직 확인하도록 변경 예정
		var result = roomService.getMyListedRooms(memberId, page, size);

		return ResponseEntity.ok(result);
	}

	@GetMapping("/like")
	public ResponseEntity<RoomListResponseDto> getLikedRooms(@RequestParam(required = false) Integer page,
		@RequestParam(required = false) Integer size,
		@RequestBody Map<String, Long> body) {
		Long memberId = body.get("memberId");    // 이후 로그인 토큰의 memberId로 로직 확인하도록 변경 예정
		var result = roomService.getLikeRooms(memberId, page, size);

		return ResponseEntity.ok(result);
	}
}
