package com.bangjwo.contract.application.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

@Documented
@Constraint(validatedBy = NotEmptyMultipartFileValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
@NotNull
public @interface NotEmptyMultipartFile {
	String message() default "파일은 필수 입력 항목입니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
