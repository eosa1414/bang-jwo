package com.bangjwo.contract.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import com.bangjwo.contract.application.validation.NotEmptyMultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "임차인 서명 이미지 업로드 요청 DTO")
public class TenantSignatureUpdateRequestDto {

	@Schema(description = "계약 ID", example = "1")
	@NotNull
	private Long contractId;

	@Schema(description = "임차인 ID", example = "2")
	@NotNull
	private Long tenantId;

	@Schema(description = "서명 이미지", type = "string", format = "binary")
	@NotEmptyMultipartFile(message = "서명 이미지는 필수 항목입니다.")
	private MultipartFile signature;
}