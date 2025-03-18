package com.bangjwo.room.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMO")
public class Memo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memoId;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
	private Long roomId;

	@Column(nullable = false)
	private String content;
}
