package com.bangjwo.member.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bangjwo.member.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByKakaoId(Long kakaoId);

	Optional<Member> findByMemberId(Long memberId);
}
