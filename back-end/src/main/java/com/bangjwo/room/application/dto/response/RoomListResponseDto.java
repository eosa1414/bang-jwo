package com.bangjwo.room.application.dto.response;

import java.util.List;

import com.bangjwo.global.common.page.PageResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "RoomSummaryResponse 목록을 페이징 형태로 전달하는 DTO")
public class RoomListResponseDto extends PageResponse<RoomSummaryResponse> {

	public RoomListResponseDto(Integer totalItems, Integer currentPage, Integer size, List<RoomSummaryResponse> items) {
		super(totalItems, currentPage, size, items);
	}

	public RoomListResponseDto(Integer totalItems, Integer currentPage, List<RoomSummaryResponse> items) {
		super(totalItems, currentPage, items);
	}
}
