package com.bangjwo.room.application.dto.response;

import java.math.BigDecimal;

import com.bangjwo.room.domain.vo.RoomBuildingType;
import com.bangjwo.room.domain.vo.RoomStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "리스트 조회 시 간단한 매물(Room) 정보를 담는 DTO")
public class RoomSummaryResponse {

	@Schema(description = "매물(Room) ID", example = "101")
	private Long roomId;

	@Schema(description = "매물 등록자(회원) ID", example = "100")
	private Long memberId;

	@Schema(description = "좋아요 여부", example = "true")
	private Boolean isLiked;

	@Schema(description = "건물 유형", example = "OFFICETEL")
	private RoomBuildingType buildingType;

	@Schema(description = "매물 상태", example = "ACTIVE")
	private RoomStatus status;

	@Schema(description = "보증금", example = "1000")
	private Integer deposit;

	@Schema(description = "월세", example = "50")
	private Integer monthlyRent;

	@Schema(description = "전용면적", example = "25.4")
	private BigDecimal exclusiveArea;

	@Schema(description = "공급면적", example = "33.0")
	private BigDecimal supplyArea;

	@Schema(description = "관리비", example = "5")
	private Integer maintenanceCost;

	@Schema(description = "층수(문자열)", example = "3")
	private String floor;

	@Schema(description = "대표 이미지 URL", example = "https://example.com/images/1.jpg")
	private String imageUrl;
}
