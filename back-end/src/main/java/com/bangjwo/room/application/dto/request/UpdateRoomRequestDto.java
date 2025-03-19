package com.bangjwo.room.application.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.bangjwo.room.domain.vo.MaintenanceIncludeName;
import com.bangjwo.room.domain.vo.RoomBuildingType;
import com.bangjwo.room.domain.vo.RoomDirection;
import com.bangjwo.room.domain.vo.RoomOption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoomRequestDto {
	private Long roomId;
	private Long memberId;
	private RoomBuildingType buildingType;
	private String realEstateId;
	private String roomNumber;
	private Integer deposit;
	private Integer monthlyRent;
	private BigDecimal exclusiveArea;
	private BigDecimal supplyArea;
	private Integer totalUnits;
	private String floor;
	private Integer maxFloor;
	private Integer parkingSpaces;
	private LocalDate availableFrom;
	private LocalDate permissionDate;
	private String simpleDescription;
	private String description;
	private Integer maintenanceCost;
	private Integer roomCnt;
	private Integer bathroomCnt;
	private RoomDirection direction;
	private List<MaintenanceIncludeName> maintenanceIncludes;
	private List<RoomOption> options;
}