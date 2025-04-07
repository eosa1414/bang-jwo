package com.bangjwo.global.common.lock;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.bangjwo.global.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RedisLockAspect {

	private final StringRedisTemplate redisTemplate;

	private static final String UNLOCK_SCRIPT =
		"if redis.call('get', KEYS[1]) == ARGV[1] then " +
			"   return redis.call('del', KEYS[1]) " +
			"else " +
			"   return 0 " +
			"end";

	@Around("@annotation(redisLock)")
	public Object around(ProceedingJoinPoint joinPoint, RedisLock redisLock) throws Throwable {
		String key = parseKey(redisLock.key(), joinPoint);
		long leaseTime = redisLock.leaseTime();

		String uuid = UUID.randomUUID().toString();
		log.info("[RedisLock] Trying to acquire lock. Key = '{}', UUID = {}, LeaseTime = {}s", key, uuid, leaseTime);

		Boolean success = redisTemplate
			.opsForValue()
			.setIfAbsent(key, uuid, leaseTime, TimeUnit.SECONDS);

		if (Boolean.FALSE.equals(success)) {
			log.warn("[RedisLock] Lock acquisition failed. Key = '{}'", key);
			throw new BusinessException(redisLock.errorCode());
		}

		try {
			log.info("[RedisLock] Lock acquired. Proceeding with business logic. Key = '{}'", key);
			return joinPoint.proceed();
		} finally {
			releaseLock(key, uuid);
		}
	}

	private void releaseLock(String key, String uuid) {
		redisTemplate.execute(
			new DefaultRedisScript<>(UNLOCK_SCRIPT, Long.class),
			List.of(key),
			uuid
		);
		log.info("[RedisLock] Lock released safely. Key = '{}'", key);
	}

	private String parseKey(String keyExpression, ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();

		EvaluationContext context = new StandardEvaluationContext();
		String[] parameterNames = signature.getParameterNames();
		Object[] args = joinPoint.getArgs();

		for (int i = 0; i < parameterNames.length; i++) {
			context.setVariable(parameterNames[i], args[i]);
		}

		ExpressionParser parser = new SpelExpressionParser();
		return parser.parseExpression(keyExpression).getValue(context, String.class);
	}
}
