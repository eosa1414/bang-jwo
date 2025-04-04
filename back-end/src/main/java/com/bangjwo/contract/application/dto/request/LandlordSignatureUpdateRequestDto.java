package com.bangjwo.contract.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import com.bangjwo.contract.application.validation.NotEmptyMultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "임대인 서명 이미지 업로드 요청 DTO")
public class LandlordSignatureUpdateRequestDto {

	@Schema(description = "계약 ID", example = "1")
	@NotNull
	private Long contractId;

	@Schema(description = "임대인 ID", example = "1")
	@NotNull
	private Long landlordId;

	@Schema(description = "서명 이미지 1", type = "string", format = "binary")
	private MultipartFile signature1;

	@Schema(description = "서명 이미지 2", type = "string", format = "binary")
	private MultipartFile signature2;

	@Schema(description = "서명 이미지 3", type = "string", format = "binary")
	@NotEmptyMultipartFile(message = "서명 이미지 3은 필수 항목입니다.")
	private MultipartFile signature3;

	@Schema(description = "서명 이미지 4", type = "string", format = "binary")
	@NotEmptyMultipartFile(message = "서명 이미지 4는 필수 항목입니다.")
	private MultipartFile signature4;
}
