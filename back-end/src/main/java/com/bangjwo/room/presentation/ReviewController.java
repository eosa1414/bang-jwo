package com.bangjwo.room.presentation;

import java.util.List;

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

import com.bangjwo.auth.resolver.MemberHeader;
import com.bangjwo.room.application.dto.request.ReviewRequestDto;
import com.bangjwo.room.application.dto.response.ReviewDto;
import com.bangjwo.room.application.dto.response.ReviewResponseDto;
import com.bangjwo.room.application.service.ReviewService;
import com.bangjwo.room.domain.entity.Review;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Review", description = "리뷰 관련 API")
@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@Operation(
		summary = "리뷰 작성",
		description = "특정 매물에 대한 리뷰를 작성합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@ApiResponse(responseCode = "200", description = "리뷰 작성에 성공했습니다.")
	@PostMapping("/{roomId}")
	public ResponseEntity<ReviewResponseDto> addReview(@PathVariable Long roomId,
		@MemberHeader Long memberId, @RequestBody ReviewRequestDto requestDto) {
		Review review = reviewService.createReview(roomId, memberId, requestDto.getContent());

		ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
			.reviewId(review.getReviewId())
			.content(review.getContent())
			.build();
		return ResponseEntity.ok(reviewResponseDto);
	}

	@Operation(
		summary = "리뷰 조회",
		description = "해당 부동산 주소에 대한 리뷰를 조회합니다."
	)
	@ApiResponse(responseCode = "200", description = "리뷰 목록을 성공적으로 반환했습니다.")
	@GetMapping
	public ResponseEntity<List<ReviewDto>> getReview(@RequestParam String realEstateId,
		@RequestParam String addressDetail) {
		List<ReviewDto> result = reviewService.getReviews(realEstateId, addressDetail);
		return ResponseEntity.ok(result);
	}

	@Operation(
		summary = "리뷰 수정",
		description = "리뷰 ID를 기반으로 리뷰 내용을 수정합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@ApiResponse(responseCode = "200", description = "리뷰 수정에 성공했습니다.")
	@PatchMapping("/{reviewId}")
	public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Long reviewId,
		@MemberHeader Long memberId, @RequestBody ReviewRequestDto requestDto) {
		Review review = reviewService.updateReview(reviewId, memberId, requestDto.getContent());
		ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
			.reviewId(review.getReviewId())
			.content(review.getContent())
			.build();
		return ResponseEntity.ok(reviewResponseDto);
	}

	@Operation(
		summary = "리뷰 삭제",
		description = "리뷰 ID를 기반으로 리뷰를 삭제합니다.",
		security = @SecurityRequirement(name = "JWT")
	)
	@ApiResponse(responseCode = "200", description = "리뷰 삭제에 성공했습니다.")
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<ReviewResponseDto> deleteReview(@PathVariable Long reviewId, @MemberHeader Long memberId) {
		reviewService.deleteReview(reviewId, memberId);
		ReviewResponseDto reviewResponseDto = ReviewResponseDto.builder()
			.reviewId(reviewId)
			.build();
		return ResponseEntity.ok(reviewResponseDto);
	}
}
