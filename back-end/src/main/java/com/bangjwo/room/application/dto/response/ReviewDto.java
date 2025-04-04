package com.bangjwo.room.application.dto.response;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@Schema(description = "리뷰 상세 정보 DTO")
public class ReviewDto {

	@Schema(description = "리뷰 ID", example = "1")
	private long reviewId;

	@Schema(description = "집주인(임대인) ID", example = "2")
	private long landlordId;

	@Schema(description = "매물(Room) ID", example = "3")
	private long roomId;

	@Schema(description = "리뷰 내용", example = "집주인도 친절하고 집 상태도 좋아요!")
	private String content;

	@Schema(description = "리뷰 작성일", example = "2024-03-20")
	private LocalDate createdAt;

	@Schema(description = "리뷰 수정일", example = "2024-03-22")
	private LocalDate updatedAt;
}
