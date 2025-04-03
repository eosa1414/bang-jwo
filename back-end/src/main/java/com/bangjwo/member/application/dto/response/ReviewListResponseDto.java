package com.bangjwo.member.application.dto.response;

import java.util.List;

import com.bangjwo.global.common.page.PageResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "내 리뷰 목록 페이징 응답 DTO")
public class ReviewListResponseDto extends PageResponse<MyReviewListDto> {

	public ReviewListResponseDto(
		@Schema(description = "전체 항목 수", example = "23") Integer totalItems,
		@Schema(description = "현재 페이지 번호", example = "1") Integer currentPage,
		@Schema(description = "페이지 당 아이템 수", example = "15") Integer size,
		@Schema(description = "내 리뷰 목록") List<MyReviewListDto> reviews
	) {
		super(totalItems, currentPage, size, reviews);
	}
}
