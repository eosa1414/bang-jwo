package com.bangjwo.auth.presentation.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bangjwo.auth.infrastructure.JwtTokenProvider;
import com.bangjwo.auth.resolver.MemberHeader;
import com.bangjwo.global.common.error.auth.AuthErrorCode;
import com.bangjwo.global.common.exception.BusinessException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberHeaderArgumentResolver implements HandlerMethodArgumentResolver {
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(MemberHeader.class)
			&& parameter.getParameterType().equals(Long.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		String authorizationHeader = request.getHeader("Authorization");

		MemberHeader memberHeader = parameter.getParameterAnnotation(MemberHeader.class);

		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			if (!memberHeader.required()) {
				return null;
			} else {
				throw new BusinessException(AuthErrorCode.NOT_EXIST_AUTHORIZATION_TOKEN);
			}
		}
		String token = authorizationHeader.substring(7);
		String memberIdStr = jwtTokenProvider.getClaims(token);

		try {
			return Long.valueOf(memberIdStr);
		} catch (NumberFormatException e) {
			throw new BusinessException(AuthErrorCode.INVALID_AUTHORIZATION_TOKEN);
		}
	}
}
