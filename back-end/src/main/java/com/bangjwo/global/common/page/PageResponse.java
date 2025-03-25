package com.bangjwo.global.common.page;

import java.util.List;

import lombok.Getter;

/**
 * DB에서 일부만 조회한 결과를 감싸는 페이지네이션 응답 클래스입니다.
 * <p>
 * 응답 데이터는 전체 항목 수(totalItems), 총 페이지 수(totalPages),
 * 현재 페이지(currentPage), 페이지당 아이템 수(size), 그리고 해당 페이지의 데이터(items)를 포함합니다.
 * 사용 예시:
 * <pre>
 *     // DB에서 일부 데이터 조회 후
 *     int totalItems = postRepository.countAll();
 *     List<PostDto> posts = postRepository.findPage(offset, size);
 *     PageResponse<PostDto> response = new PageResponse<>(totalItems, currentPage, size, posts);
 * </pre>
 */
@Getter
public class PageResponse<T> {
	private static final int DEFAULT_SIZE = 15;

	/** 전체 항목 수 */
	private final int totalItems;
	/** 총 페이지 수 (전체 항목 수와 페이지 크기를 기반으로 자동 계산) */
	private final int totalPages;
	/** 현재 페이지 번호 */
	private final int currentPage;
	/** 페이지당 아이템 수 */
	private final int size;
	/** 현재 페이지에 해당하는 데이터 리스트 */
	private final List<T> items;

	/**
	 * 전체 항목 수, 현재 페이지, 페이지당 아이템 수, 데이터 리스트를 입력받아 생성합니다.
	 * currentPage와 size가 null이거나 1 이하인 경우 각각 1, DEFAULT_SIZE(15)를 적용합니다.
	 *
	 * @param totalItems 전체 항목 수
	 * @param currentPage 현재 페이지 번호 (null 또는 1 이하이면 1로 초기화)
	 * @param size 페이지당 아이템 수 (null 또는 1 이하이면 DEFAULT_SIZE로 초기화)
	 * @param items 현재 페이지에 해당하는 데이터 리스트
	 */
	public PageResponse(int totalItems, Integer currentPage, Integer size, List<T> items) {
		int cp = (currentPage == null || currentPage <= 0) ? 1 : currentPage;
		int s = (size == null || size <= 0) ? DEFAULT_SIZE : size;
		this.totalItems = totalItems;
		this.currentPage = cp;
		this.size = s;
		// totalItems가 0이면 총 페이지 수는 0, 아니면 올림하여 계산
		this.totalPages = (totalItems == 0) ? 0 : (int)Math.ceil((double)totalItems / s);
		this.items = items;
	}

	/**
	 * 전체 항목 수, 현재 페이지, 데이터 리스트를 입력받아 생성합니다.
	 * 페이지당 아이템 수는 기본값(DEFAULT_SIZE)을 사용합니다.
	 *
	 * @param totalItems 전체 항목 수
	 * @param currentPage 현재 페이지 번호 (null 또는 1 이하이면 1로 초기화)
	 * @param items 현재 페이지에 해당하는 데이터 리스트
	 */
	public PageResponse(int totalItems, Integer currentPage, List<T> items) {
		this(totalItems, currentPage, DEFAULT_SIZE, items);
	}

	/**
	 * 현재 페이지의 조회 시작 위치(offset)를 계산하여 반환합니다.
	 * (예: currentPage가 1이면 0, 2이면 size만큼의 offset 반환)
	 *
	 * @return 0부터 시작하는 조회 offset 값
	 */
	public int getOffset() {
		return (currentPage - 1) * size;
	}

	@Override
	public String toString() {
		return "PageResponse{" +
			"totalItems=" + totalItems +
			", totalPages=" + totalPages +
			", currentPage=" + currentPage +
			", size=" + size +
			", offset=" + getOffset() +
			", items=" + items +
			'}';
	}
}
