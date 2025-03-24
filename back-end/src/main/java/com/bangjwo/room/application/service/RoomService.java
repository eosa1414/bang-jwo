package com.bangjwo.room.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.room.application.convert.RoomConverter;
import com.bangjwo.room.application.dto.request.CreateRoomRequestDto;
import com.bangjwo.room.application.dto.request.UpdateRoomRequestDto;
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

	public void updateRoom(Long roomId, UpdateRoomRequestDto requestDto) {

	}

}