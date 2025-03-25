package com.bangjwo.global.common.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PaginationRequest {
	private static final int DEFAULT_SIZE = 15;

	public static Pageable toPageable(Integer page, Integer size) {
		int currentPage = (page == null || page < 1) ? 1 : page;
		int pageSize = (size == null || size < 1) ? DEFAULT_SIZE : size;

		return PageRequest.of(currentPage - 1, pageSize);
	}
}
