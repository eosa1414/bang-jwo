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
			.memberId(room.getMemberId())
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
}
