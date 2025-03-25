package com.bangjwo.room.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ImageResponseDto {
	private Long imageId;
	private String imageUrl;
}
