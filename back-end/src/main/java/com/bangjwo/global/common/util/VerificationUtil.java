package com.bangjwo.global.common.util;

import com.bangjwo.global.common.error.portone.PortoneErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.member.domain.entity.Member;
import com.bangjwo.portone.application.dto.IdentityDto;

public class VerificationUtil {
	public static void compareMemberInfo(Member member, IdentityDto.IdentityResponse dto) {
		if (!member.getName().equals(dto.name()) ||
			!member.getPhone().equals(dto.phone()) ||
			!member.getBirthday().equals(dto.birthDate())) {
			throw new BusinessException(PortoneErrorCode.USER_INFO_MISMATCH);
		}
	}
}
