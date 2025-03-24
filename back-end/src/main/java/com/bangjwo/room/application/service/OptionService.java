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

		System.out.println(room.getRoomId());
	}
}
