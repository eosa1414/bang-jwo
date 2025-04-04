// package com.bangjwo.auth.infrastructure;
//
// import java.util.Optional;
// import java.util.concurrent.TimeUnit;
//
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.data.redis.RedisConnectionFailureException;
// import org.springframework.data.redis.core.StringRedisTemplate;
// import org.springframework.stereotype.Component;
//
// import com.bangjwo.member.application.dto.response.LoginMemberDto;
// import com.bangjwo.global.common.error.GlobalErrorCodes;
// import com.bangjwo.global.common.error.auth.AuthErrorCode;
// import com.bangjwo.global.common.exception.BusinessException;
// import com.bangjwo.auth.application.dto.response.MemberAuthDto;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
// @Slf4j
// @Component
// @RequiredArgsConstructor
// public class RefreshTokenStore {
// 	private static final String REDIS_PREFIX = "bangjwo_refresh";
//
// 	private final StringRedisTemplate redisTemplate;
// 	private final ObjectMapper objectMapper;
//
// 	@Value("${token.refresh_expiration_time}")
// 	private Long refreshExpirationTime;
//
// 	public void saveToken(String refreshToken, MemberAuthDto memberDto) {
// 		try {
// 			String memberJson = objectMapper.writeValueAsString(memberDto);
//
// 			log.info("redis : 유저 정보 log 확인 : " + memberJson);
//
// 			redisTemplate.opsForValue()
// 				.set(REDIS_PREFIX + refreshToken, memberJson, refreshExpirationTime, TimeUnit.MILLISECONDS);
// 		} catch (JsonProcessingException e) {
// 			throw new BusinessException(AuthErrorCode.MEMBER_JSON_SERIALIZE_FAIL);
// 		} catch (RedisConnectionFailureException e) {
// 			throw new BusinessException(GlobalErrorCodes.INTERNAL_SERVER_ERROR);
// 		}
// 	}
//
// 	public Optional<LoginMemberDto> getMemberByToken(String refreshToken) {
// 		try {
// 			String memberJson = redisTemplate.opsForValue().get(REDIS_PREFIX + refreshToken);
//
// 			log.info("redis : 유저 정보 log 확인 : " + memberJson);
//
// 			if (memberJson == null) {
// 				return Optional.empty();
// 			}
//
// 			return Optional.of(objectMapper.readValue(memberJson, LoginMemberDto.class));
// 		} catch (Exception e) {
// 			log.error("error class : {}", e.getClass().getSimpleName());
// 			log.error("error message: {}", e.getMessage());
// 			throw new BusinessException(AuthErrorCode.MEMBER_JSON_SERIALIZE_FAIL);
// 		}
// 	}
//
// 	public void removeToken(String refreshToken) {
// 		redisTemplate.delete(REDIS_PREFIX + refreshToken);
// 	}
// }
