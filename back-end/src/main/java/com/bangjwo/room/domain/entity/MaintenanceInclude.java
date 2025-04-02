package com.bangjwo.room.domain.entity;

import com.bangjwo.room.domain.vo.MaintenanceIncludeName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(indexes = {
	@Index(name = "idx_room_id", columnList = "room_id")
})
public class MaintenanceInclude {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long maintenanceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MaintenanceIncludeName maintenanceIncludeName;
}