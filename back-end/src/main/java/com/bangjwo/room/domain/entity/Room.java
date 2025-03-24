package com.bangjwo.room.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bangjwo.global.common.entity.BaseEntity;
import com.bangjwo.room.domain.vo.RoomBuildingType;
import com.bangjwo.room.domain.vo.RoomDirection;
import com.bangjwo.room.domain.vo.RoomStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "ROOM")
public class Room extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;

	@Column(nullable = false)
	private Long memberId;

	@Column
	@Enumerated(EnumType.STRING)
	private RoomBuildingType buildingType;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RoomStatus status;

	@Column(length = 20, nullable = false)
	private String realEstateId;

	@Column(nullable = false)
	private Integer deposit;

	@Column(nullable = false)
	private Integer monthlyRent;

	@Column(precision = 10, scale = 2)
	private BigDecimal exclusiveArea;

	@Column(precision = 10, scale = 2)
	private BigDecimal supplyArea;

	@Column
	private Integer totalUnits;

	@Column(length = 10)
	private String floor;

	@Column
	private Integer maxFloor;

	@Column(nullable = false)
	private Integer parkingSpaces;

	@Column(nullable = false)
	private LocalDate availableFrom;

	@Column(nullable = false)
	private LocalDate permissionDate;

	@Column
	private String simpleDescription;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	private Integer maintenanceCost;

	@Column(nullable = false)
	private Integer roomCnt;

	@Column(nullable = false)
	private Integer bathroomCnt;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RoomDirection direction;

	@Column(nullable = false)
	private Boolean verified;

	@Column(nullable = false)
	private Boolean registryPaid;

	@Column(nullable = false)
	private Boolean discussable;

	@Column
	private String discussDetail;

	@Column(nullable = false)
	private Boolean reviewable;

	@Column(nullable = false)
	private Boolean isPhonePublic;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<MaintenanceInclude> maintenanceIncludes = new ArrayList<>();

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<Options> roomOptions = new ArrayList<>();

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<Image> images = new ArrayList<>();

	public void addMaintenanceInclude(MaintenanceInclude include) {
		this.maintenanceIncludes.add(include);
		include.setRoom(this);
	}

	public void addRoomOption(Options option) {
		this.roomOptions.add(option);
		option.setRoom(this);
	}
}

