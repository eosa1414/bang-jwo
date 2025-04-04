package com.bangjwo.global.common.error;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "공통 에러 응답 DTO")
public class ErrorResponseDto {
	@Schema(description = "서버 에러코드")
	private String code;

	@Schema(description = "에러 메시지")
	private String message;

	@Schema(description = "에러 발생 서버 시간")
	private final LocalDateTime serverDateTime;

	public ErrorResponseDto(ErrorCode errorCode) {
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
		this.serverDateTime = LocalDateTime.now();
	}

	public static ResponseEntity<ErrorResponseDto> of(ErrorCode errorCode) {

		return ResponseEntity
			.status(errorCode.getHttpStatus())
			.body(new ErrorResponseDto(errorCode));
	}
}
