package com.bangjwo.room.application.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ReviewDto {
	long reviewId;
	long landlordId;
	long roomId;
	String content;
	LocalDate createdAt;
	LocalDate updatedAt;
}
