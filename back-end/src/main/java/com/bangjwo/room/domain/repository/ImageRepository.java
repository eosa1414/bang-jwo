package com.bangjwo.room.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bangjwo.room.domain.entity.Image;
import com.bangjwo.room.domain.entity.Room;

public interface ImageRepository extends JpaRepository<Image, Integer> {
	Optional<Image> deleteByImageId(Long imageId);

	List<Image> findAllByRoom(Room room);

	Image findFirstByRoomOrderByRoomDesc(Room room);
}
