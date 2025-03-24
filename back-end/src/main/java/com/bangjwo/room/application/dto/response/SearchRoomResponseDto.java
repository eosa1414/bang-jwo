package com.bangjwo.room.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.bangjwo.room.domain.vo.MaintenanceIncludeName;
import com.bangjwo.room.domain.vo.RoomBuildingType;
import com.bangjwo.room.domain.vo.RoomDirection;
import com.bangjwo.room.domain.vo.RoomOption;
import com.bangjwo.room.domain.vo.RoomStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchRoomResponseDto {
	private Long roomId;
	private Long memberId;
	private RoomStatus roomStatus;
	private RoomBuildingType buildingType;
	private String realEstateId;
	private String postalCode;
	private String address;
	private String addressDetail;
	private BigDecimal lat;
	private BigDecimal lng;
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
	private Boolean discussable;
	private String discussDetail;
	private Boolean reviewable;
	private Boolean isPhonePublic;

	private List<MaintenanceIncludeName> maintenanceIncludes;
	private List<RoomOption> options;
	private List<ImageResponseDto> images;
}

