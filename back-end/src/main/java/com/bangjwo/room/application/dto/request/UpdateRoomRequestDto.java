package com.bangjwo.room.application.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bangjwo.room.domain.vo.MaintenanceIncludeName;
import com.bangjwo.room.domain.vo.RoomDirection;
import com.bangjwo.room.domain.vo.RoomOption;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "매물 정보 수정 요청 DTO")
public class UpdateRoomRequestDto {

	@Schema(description = "보증금", example = "10000000", minimum = "0")
	private Integer deposit;

	@Schema(description = "월세", example = "500000", minimum = "0")
	@Min(0)
	private Integer monthlyRent;

	@Schema(description = "전용면적", example = "300.12")
	@Min(0)
	private BigDecimal exclusiveArea;

	@Schema(description = "공급면적", example = "400.50")
	@Min(0)
	private BigDecimal supplyArea;

	@Schema(description = "세대수", example = "80", minimum = "0")
	@Min(0)
	private Integer totalUnits;

	@Schema(description = "층수(문자열)", example = "5층 or 반지하 or 지하1층")
	private String floor;

	@Schema(description = "최고 층수", example = "20", minimum = "0")
	@Min(0)
	private Integer maxFloor;

	@Schema(description = "주차 가능 대수", example = "100", minimum = "0")
	@Min(0)
	private Integer parkingSpaces;

	@Schema(description = "입주 가능일", example = "2025-04-01")
	private LocalDate availableFrom;

	@Schema(description = "사용 승인일", example = "2024-12-31")
	private LocalDate permissionDate;

	@Schema(description = "간단한 매물 설명", example = "역세권, 풀옵션")
	private String simpleDescription;

	@Schema(description = "매물 상세 설명", example = "지하철 5분 거리, 1층 편의점 연결이 가능하고 SSAFY 가기가 편합니다.")
	private String description;

	@Schema(description = "관리비", example = "50000", minimum = "0")
	private Integer maintenanceCost;

	@Schema(description = "방 개수", example = "1", minimum = "1")
	private Integer roomCnt;

	@Schema(description = "화장실 개수", example = "1", minimum = "0")
	private Integer bathroomCnt;

	@Schema(description = "방 향", example = "SOUTH")
	private RoomDirection direction;

	@Schema(description = "협의 가능 여부", example = "true")
	private Boolean discussable;

	@Schema(description = "협의 상세 정보", example = "보증금 조정 가능")
	private String discussDetail;

	@Schema(description = "리뷰 작성 가능 여부", example = "true")
	private Boolean reviewable;

	@Schema(description = "전화번호 공개 여부", example = "false")
	private Boolean isPhonePublic;

	@Schema(description = "관리비 포함 항목 리스트", example = "[\"INTERNET\", \"GAS\"]")
	private List<MaintenanceIncludeName> maintenanceIncludes;

	@Schema(description = "매물 옵션 리스트", example = "[\"BED\", \"AIR_CONDITIONER\"]")
	private List<RoomOption> options;

	@Schema(description = "매물 이미지 파일 리스트(최소 3개 이상 등록)")
	private List<MultipartFile> images;

	@Schema(description = "삭제할 매물 이미지 ID 리스트", example = "[1, 2]")
	private List<Long> deleteImageIds;
}
