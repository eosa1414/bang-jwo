package com.bangjwo.room.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "매물 이미지에 대한 응답 DTO")
public class ImageResponseDto {
	@Schema(description = "이미지 ID", example = "1")
	private Long imageId;

	@Schema(description = "이미지 URL", example = "https://example.com/images/1.jpg")
	private String imageUrl;
}
