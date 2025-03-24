package com.bangjwo.room.application.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bangjwo.global.common.error.room.RoomErrorCode;
import com.bangjwo.global.common.exception.BusinessException;
import com.bangjwo.room.domain.entity.Image;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.repository.ImageRepository;
import com.bangjwo.room.domain.repository.RoomRepository;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class ImageService {

	private final ImageRepository imageRepository;
	private final RoomRepository roomRepository;

	private final S3Client s3Client;

	@Value("${aws.s3.bucket}")
	private String bucketName;

	@Value("${aws.region}")
	private String awsRegion;

	/**
	 * S3에 파일 업로드 후 생성된 이미지 URL을 이용해 Image 엔티티를 저장합니다.
	 *
	 * @param room 저장할 Room 엔티티
	 * @param file 업로드할 파일
	 */
	public void uploadAndSaveImage(Room room, MultipartFile file) {
		try {
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

			PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.bucket(bucketName)
				.key(fileName)
				.build();

			s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

			String imageUrl = "https://" + bucketName + ".s3." + awsRegion + ".amazonaws.com/" + fileName;

			Room managedRoom = roomRepository.getReferenceById(room.getRoomId());

			Image image = Image.builder()
				.room(managedRoom)
				.imageUrl(imageUrl)
				.build();

			imageRepository.save(image);
		} catch (IOException e) {
			throw new BusinessException(RoomErrorCode.FAIL_IMAGE_UPLOAD);
		}
	}

	@Transactional
	public void uploadAndSaveImages(Room savedRoom, List<MultipartFile> images) {
		if (images != null) {
			for (MultipartFile file : images) {
				uploadAndSaveImage(savedRoom, file);
			}
		}
	}

	@Transactional
	public void updateImages(Room room, List<Long> deleteImageIds, List<MultipartFile> images) {
		if (deleteImageIds != null) {
			deleteImageIds.forEach(this::deleteById);
		}

		if (images != null) {
			uploadAndSaveImages(room, images);
		}
	}

	@Transactional
	public void deleteById(Long imageId) {
		imageRepository.deleteByImageId(imageId).orElseThrow(
			() -> new BusinessException(RoomErrorCode.NOT_FOUND_SEARCH_ROOM_IMAGE)
		);
	}

	@Transactional(readOnly = true)
	public List<Image> findByRoom(Room room) {
		return imageRepository.findAllByRoom(room);
	}
}
