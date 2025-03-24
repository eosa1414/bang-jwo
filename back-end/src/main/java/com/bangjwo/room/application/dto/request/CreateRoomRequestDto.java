package com.bangjwo.room.application.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bangjwo.room.domain.vo.MaintenanceIncludeName;
import com.bangjwo.room.domain.vo.RoomBuildingType;
import com.bangjwo.room.domain.vo.RoomDirection;
import com.bangjwo.room.domain.vo.RoomOption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomRequestDto {
	private Long memberId;    // 해당 ID는 이후 토큰 처리로 대체 예정
	private RoomBuildingType buildingType;
	private String realEstateId;
	private String postalCode;    // 주소 엔티티
	private String address;    // 주소 엔티티
	private String addressDetail;    // 주소 엔티티
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
	private List<MultipartFile> images; // image 엔티티
}
