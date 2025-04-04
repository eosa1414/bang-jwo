package com.bangjwo.room.presentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.room.application.dto.request.ReviewRequestDto;
import com.bangjwo.room.application.dto.response.ReviewDto;
import com.bangjwo.room.application.dto.response.ReviewResponseDto;
import com.bangjwo.room.application.service.ReviewService;
import com.bangjwo.room.domain.entity.Review;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
	private final ReviewService reviewService;

	@PostMapping("/{roomId}")
	public ResponseEntity<ReviewResponseDto> addReview(@PathVariable Long roomId,
		@RequestBody ReviewRequestDto requestDto) {
		Review review = reviewService.createReview(roomId, requestDto.getContent());

		ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
			.reviewId(review.getReviewId())
			.content(review.getContent())
			.build();
		return ResponseEntity.ok(reviewResponseDto);
	}

	@GetMapping
	public ResponseEntity<List<ReviewDto>> getReview(@RequestParam String realEstateId,
		@RequestParam String addressDetail) {
		List<ReviewDto> result = reviewService.getReviews(realEstateId, addressDetail);
		return ResponseEntity.ok(result);
	}

	@PatchMapping("/{reviewId}")
	public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Long reviewId,
		@RequestBody ReviewRequestDto requestDto) {
		Review review = reviewService.updateReview(reviewId, requestDto.getContent());
		ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
			.reviewId(review.getReviewId())
			.content(review.getContent())
			.build();
		return ResponseEntity.ok(reviewResponseDto);
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<ReviewResponseDto> deleteReview(@PathVariable Long reviewId) {
		reviewService.deleteReview(reviewId);
		ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
			.reviewId(reviewId)
			.build();
		return ResponseEntity.ok(reviewResponseDto);
	}

	@GetMapping("/ping")
	public ResponseEntity<Map<String, String>> ping() {
		Map<String, String> result = new HashMap<>();
		result.put("status", "ok");
		return ResponseEntity.ok(result);
	}
}
