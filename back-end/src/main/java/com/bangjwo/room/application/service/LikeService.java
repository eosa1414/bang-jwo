package com.bangjwo.room.application.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.room.domain.entity.Likes;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.repository.LikeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {
	private final LikeRepository likeRepository;

	@Transactional(readOnly = true)
	public List<Likes> getLikeRooms(List<Room> rooms, Long memberId) {
		if (memberId == null) {
			return Collections.emptyList();
		}

		return likeRepository.findByRoomInAndMemberId(rooms, memberId);
	}

	@Transactional(readOnly = true)
	public Optional<Likes> getLike(Room room, Long memberId) {
		return likeRepository.findByRoomAndMemberId(room, memberId);
	}

	@Transactional
	public Likes saveLike(Likes likes) {
		return likeRepository.save(likes);
	}

	@Transactional(readOnly = true)
	public Page<Likes> getAllLikes(Long memberId, Pageable pageable) {
		return likeRepository.findAllByMemberIdAndFlagTrue(memberId, pageable);
	}
}
