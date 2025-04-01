package com.bangjwo.room.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "매물 메모 업데이트 요청 DTO, 메모 생성/수정 모두 담당 ( 기존 메모가 없다면 생성 )")
public class UpdateRoomMemoRequestDto {
	@Schema(description = "회원 ID (이후 토큰 처리로 대체 예정)", example = "1")
	@Min(1)
	private Long memberId;

	@Schema(description = "메모 내용", example = "해당 매물에 대한 유저가 메모를 수정했습니다.")
	private String content;
}
