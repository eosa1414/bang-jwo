package com.bangjwo.member.application.convert;

import com.bangjwo.auth.application.dto.response.KakaoUserInfo;
import com.bangjwo.auth.application.dto.response.MemberAuthDto;
import com.bangjwo.auth.domain.vo.LoginType;
import com.bangjwo.member.domain.entity.Member;

public class MemberConverter {
	public static Member convert(KakaoUserInfo userInfo) {
		return Member.builder()
			.kakaoId(Long.valueOf(userInfo.getKakaoId()))
			.nickname(userInfo.getNickname())
			.profileUrl(userInfo.getProfileImageUrl())
			.build();
	}

	public static MemberAuthDto toMemberAuthDto(Member member, LoginType loginType) {
		return MemberAuthDto.builder()
			.memberId(member.getMemberId())
			.nickName(member.getNickname())
			.loginType(loginType)
			.build();
	}
}
