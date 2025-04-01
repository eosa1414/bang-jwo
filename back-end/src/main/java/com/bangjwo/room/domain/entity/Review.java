package com.bangjwo.room.domain.entity;

import com.bangjwo.global.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String addressDetail;

	@Column
	private String content;

	public void updateContent(String content) {
		this.content = content;
	}
}

