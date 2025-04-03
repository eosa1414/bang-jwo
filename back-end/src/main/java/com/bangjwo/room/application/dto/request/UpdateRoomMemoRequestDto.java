package com.bangjwo.room.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "매물 메모 업데이트 요청 DTO, 메모 생성/수정 모두 담당 ( 기존 메모가 없다면 생성 )")
public class UpdateRoomMemoRequestDto {
	@Schema(description = "메모 내용", example = "해당 매물에 대한 유저가 메모를 수정했습니다.")
	private String content;
}
