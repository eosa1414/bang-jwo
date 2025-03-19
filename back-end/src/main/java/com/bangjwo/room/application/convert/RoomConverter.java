package com.bangjwo.room.application.convert;

import java.util.Optional;

import com.bangjwo.room.application.dto.request.CreateRoomRequestDto;
import com.bangjwo.room.application.dto.response.CreateRoomResponseDto;
import com.bangjwo.room.domain.entity.MaintenanceInclude;
import com.bangjwo.room.domain.entity.Options;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.vo.RoomStatus;

import lombok.Builder;

public class RoomConverter {

	@Builder
	public static Room convert(CreateRoomRequestDto requestDto) {
		Room room = Room.builder()
			.memberId(requestDto.getMemberId())
			.buildingType(requestDto.getBuildingType())
			.status(RoomStatus.UNDER_VERIFICATION)
			.realEstateId(requestDto.getRealEstateId())
			.roomNumber(requestDto.getRoomNumber())
			.deposit(requestDto.getDeposit())
			.monthlyRent(requestDto.getMonthlyRent())
			.exclusiveArea(requestDto.getExclusiveArea())
			.supplyArea(requestDto.getSupplyArea())
			.totalUnits(requestDto.getTotalUnits())
			.floor(requestDto.getFloor())
			.maxFloor(requestDto.getMaxFloor())
			.parkingSpaces(requestDto.getParkingSpaces())
			.availableFrom(requestDto.getAvailableFrom())
			.permissionDate(requestDto.getPermissionDate())
			.simpleDescription(requestDto.getSimpleDescription())
			.description(requestDto.getDescription())
			.maintenanceCost(requestDto.getMaintenanceCost())
			.roomCnt(requestDto.getRoomCnt())
			.bathroomCnt(requestDto.getBathroomCnt())
			.direction(requestDto.getDirection())
			.verified(false)
			.registryPaid(false)
			.build();

		Optional.ofNullable(requestDto.getMaintenanceIncludes()).ifPresent(list -> {
			list.forEach(includeName -> {
				MaintenanceInclude mi = MaintenanceInclude.builder()
					.maintenanceIncludeName(includeName)
					.build();
				room.addMaintenanceInclude(mi);
			});
		});

		Optional.ofNullable(requestDto.getOptions()).ifPresent(list -> {
			list.forEach(option -> {
				Options optionEntity = Options.builder()
					.optionName(option)
					.build();
				room.addRoomOption(optionEntity);
			});
		});

		return room;
	}

	@Builder
	public static CreateRoomResponseDto from(Room room) {
		return CreateRoomResponseDto.builder()
			.buildingType(room.getBuildingType())
			.status(room.getStatus())
			.realEstateId(room.getRealEstateId())
			.roomNumber(room.getRoomNumber())
			.deposit(room.getDeposit())
			.monthlyRent(room.getMonthlyRent())
			.exclusiveArea(room.getExclusiveArea())
			.supplyArea(room.getSupplyArea())
			.totalUnits(room.getTotalUnits())
			.floor(room.getFloor())
			.maxFloor(room.getMaxFloor())
			.parkingSpaces(room.getParkingSpaces())
			.availableFrom(room.getAvailableFrom())
			.permissionDate(room.getPermissionDate())
			.simpleDescription(room.getSimpleDescription())
			.description(room.getDescription())
			.maintenanceCost(room.getMaintenanceCost())
			.roomCnt(room.getRoomCnt())
			.bathroomCnt(room.getBathroomCnt())
			.direction(room.getDirection())
			.verified(room.getVerified())
			.registryPaid(room.getRegistryPaid())

			.maintenanceIncludes(
				room.getMaintenanceIncludes().stream()
					.map(MaintenanceInclude::getMaintenanceIncludeName)
					.toList()
			)
			.options(
				room.getRoomOptions().stream()
					.map(Options::getOptionName)
					.toList()
			)
			.build();
	}

	// @Builder
	// public static void update(Room existingRoom, UpdateRoomRequestDto requestDto) {
	// 	existingRoom.setMemberId(requestDto.getMemberId());
	// 	existingRoom.setBuildingType(requestDto.getBuildingType());
	// 	existingRoom.setRealEstateId(requestDto.getRealEstateId());
	// 	existingRoom.setRoomNumber(requestDto.getRoomNumber());
	// 	existingRoom.setDeposit(requestDto.getDeposit());
	// 	existingRoom.setMonthlyRent(requestDto.getMonthlyRent());
	// 	existingRoom.setExclusiveArea(requestDto.getExclusiveArea());
	// 	existingRoom.setSupplyArea(requestDto.getSupplyArea());
	// 	existingRoom.setTotalUnits(requestDto.getTotalUnits());
	// 	existingRoom.setFloor(requestDto.getFloor());
	// 	existingRoom.setMaxFloor(requestDto.getMaxFloor());
	// 	existingRoom.setParkingSpaces(requestDto.getParkingSpaces());
	// 	existingRoom.setAvailableFrom(requestDto.getAvailableFrom());
	// 	existingRoom.setPermissionDate(requestDto.getPermissionDate());
	// 	existingRoom.setSimpleDescription(requestDto.getSimpleDescription());
	// 	existingRoom.setDescription(requestDto.getDescription());
	// 	existingRoom.setMaintenanceCost(requestDto.getMaintenanceCost());
	// 	existingRoom.setRoomCnt(requestDto.getRoomCnt());
	// 	existingRoom.setBathroomCnt(requestDto.getBathroomCnt());
	// 	existingRoom.setDirection(requestDto.getDirection());
	// 	// verified, registryPaid 등은 상황에 따라 그대로 두거나 변경
	//
	// 	// 2) 연관된 자식 엔티티(maintenanceIncludes, options) 초기화 후 재생성
	// 	//    (기존 목록을 clear하고, 새로 add)
	// 	existingRoom.getMaintenanceIncludes().clear();
	// 	Optional.ofNullable(requestDto.getMaintenanceIncludes()).ifPresent(list -> {
	// 		list.forEach(name -> {
	// 			MaintenanceInclude mi = MaintenanceInclude.builder()
	// 				.maintenanceIncludeName(name)
	// 				.build();
	// 			existingRoom.addMaintenanceInclude(mi);
	// 		});
	// 	});
	//
	// 	existingRoom.getRoomOptions().clear();
	// 	Optional.ofNullable(requestDto.getOptions()).ifPresent(list -> {
	// 		list.forEach(opt -> {
	// 			Options optionEntity = Options.builder()
	// 				.optionName(opt)
	// 				.build();
	// 			existingRoom.addRoomOption(optionEntity);
	// 		});
	// 	});
	// }
}
