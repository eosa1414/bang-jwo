package com.bangjwo.room.application.dto.response;

import java.util.List;

import com.bangjwo.global.common.page.PageResponse;

import lombok.Getter;

@Getter
public class RoomListResponseDto extends PageResponse<RoomSummaryResponse> {
	public RoomListResponseDto(int totalItems, Integer currentPage, List<RoomSummaryResponse> items) {
		super(totalItems, currentPage, items);
	}
}
