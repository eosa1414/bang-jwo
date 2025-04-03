package com.bangjwo.room.domain.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bangjwo.room.domain.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findAllByRealEstateIdAndAddressDetailAndDeletedAtIsNull(String realEstateId, String addressDetail);

	Optional<Review> findByReviewIdAndDeletedAtIsNull(Long reviewId);

	@Query(value = """
		    SELECT 
		        r.review_id AS reviewId,
		        r.content AS content,
		        r.created_at AS createdAt,
		        r.updated_at AS updatedAt,
		        rm.room_id AS roomId,
		        rm.monthly_rent AS monthlyRent,
		        rm.deposit AS deposit,
		        rm.building_type AS roomType
		    FROM review r
		    JOIN room rm ON r.room_id = rm.room_id
		    WHERE r.tenant_id = :memberId
		      AND r.deleted_at IS NULL
		    ORDER BY r.created_at DESC
		    LIMIT :size OFFSET :offset
		""", nativeQuery = true)
	List<Map<String, Object>> findMyReviewsWithRoomInfo(
		@Param("memberId") Long memberId,
		@Param("size") Integer size,
		@Param("offset") Long offset
	);

	@Query(value = """
		    SELECT COUNT(*)
		    FROM review r
		    WHERE r.tenant_id = :memberId
		      AND r.deleted_at IS NULL
		""", nativeQuery = true)
	Integer countMyReviews(@Param("memberId") Long memberId);

}
