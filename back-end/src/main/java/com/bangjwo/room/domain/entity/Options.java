package com.bangjwo.room.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "OPTIONS")
public class Options {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long optionId;

	@Column(nullable = false)
	private Long roomId;

	// ENUM('엘리베이터','복층','옥탑','에어컨','세탁기','냉장고','전자렌지','가스렌지','인덕션','침대')
	@Column(nullable = false)
	private String optionName;
}

