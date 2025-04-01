package com.bangjwo.room.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bangjwo.room.domain.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findAllByRealEstateIdAndAddressDetailAndDeletedAtIsNull(String realEstateId, String addressDetail);

	Optional<Review> findByReviewIdAndDeletedAtIsNull(Long reviewId);
}
