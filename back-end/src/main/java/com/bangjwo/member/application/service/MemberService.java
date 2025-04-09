package com.bangjwo.member.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.global.common.error.member.MemberErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.global.common.page.PaginationRequest;
import com.bangjwo.member.application.convert.MemberConverter;
import com.bangjwo.member.application.dto.request.UpdateMemberRequestDto;
import com.bangjwo.member.application.dto.response.MemberResponseDto;
import com.bangjwo.member.application.dto.response.ReviewListResponseDto;
import com.bangjwo.member.domain.entity.Member;
import com.bangjwo.member.domain.repository.MemberRepository;
import com.bangjwo.portone.application.dto.IdentityDto;
import com.bangjwo.portone.application.service.VerificationService;
import com.bangjwo.room.domain.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final MemberImageService memberImageService;
	private final ReviewRepository reviewRepository;
	private final VerificationService verificationService;

	@Transactional(readOnly = true)
	public MemberResponseDto getMyInfo(Long memberId) {
		var member = searchMember(memberId);

		return MemberConverter.toMemberDto(member);
	}

	@Transactional(readOnly = true)
	public Member searchMember(Long memberId) {
		return memberRepository.findByMemberId(memberId)
			.orElseThrow(() -> new BusinessException(MemberErrorCode.NOT_FOUND_MEMBER));
	}

	@Transactional(readOnly = true)
	public ReviewListResponseDto getMyReviewList(Long memberId, Integer page, Integer size) {
		var pageable = PaginationRequest.toPageable(page, size);
		var rawResult = reviewRepository.findMyReviewsWithRoomInfo(memberId, pageable.getPageSize(),
			pageable.getOffset());
		var reviewList = MemberConverter.toDtoList(rawResult);
		var total = reviewRepository.countMyReviews(memberId);

		return new ReviewListResponseDto(total, page, size, reviewList);
	}

	@Transactional
	public void updateMember(Long memberId, UpdateMemberRequestDto requestDto) {
		var member = searchMember(memberId);
		if (requestDto.getProfileImage() != null && !requestDto.getProfileImage().isEmpty()) {
			memberImageService.uploadAndUpdateProfileImage(member, requestDto.getProfileImage());
		}
		if (requestDto.getNickname() != null && !requestDto.getNickname().isBlank()) {
			member.updateNickname(requestDto.getNickname());
		}
	}

	@Transactional
	public void updateMemberForVerify(Long memberId, IdentityDto.IdentityRequest dto) {
		IdentityDto.IdentityResponse identity = verificationService.getVerification(dto.identityVerificationId());
		Member member = searchMember(memberId);

		System.out.println(
			"name : " + identity.name() + " phone : " + identity.phone() + " birthDate : " + identity.birthDate());

		member.updateForVerify(identity.name(), identity.birthDate(), identity.phone());
	}

	@Transactional
	public void withdraw(Long memberId) {
		var member = searchMember(memberId);
		member.softDelete();
	}
}
