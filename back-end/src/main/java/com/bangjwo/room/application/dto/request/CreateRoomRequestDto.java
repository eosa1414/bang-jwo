package com.bangjwo.room.application.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bangjwo.room.domain.vo.MaintenanceIncludeName;
import com.bangjwo.room.domain.vo.RoomBuildingType;
import com.bangjwo.room.domain.vo.RoomDirection;
import com.bangjwo.room.domain.vo.RoomOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomRequestDto {
	@NotNull
	@Min(0)
	private Long memberId;    // 해당 ID는 이후 토큰 처리로 대체 예정

	@NotNull
	private RoomBuildingType buildingType;

	@NotNull
	private String realEstateId;

	@NotNull
	private String postalCode;    // 주소 엔티티

	@NotNull
	private String address;    // 주소 엔티티

	@NotNull
	private String addressDetail;    // 주소 엔티티

	@NotNull
	@Min(0)
	private Integer deposit;

	@NotNull
	@Min(0)
	private Integer monthlyRent;

	@NotNull
	private BigDecimal exclusiveArea;

	@NotNull
	private BigDecimal supplyArea;

	@Min(0)
	private Integer totalUnits;

	@NotBlank
	private String floor;

	@NotNull
	@Min(0)
	private Integer maxFloor;

	@NotNull
	@Min(0)
	private Integer parkingSpaces;

	@NotNull
	private LocalDate availableFrom;

	@NotNull
	private LocalDate permissionDate;

	private String simpleDescription;

	private String description;

	@NotNull
	@Min(0)
	private Integer maintenanceCost;

	@NotNull
	@Min(1)
	private Integer roomCnt;

	@NotNull
	@Min(0)
	private Integer bathroomCnt;

	@NotNull
	private RoomDirection direction;

	private Boolean discussable;

	@NotNull
	private String discussDetail;

	@NotNull
	private Boolean reviewable;

	@NotNull
	private Boolean isPhonePublic;

	private List<MaintenanceIncludeName> maintenanceIncludes;

	private List<RoomOption> options;

	@NotNull
	@Size(min = 3, message = "이미지는 최소 3개 이상 등록해야 합니다.")
	private List<MultipartFile> images; // image 엔티티
}
