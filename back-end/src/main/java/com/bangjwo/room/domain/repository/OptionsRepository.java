package com.bangjwo.room.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bangjwo.room.domain.entity.Options;
import com.bangjwo.room.domain.entity.Room;

public interface OptionsRepository extends JpaRepository<Options, Integer> {
	void deleteByRoom(Room room);

	List<Options> findAllByRoom(Room room);
}
