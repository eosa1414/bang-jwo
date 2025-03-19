package com.bangjwo.room.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.room.application.convert.RoomConverter;
import com.bangjwo.room.application.dto.request.CreateRoomRequestDto;
import com.bangjwo.room.application.dto.request.UpdateRoomRequestDto;
import com.bangjwo.room.application.dto.response.CreateRoomResponseDto;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {
	private final RoomRepository roomRepository;

	public CreateRoomResponseDto createRoom(CreateRoomRequestDto requestDto) {
		Room room = RoomConverter.convert(requestDto);
		Room savedRoom = roomRepository.save(room);

		return RoomConverter.from(savedRoom);
	}

	public void updateRoom(Long roomId, UpdateRoomRequestDto requestDto) {

	}

}