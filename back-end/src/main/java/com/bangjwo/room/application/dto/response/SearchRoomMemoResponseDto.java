package com.bangjwo.room.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "매물에 대한 메모 조회 응답 DTO")
public class SearchRoomMemoResponseDto {

	@Schema(description = "매물(Room) ID", example = "1")
	private Long roomId;

	@Schema(description = "메모 내용", example = "이 매물은 일단 주변에 음식점이 없고, 햇빛이 들어오지 않을것 같지만 저렴함")
	private String content;
}
