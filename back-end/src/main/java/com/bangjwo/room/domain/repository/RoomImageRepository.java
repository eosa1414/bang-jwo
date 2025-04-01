package com.bangjwo.room.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bangjwo.room.domain.entity.Image;
import com.bangjwo.room.domain.entity.Room;

public interface RoomImageRepository extends JpaRepository<Image, Integer> {
	Optional<Image> deleteByImageId(Long imageId);

	List<Image> findAllByRoom(Room room);

	@Query("""
		    SELECT i
		    FROM Image i
		    WHERE i.room.roomId IN :roomIds
		    AND i.createdAt = (
		        SELECT MAX(i2.createdAt)
		        FROM Image i2
		        WHERE i2.room.roomId = i.room.roomId
		    )
		""")
	List<Image> findMainImagesByRoomIds(@Param("roomIds") List<Long> roomIds);
}
