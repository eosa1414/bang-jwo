package com.bangjwo.room.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.bangjwo.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "IMAGE")
public class Image extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageId;

	@Column(nullable = false)
	private Long roomId;

	@Column(nullable = false)
	private String imageUrl;

	@CreatedDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdAt;
}

