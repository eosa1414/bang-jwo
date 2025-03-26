package com.bangjwo.room.application.dto.response;

import java.util.List;

import com.bangjwo.global.common.page.PageResponse;

import lombok.Getter;

@Getter
public class RoomListResponseDto extends PageResponse<RoomSummaryResponse> {
	public RoomListResponseDto(Integer totalItems, Integer currentPage, Integer size, List<RoomSummaryResponse> items) {
		super(totalItems, currentPage, size, items);
	}

	public RoomListResponseDto(Integer totalItems, Integer currentPage, List<RoomSummaryResponse> items) {
		super(totalItems, currentPage, items);
	}
}
