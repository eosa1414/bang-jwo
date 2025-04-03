package com.bangjwo.member.application.dto.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "내가 작성한 리뷰 정보 DTO")
public class MyReviewListDto {

	@Schema(description = "리뷰 ID", example = "1")
	private Long reviewId;

	@Schema(description = "리뷰 내용", example = "집주인 분이 아주 친절하셨습니다~~")
	private String content;

	@Schema(description = "작성일시", example = "2025-04-02T14:00:00")
	private LocalDateTime createdAt;

	@Schema(description = "수정일시", example = "2025-04-03T12:30:00")
	private LocalDateTime updatedAt;

	@Schema(description = "매물 ID", example = "1")
	private Long roomId;

	@Schema(description = "월세", example = "540000")
	private int monthlyRent;

	@Schema(description = "보증금", example = "5000000")
	private int deposit;

	@Schema(description = "건물 유형", example = "VILLA_HOUSE")
	private String roomType;
}
