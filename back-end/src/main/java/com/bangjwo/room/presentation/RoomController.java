package com.bangjwo.room.presentation;

import java.math.BigDecimal;
import java.util.List;

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

import com.bangjwo.auth.resolver.MemberHeader;
import com.bangjwo.room.application.dto.request.CreateRoomRequestDto;
import com.bangjwo.room.application.dto.request.UpdateRoomMemoRequestDto;
import com.bangjwo.room.application.dto.request.UpdateRoomRequestDto;
import com.bangjwo.room.application.dto.request.UpdateRoomStatusDto;
import com.bangjwo.room.application.dto.request.VerifyRoomRequestDto;
import com.bangjwo.room.application.dto.response.IsRoomLikedResponseDto;
import com.bangjwo.room.application.dto.response.RoomListResponseDto;
import com.bangjwo.room.application.dto.response.SearchDetailRoomResponseDto;
import com.bangjwo.room.application.dto.response.SearchRoomMemoResponseDto;
import com.bangjwo.room.application.service.RoomService;
import com.bangjwo.room.domain.vo.RoomAreaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Room", description = "매물 관련 API")
@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {
	private final RoomService roomService;

	@Operation(
		summary = "매물 목록 조회",
		description = "필요한 조건(price, 면적 등)을 만족하는 매물 목록을 조회합니다."
	)
	@ApiResponse(responseCode = "200", description = "정상적으로 매물 목록을 반환했습니다.")
	@GetMapping
	public ResponseEntity<RoomListResponseDto> searchRooms(
		@RequestParam(required = false) Integer price,
		@RequestParam(required = false) List<RoomAreaType> areaTypes,
		@RequestParam BigDecimal lat,
		@RequestParam BigDecimal lng,
		@RequestParam(required = false) Integer zoom,
		@RequestParam(required = false) Integer page,
		@MemberHeader(required = false) Long memberId) {
		RoomListResponseDto response = roomService.searchRooms(price, areaTypes, lat, lng, zoom, page, memberId);

		return ResponseEntity.ok(response);
	}

	@Operation(
		summary = "매물 등록",
		description = "새로운 매물을 등록합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@ApiResponse(responseCode = "201", description = "매물 등록에 성공했습니다.")
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> createRoom(@ModelAttribute CreateRoomRequestDto requestDto,
		@MemberHeader Long memberId) {
		roomService.createRoom(requestDto, memberId);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Operation(
		summary = "매물 수정",
		description = "기존 매물 정보를 수정합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@ApiResponse(responseCode = "204", description = "매물 수정에 성공했습니다.")
	@PatchMapping(value = "/{roomId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> updateRoom(@PathVariable Long roomId, @ModelAttribute UpdateRoomRequestDto requestDto,
		@MemberHeader Long memberId) {
		roomService.updateRoom(roomId, requestDto, memberId);

		return ResponseEntity.noContent().build();
	}

	@Operation(
		summary = "매물 상세 조회",
		description = "선택한 매물의 상세 정보를 조회합니다."
	)
	@ApiResponse(responseCode = "200", description = "정상적으로 매물 상세 정보를 반환했습니다.")
	@GetMapping("/{roomId}")
	public ResponseEntity<SearchDetailRoomResponseDto> searchRoomDetail(@PathVariable Long roomId,
		@MemberHeader(required = false) Long memberId) {
		var searchRoom = roomService.searchRoomDetail(roomId, memberId);

		return ResponseEntity.ok(searchRoom);
	}

	@Operation(
		summary = "매물 삭제",
		description = "선택한 매물을 삭제합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@ApiResponse(responseCode = "204", description = "매물 삭제에 성공했습니다.")
	@DeleteMapping("/{roomId}")
	public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId,
		@MemberHeader Long memberId) {
		roomService.deleteRoom(roomId, memberId);

		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "매물 메모 조회", description = "선택한 매물에 대해 작성된 메모를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "정상적으로 매물 메모를 조회했습니다.")
	@GetMapping("/{roomId}/memo")
	public ResponseEntity<SearchRoomMemoResponseDto> searchMemo(@PathVariable Long roomId,
		@MemberHeader(required = false) Long memberId) {
		var response = roomService.searchRoomMemo(roomId, memberId);

		return ResponseEntity.ok(response);
	}

	@Operation(
		summary = "매물 메모 수정",
		description = "선택한 매물에 대한 메모를 수정합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@ApiResponse(responseCode = "204", description = "매물 메모 수정에 성공했습니다.")
	@PatchMapping("/{roomId}/memo")
	public ResponseEntity<Void> updateMemo(@PathVariable Long roomId,
		@RequestBody UpdateRoomMemoRequestDto requestDto,
		@MemberHeader Long memberId) {
		roomService.updateRoomMemo(roomId, requestDto, memberId);

		return ResponseEntity.noContent().build();
	}

	@Operation(
		summary = "매물 메모 초기화",
		description = "선택한 매물에 대한 메모 내용을 비웁니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@ApiResponse(responseCode = "204", description = "매물 메모 초기화에 성공했습니다.")
	@DeleteMapping("/{roomId}/memo")
	public ResponseEntity<Void> clearMemo(@PathVariable Long roomId,
		@MemberHeader Long memberId) {
		roomService.clearMemo(roomId, memberId);

		return ResponseEntity.noContent().build();
	}

	@Operation(
		summary = "매물 좋아요 토글",
		description = "선택한 매물에 대한 좋아요 상태를 토글합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@ApiResponse(responseCode = "200", description = "정상적으로 좋아요 상태를 토글했습니다.")
	@PostMapping("/{roomId}/like")
	public ResponseEntity<IsRoomLikedResponseDto> toggleLike(@PathVariable Long roomId,
		@MemberHeader Long memberId) {
		var isLike = roomService.toggleLike(roomId, memberId);

		return ResponseEntity.ok(isLike);
	}

	@Operation(
		summary = "내 매물 목록 조회",
		description = "로그인된 사용자가 등록한 매물 목록을 조회합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@ApiResponse(responseCode = "200", description = "정상적으로 내 매물 목록을 반환했습니다.")
	@GetMapping("/me")
	public ResponseEntity<RoomListResponseDto> getMyListedRooms(@RequestParam(required = false) Integer page,
		@RequestParam(required = false) Integer size,
		@MemberHeader Long memberId) {
		var result = roomService.getMyListedRooms(memberId, page, size);

		return ResponseEntity.ok(result);
	}

	@Operation(
		summary = "좋아요한 매물 목록 조회",
		description = "로그인된 사용자가 좋아요한 매물 목록을 조회합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@ApiResponse(responseCode = "200", description = "정상적으로 좋아요한 매물 목록을 반환했습니다.")
	@GetMapping("/like")
	public ResponseEntity<RoomListResponseDto> getLikedRooms(@RequestParam(required = false) Integer page,
		@RequestParam(required = false) Integer size,
		@MemberHeader Long memberId) {
		var result = roomService.getLikeRooms(memberId, page, size);

		return ResponseEntity.ok(result);
	}

	@Operation(summary = "매물 임시 상태 변경", description = "개발 용도로 매물 상태를 임시 변경. 이후 삭제 예정.")
	@ApiResponse(responseCode = "200", description = "정상적으로 매물 상태를 변경하였습니다.")
	@PatchMapping("/status")
	public ResponseEntity<Void> updateRoomStatus(@RequestBody UpdateRoomStatusDto requestDto) {
		roomService.updateRoomStatus(requestDto);

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{roomId}/verify")
	@Operation(summary = "매물 본인 인증", description = "본인인증 성공 시 해당 매물을 verified 처리합니다.",
		security = @SecurityRequirement(name = "JWT"))
	public ResponseEntity<Void> verifyRoom(
		@MemberHeader Long memberId,
		@RequestBody VerifyRoomRequestDto dto
	) {
		roomService.verifyRoom(memberId, dto);

		return ResponseEntity.ok().build();
	}

	@Operation(
		summary = "매물 상태 판매중으로 전환",
		description = "verified와 registryPaid가 모두 true인 경우, 매물 상태를 판매중(ON_SALE)으로 변경합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@PatchMapping("/{roomId}/status/on-sale")
	public ResponseEntity<Void> publishRoom(
		@PathVariable Long roomId,
		@MemberHeader Long memberId
	) {
		roomService.publishRoom(roomId, memberId);
		
		return ResponseEntity.ok().build();
	}
}
