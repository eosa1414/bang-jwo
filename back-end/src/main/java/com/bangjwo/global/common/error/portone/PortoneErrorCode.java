package com.bangjwo.global.common.error.portone;

import org.springframework.http.HttpStatus;

import com.bangjwo.global.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PortoneErrorCode implements ErrorCode {
	PREPAYMENT_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "PAYMENT4001", "사전 결제 정보 저장에 실패하였습니다."),

	// 포트원 검증 관련
	IMP_UID_NOT_FOUND(HttpStatus.NOT_FOUND, "PAYMENT4002", "impUid에 해당하는 결제 정보를 찾을 수 없습니다."),
	IMP_PAYMENT_VERIFICATION_FAILED(HttpStatus.BAD_REQUEST, "PAYMENT4003", "결제 정보 검증에 실패하였습니다."),

	// 결제 결과 처리
	PAYMENT_RESULT_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "PAYMENT4004", "결제 결과 저장에 실패하였습니다."),
	PAYMENT_ALREADY_COMPLETED(HttpStatus.CONFLICT, "PAYMENT4005", "이미 완료된 결제입니다."),

	// 결제 조회
	PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "PAYMENT4006", "해당 결제 내역을 찾을 수 없습니다."),
	PAYMENT_FAILED(HttpStatus.BAD_REQUEST, "PAYMENT4010", "결제가 이뤄지지 않았습니다."),
	UNAUTHORIZED_USER_ACCESS(HttpStatus.FORBIDDEN, "PAYMENT4007", "결제 내역에 접근할 수 있는 권한이 없습니다."),

	// 본인인증
	USER_INFO_MISMATCH(HttpStatus.BAD_REQUEST, "VERIFCATION4009", "인증한 사용자 정보가 기존 정보와 일치하지 않습니다."),
	UNAUTHORIZED_USER(HttpStatus.FORBIDDEN, "VERIFCATION4010", "사용자가 인증되지 않았습니다."),

	// 기타
	INVALID_PAYMENT_STATUS(HttpStatus.BAD_REQUEST, "PAYMENT4008", "잘못된 결제 상태 값입니다."),
	PAYMENT_INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "PAYMENT5000", "결제 처리 중 서버 오류가 발생했습니다.")
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
