package com.bangjwo.member.application.service;

import com.bangjwo.auth.application.dto.response.KakaoUserInfo;
import com.bangjwo.auth.application.dto.response.MemberAuthDto;

public interface MemberQueryService {
	MemberAuthDto loginOrSignupByKakao(KakaoUserInfo kakaoUserInfo);
}
