package com.bangjwo.global.common.page;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"totalItems", "totalPages", "currentPage", "size", "currentPageItemCount", "offset", "items"})
@Schema(description = "공통 페이지네이션 응답 DTO")
public class PageResponse<T> {
	private static final int DEFAULT_SIZE = 15;

	@Schema(description = "전체 항목 수", example = "100")
	private final int totalItems;

	@Schema(description = "전체 페이지 수", example = "10")
	private final int totalPages;

	@Schema(description = "현재 페이지 번호", example = "1")
	private final int currentPage;

	@Schema(description = "페이지 당 아이템 수", example = "15")
	private final int size;

	@Schema(description = "현재 페이지에 포함된 아이템 개수", example = "15")
	private final int currentPageItemCount;

	@Schema(description = "현재 페이지의 아이템 목록")
	private final List<T> items;

	public PageResponse(int totalItems, Integer currentPage, Integer size, List<T> items) {
		int cp = (currentPage == null || currentPage <= 0) ? 1 : currentPage;
		int s = (size == null || size <= 0) ? DEFAULT_SIZE : size;
		this.totalItems = totalItems;
		this.currentPage = cp;
		this.size = s;
		this.currentPageItemCount = (items == null) ? 0 : items.size();
		this.totalPages = (totalItems == 0) ? 0 : (int)Math.ceil((double)totalItems / s);
		this.items = items;
	}

	public PageResponse(int totalItems, Integer currentPage, List<T> items) {
		this(totalItems, currentPage, DEFAULT_SIZE, items);
	}

	public int getOffset() {
		return (currentPage - 1) * size + 1;
	}
}
