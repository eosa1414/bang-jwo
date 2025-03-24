package com.bangjwo.room.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bangjwo.room.domain.entity.MaintenanceInclude;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.repository.MaintenanceIncludeRepository;
import com.bangjwo.room.domain.vo.MaintenanceIncludeName;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaintenanceIncludeService {

	private final MaintenanceIncludeRepository maintenanceIncludeRepository;

	@Transactional
	public void saveMaintenanceIncludes(Room room, List<MaintenanceIncludeName> maintenanceList) {
		if (maintenanceList != null) {
			for (MaintenanceIncludeName includeName : maintenanceList) {
				MaintenanceInclude mi = MaintenanceInclude.builder()
					.room(room)
					.maintenanceIncludeName(includeName)
					.build();
				maintenanceIncludeRepository.save(mi);
			}
		}

		System.out.println(room.getRoomId());
	}
}
