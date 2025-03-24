package com.bangjwo.room.application.convert;

import com.bangjwo.room.application.dto.request.CreateRoomRequestDto;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.vo.RoomStatus;

import lombok.Builder;

public class RoomConverter {

	@Builder
	public static Room convert(CreateRoomRequestDto requestDto) {
		return Room.builder()
			.memberId(requestDto.getMemberId())
			.buildingType(requestDto.getBuildingType())
			.status(RoomStatus.UNDER_VERIFICATION)
			.realEstateId(requestDto.getRealEstateId())
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
			// 신규 추가 필드
			.discussable(requestDto.getDiscussable())
			.discussDetail(requestDto.getDiscussDetail())
			.reviewable(requestDto.getReviewable())
			.isPhonePublic(requestDto.getIsPhonePublic())
			// 기본값 설정
			.verified(false)
			.registryPaid(false)
			.build();
	}
}
