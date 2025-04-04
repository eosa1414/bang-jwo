package com.bangjwo.room.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bangjwo.room.domain.entity.MaintenanceInclude;
import com.bangjwo.room.domain.entity.Room;

public interface MaintenanceIncludeRepository extends JpaRepository<MaintenanceInclude, Long> {
	void deleteByRoom(Room room);

	List<MaintenanceInclude> findAllByRoom(Room room);
}
