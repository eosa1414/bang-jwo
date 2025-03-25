package com.bangjwo.room.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
@Table(name = "MEMO", indexes = {
	@Index(name = "idx_room_member", columnList = "roomId, memberId")
})
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

	public void updateContent(String content) {
		this.content = content;
	}
}
