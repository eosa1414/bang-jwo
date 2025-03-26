package com.bangjwo.room.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.global.common.error.room.RoomErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.global.common.exception.RoomException;
import com.bangjwo.global.common.page.PaginationRequest;
import com.bangjwo.room.application.convert.RoomConverter;
import com.bangjwo.room.application.dto.request.CreateRoomRequestDto;
import com.bangjwo.room.application.dto.request.UpdateRoomMemoRequestDto;
import com.bangjwo.room.application.dto.request.UpdateRoomRequestDto;
import com.bangjwo.room.application.dto.response.IsRoomLikedResponseDto;
import com.bangjwo.room.application.dto.response.RoomListResponseDto;
import com.bangjwo.room.application.dto.response.RoomSummaryResponse;
import com.bangjwo.room.application.dto.response.SearchRoomMemoResponseDto;
import com.bangjwo.room.application.dto.response.SearchRoomResponseDto;
import com.bangjwo.room.domain.entity.Likes;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.repository.LikeRepository;
import com.bangjwo.room.domain.repository.MemoRepository;
import com.bangjwo.room.domain.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {
	private final RoomRepository roomRepository;
	private final MemoRepository memoRepository;
	private final LikeRepository likeRepository;
	private final AddressService addressService;
	private final OptionService optionService;
	private final MaintenanceIncludeService maintenanceIncludeService;
	private final ImageService imageService;

	@Transactional
	public void createRoom(CreateRoomRequestDto requestDto) {
		var savedRoom = roomRepository.save(RoomConverter.convert(requestDto));

		addressService.createAndSaveAddress(savedRoom, requestDto.getAddress(),
			requestDto.getAddressDetail(), requestDto.getPostalCode());
		optionService.saveOptions(savedRoom, requestDto.getOptions());
		maintenanceIncludeService.saveMaintenanceIncludes(savedRoom, requestDto.getMaintenanceIncludes());
		imageService.uploadAndSaveImages(savedRoom, requestDto.getImages());
	}

	@Transactional
	public void updateRoom(Long roomId, UpdateRoomRequestDto requestDto) {
		var searchRoom = findRoom(roomId);

		if (!searchRoom.getMemberId().equals(requestDto.getMemberId())) {
			throw new BusinessException(RoomErrorCode.NO_AUTH_TO_UPDATE_ROOM);
		}

		searchRoom.updateRoom(requestDto);
		optionService.updateOptions(searchRoom, requestDto.getOptions());
		maintenanceIncludeService.updateMaintenanceIncludes(searchRoom, requestDto.getMaintenanceIncludes());
		imageService.updateImages(searchRoom, requestDto.getDeleteImageIds(), requestDto.getImages());
	}

	@Transactional
	public void deleteRoom(Long roomId) {
		var searchRoom = findRoom(roomId);

		searchRoom.softDelete();
	}

	@Transactional(readOnly = true)
	public Room findRoom(Long roomId) {
		return roomRepository.findByRoomIdAndDeletedAtIsNull(roomId)
			.orElseThrow(() -> new RoomException(RoomErrorCode.NOT_FOUND_SEARCH_ROOM));
	}

	@Transactional(readOnly = true)
	public SearchRoomResponseDto searchRoom(Long roomId, Long memberId) {
		var room = findRoom(roomId);
		var address = addressService.findByRoom(room);
		var options = optionService.findByRoom(room);
		var maintenanceIncludes = maintenanceIncludeService.findByRoom(room);
		var images = imageService.findByRoom(room);
		var isLiked = likeRepository.findByRoomIdAndMemberId(roomId, memberId)
			.map(Likes::getFlag)
			.orElse(false);

		return RoomConverter.convert(room, isLiked, address, options, maintenanceIncludes, images);
	}

	@Transactional(readOnly = true)
	public SearchRoomMemoResponseDto searchRoomMemo(Long roomId, Long memberId) {
		return memoRepository.findByRoomIdAndMemberId(roomId, memberId)
			.map(RoomConverter::convert)
			.orElse(new SearchRoomMemoResponseDto(roomId, ""));    // 현재 생성된 메모가 없다면 빈 문자열 응답
	}

	@Transactional
	public void updateRoomMemo(Long roomId, UpdateRoomMemoRequestDto requestDto) {
		var memo = memoRepository.findByRoomIdAndMemberId(roomId, requestDto.getMemberId());

		if (memo.isPresent()) {
			memo.get().updateContent(requestDto.getContent());
		} else {
			createRoomMemo(roomId, requestDto);
		}
	}

	@Transactional
	public void createRoomMemo(Long roomId, UpdateRoomMemoRequestDto requestDto) {
		memoRepository.save(RoomConverter.convert(roomId, requestDto));
	}

	@Transactional
	public void clearMemo(Long roomId, Long memberId) {
		memoRepository.findByRoomIdAndMemberId(roomId, memberId)
			.ifPresent(memo -> memo.updateContent(""));
	}

	@Transactional
	public IsRoomLikedResponseDto toggleLike(Long roomId, Long memberId) {
		var roomLike = likeRepository.findByRoomIdAndMemberId(roomId, memberId)
			.map(like -> {
				like.toggleFlag();
				return like;
			})
			.orElseGet(() -> likeRepository.save(RoomConverter.convertLike(roomId, memberId)));

		return RoomConverter.convert(roomLike);
	}

	@Transactional(readOnly = true)
	public RoomListResponseDto getMyListedRooms(Long memberId, Integer page, Integer size) {
		var pageable = PaginationRequest.toPageable(page, size);
		var roomPage = roomRepository.findAllByMemberId(memberId, pageable);

		return createRoomListResponseDto(roomPage, memberId, page, size);
	}

	@Transactional(readOnly = true)
	public RoomListResponseDto getLikeRooms(Long memberId, Integer page, Integer size) {
		var pageable = PaginationRequest.toPageable(page, size);
		var likePages = likeRepository.findAllByMemberIdAndFlagTrue(memberId, pageable);
		int totalItems = (int)likePages.getTotalElements();

		List<Long> roomIds = likePages.getContent().stream()
			.map(Likes::getRoomId)
			.toList();

		List<Room> rooms = roomRepository.findByRoomIdIn(roomIds);

		List<RoomSummaryResponse> roomSummaryList = rooms.stream()
			.map(room -> {
				String imageUrl = imageService.findMainImageByRoom(room).getImageUrl();

				return RoomConverter.convertToRoomSummary(room, true, imageUrl);
			})
			.toList();

		return new RoomListResponseDto(totalItems, page, size, roomSummaryList);
	}

	private RoomListResponseDto createRoomListResponseDto(Page<Room> roomPage, Long memberId, Integer page,
		Integer size) {
		var paginatedRooms = roomPage.getContent();
		int totalItems = (int)roomPage.getTotalElements();

		var roomSummaryList = paginatedRooms.stream()
			.map(room -> {
				boolean like = likeRepository.findByRoomIdAndMemberId(room.getRoomId(), memberId)
					.map(Likes::getFlag)
					.orElse(false);
				String imageUrl = imageService.findMainImageByRoom(room).getImageUrl();

				return RoomConverter.convertToRoomSummary(room, like, imageUrl);
			})
			.toList();

		return new RoomListResponseDto(totalItems, page, size, roomSummaryList);
	}

}