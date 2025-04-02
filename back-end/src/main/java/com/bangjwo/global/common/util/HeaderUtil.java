package com.bangjwo.global.common.util;

import com.bangjwo.global.common.error.GlobalErrorCodes;
import com.bangjwo.global.common.exception.BusinessException;

public class HeaderUtil {
	private static final String BEARER_PREFIX = "Bearer ";

	public static String extractToken(String authorizationHeader) {
		if (authorizationHeader == null || authorizationHeader.isBlank()) {
			throw new BusinessException(GlobalErrorCodes.INVALID_HEADER_DATA);
		}

		if (authorizationHeader.startsWith(BEARER_PREFIX)) {
			return authorizationHeader.substring(BEARER_PREFIX.length());
		}

		return authorizationHeader;
	}
}
