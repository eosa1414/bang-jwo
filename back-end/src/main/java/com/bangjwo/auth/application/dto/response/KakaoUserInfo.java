package com.bangjwo.auth.application.dto.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoUserInfo {

	@JsonProperty("id")
	private String kakaoId;

	private String nickname;
	private String profileImageUrl;

	@JsonProperty("kakao_account")
	public void unpackKakaoAccount(Map<String, Object> kakaoAccount) {
		if (kakaoAccount != null) {
			Map<String, Object> profile = (Map<String, Object>)kakaoAccount.get("profile");

			if (profile != null) {
				this.nickname = (String)profile.get("nickname");
				this.profileImageUrl = (String)profile.get("profile_image_url");
			}
		}
	}
}

