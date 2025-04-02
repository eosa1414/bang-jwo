package com.bangjwo.global.config.auth;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bangjwo.auth.infrastructure.JwtTokenProvider;
import com.bangjwo.auth.presentation.resolver.MemberHeaderArgumentResolver;

@Configuration
public class MemberHeaderArgumentResolverConfig implements WebMvcConfigurer {
	private final JwtTokenProvider jwtTokenProvider;

	public MemberHeaderArgumentResolverConfig(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new MemberHeaderArgumentResolver(jwtTokenProvider));
	}
}
