package com.bangjwo.auth.application.dto.response;

import com.bangjwo.auth.domain.vo.LoginType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberAuthDto {
	private LoginType loginType; // 로그인 결과: LOGIN 또는 SIGNUP
	private Long memberId;      // JWT 생성에 필요한 ID
	private String nickName;
}
