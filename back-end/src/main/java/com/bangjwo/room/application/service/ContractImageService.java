// package com.bangjwo.room.application.service;
//
// import java.util.List;
// import java.util.UUID;
//
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.multipart.MultipartFile;
//
// import com.bangjwo.contract.domain.entity.Contract;
// import com.bangjwo.contract.domain.entity.ContractImage;
// import com.bangjwo.contract.domain.repository.ContractImageRepository;
// import com.bangjwo.contract.domain.repository.ContractRepository;
// import com.bangjwo.global.common.error.contract.ContractErrorCode;
// import com.bangjwo.global.common.exception.BusinessException;
// import com.bangjwo.global.common.image.FileUploaderPort;
//
// import lombok.RequiredArgsConstructor;
//
// @Service
// @RequiredArgsConstructor
// public class ContractImageService {
//
// 	private static final String CONTRACT_IMAGE_PATH = "contracts/";
//
// 	private final FileUploaderPort fileUploader;
// 	private final ContractImageRepository contractImageRepository;
// 	private final ContractRepository contractRepository;
//
// 	@Transactional
// 	public void uploadAndSaveImages(ContractImageUploadRequestDto requestDto) {
// 		Contract contract = contractRepository.findById(requestDto.getContractId())
// 			.orElseThrow(() -> new BusinessException(ContractErrorCode.NOT_FOUND_CONTRACT));
//
// 		if (!contract.getLandlordId().equals(requestDto.getLandlordId())) {
// 			throw new BusinessException(ContractErrorCode.INVALID_LANDLORD_ACCESS);
// 		}
//
// 		List<MultipartFile> files = requestDto.getImages();
// 		if (files != null) {
// 			for (MultipartFile file : files) {
// 				String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
// 				String imageUrl = fileUploader.upload(file, CONTRACT_IMAGE_PATH, fileName);
//
// 				ContractImage image = ContractImage.builder()
// 					.contract(contract)
// 					.imageUrl(imageUrl)
// 					.build();
// 				contractImageRepository.save(image);
// 			}
// 		}
// 	}
//
// 	@Transactional
// 	public void deleteImage(Long imageId) {
// 		contractImageRepository.deleteById(imageId);
// 	}
//
// 	@Transactional(readOnly = true)
// 	public List<ContractImage> findImagesByContract(Contract contract) {
// 		return contractImageRepository.findAllByContract(contract);
// 	}
// }
