package com.bangjwo.member.application.convert;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bangjwo.auth.application.dto.response.KakaoUserInfo;
import com.bangjwo.auth.application.dto.response.MemberAuthDto;
import com.bangjwo.auth.domain.vo.LoginType;
import com.bangjwo.member.application.dto.response.MemberResponseDto;
import com.bangjwo.member.application.dto.response.MyReviewListDto;
import com.bangjwo.member.domain.entity.Member;

public class MemberConverter {
	public static Member convert(KakaoUserInfo userInfo) {
		return Member.builder()
			.kakaoId(Long.valueOf(userInfo.getKakaoId()))
			.nickname(userInfo.getNickname())
			.profileUrl(userInfo.getProfileImageUrl())
			.isAuth(false)
			.build();
	}

	public static MemberAuthDto toMemberAuthDto(Member member, LoginType loginType) {
		return MemberAuthDto.builder()
			.memberId(member.getMemberId())
			.nickName(member.getNickname())
			.loginType(loginType)
			.build();
	}

	public static MemberResponseDto toMemberDto(Member member) {
		return MemberResponseDto.builder()
			.id(member.getMemberId())
			.name(member.getName())
			.nickName(member.getNickname())
			.isAuth(member.getIsAuth())
			.profileUrl(member.getProfileUrl())
			.birthday(member.getBirthday())
			.phone(member.getPhone())
			.build();
	}

	public static MyReviewListDto toDto(Map<String, Object> row) {
		return MyReviewListDto.builder()
			.reviewId(((Number)row.get("reviewId")).longValue())
			.content((String)row.get("content"))
			.createdAt(((Timestamp)row.get("createdAt")).toLocalDateTime())
			.updatedAt(((Timestamp)row.get("updatedAt")).toLocalDateTime())
			.roomId(((Number)row.get("roomId")).longValue())
			.monthlyRent(((Number)row.get("monthlyRent")).intValue())
			.deposit(((Number)row.get("deposit")).intValue())
			.roomType((String)row.get("roomType"))
			.build();
	}

	public static List<MyReviewListDto> toDtoList(List<Map<String, Object>> rawList) {
		return rawList.stream()
			.map(MemberConverter::toDto)
			.collect(Collectors.toList());
	}
}
