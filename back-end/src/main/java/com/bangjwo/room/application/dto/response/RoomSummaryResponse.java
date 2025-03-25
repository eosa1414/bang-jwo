package com.bangjwo.room.application.dto.response;

import java.math.BigDecimal;

import com.bangjwo.room.domain.vo.RoomBuildingType;
import com.bangjwo.room.domain.vo.RoomStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RoomSummaryResponse {
	private Long roomId;
	private Long memberId;
	private Boolean isLiked;
	private RoomBuildingType buildingType;
	private RoomStatus status;
	private Integer deposit;
	private Integer monthlyRent;
	private BigDecimal exclusiveArea;
	private BigDecimal supplyArea;
	private Integer maintenanceCost;
	private String floor;
	private String imageUrl;
}
