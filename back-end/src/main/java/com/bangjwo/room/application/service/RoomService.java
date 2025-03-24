package com.bangjwo.room.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.global.common.error.room.RoomErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.global.common.exception.RoomException;
import com.bangjwo.room.application.convert.RoomConverter;
import com.bangjwo.room.application.dto.request.CreateRoomRequestDto;
import com.bangjwo.room.application.dto.request.UpdateRoomRequestDto;
import com.bangjwo.room.application.dto.response.SearchRoomResponseDto;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {
	private final RoomRepository roomRepository;
	private final AddressService addressService;
	private final OptionService optionService;
	private final MaintenanceIncludeService maintenanceIncludeService;
	private final ImageService imageService;

	@Transactional
	public void createRoom(CreateRoomRequestDto requestDto) {
		Room savedRoom = roomRepository.save(RoomConverter.convert(requestDto));

		addressService.createAndSaveAddress(savedRoom, requestDto.getAddress(),
			requestDto.getAddressDetail(), requestDto.getPostalCode());
		optionService.saveOptions(savedRoom, requestDto.getOptions());
		maintenanceIncludeService.saveMaintenanceIncludes(savedRoom, requestDto.getMaintenanceIncludes());
		imageService.uploadAndSaveImages(savedRoom, requestDto.getImages());
	}

	@Transactional
	public void updateRoom(Long roomId, UpdateRoomRequestDto requestDto) {
		Room searchRoom = findByRoomId(roomId);

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
		Room searchRoom = findByRoomId(roomId);

		searchRoom.softDelete();
	}

	@Transactional(readOnly = true)
	public Room findByRoomId(Long roomId) {
		return roomRepository.findByRoomIdAndDeletedAtIsNull(roomId)
			.orElseThrow(() -> new RoomException(RoomErrorCode.NOT_FOUND_SEARCH_ROOM));
	}

	@Transactional(readOnly = true)
	public SearchRoomResponseDto searchRoom(Long roomId) {
		var room = findByRoomId(roomId);
		var address = addressService.findByRoom(room);
		var options = optionService.findByRoom(room);
		var maintenanceIncludes = maintenanceIncludeService.findByRoom(room);
		var images = imageService.findByRoom(room);

		return RoomConverter.convert(room, address, options, maintenanceIncludes, images);
	}
}