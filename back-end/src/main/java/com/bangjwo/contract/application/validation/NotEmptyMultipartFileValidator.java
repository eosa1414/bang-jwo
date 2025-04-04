package com.bangjwo.contract.application.validation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyMultipartFileValidator implements ConstraintValidator<NotEmptyMultipartFile, MultipartFile> {
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		return value != null && !value.isEmpty();
	}
}
