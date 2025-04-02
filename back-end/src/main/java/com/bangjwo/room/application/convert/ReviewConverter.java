package com.bangjwo.room.application.convert;

import java.time.LocalDate;

import com.bangjwo.room.application.dto.response.ReviewDto;
import com.bangjwo.room.domain.entity.Address;
import com.bangjwo.room.domain.entity.Review;
import com.bangjwo.room.domain.entity.Room;

public class ReviewConverter {
	public static Review convert(Room room, Address address, String content) {

		return Review.builder()
			.roomId(room.getRoomId())
			.landlordId(room.getMemberId())
			.tenantId(room.getMemberId())
			.realEstateId(room.getRealEstateId())
			.addressDetail(address.getAddressDetail())
			.content(content)
			.build();
	}

	public static ReviewDto convert(Review review) {
		return ReviewDto.builder()
			.reviewId(review.getReviewId())
			.landlordId(review.getLandlordId())
			.roomId(review.getRoomId())
			.content(review.getContent())
			.createdAt(LocalDate.from(review.getCreatedAt()))
			.updatedAt(LocalDate.from(review.getUpdatedAt()))
			.build();
	}
}
