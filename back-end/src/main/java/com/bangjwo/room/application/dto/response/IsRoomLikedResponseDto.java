package com.bangjwo.room.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "매물 찜목록 여부 응답 DTO")
public class IsRoomLikedResponseDto {
	@Schema(description = "매물(Room) ID", example = "100")
	private Long roomId;

	@Schema(description = "찜 상태 여부", example = "true")
	private Boolean isLiked;
}
