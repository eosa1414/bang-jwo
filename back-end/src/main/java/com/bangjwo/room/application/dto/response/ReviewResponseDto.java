package com.bangjwo.room.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ReviewResponseDto {
	long reviewId;
	String content;
}
