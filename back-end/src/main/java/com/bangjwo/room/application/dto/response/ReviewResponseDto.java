package com.bangjwo.room.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@Schema(description = "리뷰 작성/수정 응답 DTO")
public class ReviewResponseDto {

	@Schema(description = "리뷰 ID", example = "1")
	private long reviewId;

	@Schema(description = "리뷰 내용", example = "집주인도 친절하고 집 상태도 좋아요!")
	private String content;
}
