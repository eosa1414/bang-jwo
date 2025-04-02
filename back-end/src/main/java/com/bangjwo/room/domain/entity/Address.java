package com.bangjwo.room.domain.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
	@Index(name = "idx_room_id", columnList = "room_id"),
	@Index(name = "idx_lat_lng", columnList = "lat, lng")
})
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", unique = true)
	private Room room;

	@Column
	private String name;

	@Column
	private String addressDetail;

	@Column(length = 10)
	private String postalCode;

	@Column(precision = 9, scale = 6)
	private BigDecimal lat;

	@Column(precision = 9, scale = 6)
	private BigDecimal lng;

	@Column(length = 100)
	private String province;

	@Column(length = 100)
	private String city;

	@Column(length = 100)
	private String district;
}
