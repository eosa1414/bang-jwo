package com.bangjwo.room.domain.entity;

import com.bangjwo.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ADDRESS")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;

	@Column(nullable = false)
	private Long roomId;

	@Column
	private String name;

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

	@Column(length = 100)
	private String neighborhood;
}
