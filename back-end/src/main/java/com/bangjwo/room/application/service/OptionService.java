package com.bangjwo.room.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.room.domain.entity.Options;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.repository.OptionsRepository;
import com.bangjwo.room.domain.vo.RoomOption;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OptionService {
	private final OptionsRepository optionsRepository;

	@Transactional
	public void saveOptions(Room room, List<RoomOption> optionList) {
		if (optionList != null) {
			for (RoomOption option : optionList) {
				Options optionEntity = Options.builder()
					.room(room)
					.optionName(option)
					.build();
				optionsRepository.save(optionEntity);
			}
		}
	}

	@Transactional
	public void updateOptions(Room room, List<RoomOption> optionList) {
		if (optionList != null && !optionList.isEmpty()) {
			optionsRepository.deleteByRoom(room);
			saveOptions(room, optionList);
		}
	}

	@Transactional(readOnly = true)
	public List<Options> findByRoom(Room room) {
		return optionsRepository.findAllByRoom(room);
	}
}
