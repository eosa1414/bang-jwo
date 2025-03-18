package com.bangjwo.room.domain.entity;

import com.bangjwo.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "REVIEW")
public class Review extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	@Column(nullable = false)
	private Long lessorId;

	@Column(nullable = false)
	private Long lesseeId;

	@Column(nullable = false)
	private Long roomId;

	@Column(nullable = false)
	private String realEstateId;

	@Column(nullable = false)
	private String roomNumber;

	@Column
	private String content;
}

