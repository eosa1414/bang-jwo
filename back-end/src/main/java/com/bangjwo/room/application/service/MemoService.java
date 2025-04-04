package com.bangjwo.room.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.room.domain.entity.Memo;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.repository.MemoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemoService {
	private final MemoRepository memoRepository;

	@Transactional(readOnly = true)
	public Optional<Memo> findByRoomAndMemberId(Room room, Long memberId) {
		return memoRepository.findByRoomAndMemberId(room, memberId);
	}

	@Transactional
	public void saveMemo(Memo memo) {
		memoRepository.save(memo);
	}
}
