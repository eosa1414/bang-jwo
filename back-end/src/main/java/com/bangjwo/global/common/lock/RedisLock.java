package com.bangjwo.global.common.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.bangjwo.global.common.error.RedisLockErrorCode;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {
	String key();

	long leaseTime() default 1800;

	RedisLockErrorCode errorCode() default RedisLockErrorCode.LOCKED_RESOURCE;
}
