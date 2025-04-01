package com.bangjwo.global.common.exception.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bangjwo.global.common.error.ErrorCode;
import com.bangjwo.global.common.error.ErrorResponseDto;
import com.bangjwo.global.common.error.GlobalErrorCodes;
import com.bangjwo.global.common.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponseDto> handleBusinessException(BusinessException e) {
		log.error("BusinessException", e);
		ErrorCode errorMessage = e.getErrorCode();

		return ErrorResponseDto.of(errorMessage);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e) {
		e.getBindingResult().getFieldErrors().forEach(error ->
			log.warn("Validation error - {}: {}", error.getField(), error.getDefaultMessage())
		);

		return ErrorResponseDto.of(GlobalErrorCodes.INVALID_INPUT_VALUE);
	}

	@ExceptionHandler(TypeMismatchException.class)
	protected ResponseEntity<ErrorResponseDto> handleTypeMismatchException(TypeMismatchException ex) {
		log.warn("TypeMismatchException ; {} : {}", ex.getPropertyName(), ex.getMessage());

		return ErrorResponseDto.of(GlobalErrorCodes.INVALID_INPUT_TYPE);
	}

	@ExceptionHandler(BindException.class)
	protected ResponseEntity<ErrorResponseDto> handleBindException(BindException ex) {
		List<String> errors = ex.getFieldErrors().stream()
			.map((FieldError error) -> error.getField() + ": " + error.getDefaultMessage())
			.collect(Collectors.toList());
		String errorMsg = String.join("; ", errors);
		log.warn("BindException: {}", errorMsg);

		return ErrorResponseDto.of(GlobalErrorCodes.BINDING_ERROR);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponseDto> handleException(Exception e) {
		log.error("Exception", e);
		return ErrorResponseDto.of(GlobalErrorCodes.INTERNAL_SERVER_ERROR);
	}
}
