package com.bangjwo.member.domain.entity;

import com.bangjwo.global.common.entity.BaseEntity;

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
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(indexes = {
	@Index(name = "idx_member_id", columnList = "memberId"),
	@Index(name = "idx_kakao_id", columnList = "kakaoId")
})
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	@Column(nullable = false, unique = true)
	private Long kakaoId;

	@Column(nullable = false)
	private Boolean isAuth;

	@Column(length = 20)
	private String name;

	@Column(length = 10, nullable = false)
	private String nickname;

	@Column(length = 20)
	private String birthday;

	@Column(length = 20)
	private String phone;

	@Column
	private String profileUrl;

	public void updateNickname(String nickname) {
		if (nickname != null) {
			this.nickname = nickname;
		}
	}

	public void updateProfileUrl(String profileUrl) {
		if (profileUrl != null) {
			this.profileUrl = profileUrl;
		}
	}
}

