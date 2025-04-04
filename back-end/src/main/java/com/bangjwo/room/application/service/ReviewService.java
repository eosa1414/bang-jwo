package com.bangjwo.room.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.global.common.error.review.ReviewErrorCode;
import com.bangjwo.global.common.error.room.RoomErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.global.common.exception.RoomException;
import com.bangjwo.room.application.convert.ReviewConverter;
import com.bangjwo.room.application.dto.response.ReviewDto;
import com.bangjwo.room.domain.entity.Address;
import com.bangjwo.room.domain.entity.Review;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.repository.ReviewRepository;
import com.bangjwo.room.domain.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final RoomRepository roomRepository;
	private final AddressService addressService;

	@Transactional
	public Review createReview(long roomId, long memberId, String content) {
		Room room = roomRepository.findByRoomIdAndDeletedAtIsNull(roomId)
			.orElseThrow(() -> new RoomException(RoomErrorCode.NOT_FOUND_SEARCH_ROOM));
		Address address = addressService.findByRoom(room);
		return reviewRepository.save(ReviewConverter.convert(room, address, content, memberId));
	}

	@Transactional(readOnly = true)
	public Review findReview(long reviewId) {
		return reviewRepository.findByReviewIdAndDeletedAtIsNull(reviewId)
			.orElseThrow(() -> new BusinessException(ReviewErrorCode.REVIEW_NOT_FOUND));
	}

	@Transactional
	public void deleteReview(long reviewId, long memberId) {
		Review review = findReview(reviewId);
		if (!review.getTenantId().equals(memberId)) {
			throw new BusinessException(ReviewErrorCode.NOT_WRITER);
		}
		review.softDelete();
	}

	@Transactional
	public Review updateReview(long reviewId, long memberId, String content) {
		Review review = findReview(reviewId);  // 기존 리뷰 조회
		if (!review.getTenantId().equals(memberId)) {
			throw new BusinessException(ReviewErrorCode.NOT_WRITER);
		}
		review.updateContent(content);         // 내용만 수정 (엔티티 메서드 사용 권장)
		return review;
	}

	@Transactional(readOnly = true)
	public List<ReviewDto> getReviews(String realEstateId, String addressDetail) {
		List<Review> result = reviewRepository.findAllByRealEstateIdAndAddressDetailAndDeletedAtIsNull(realEstateId,
			addressDetail);
		return result.stream().map(ReviewConverter::convert).toList();
	}
}
