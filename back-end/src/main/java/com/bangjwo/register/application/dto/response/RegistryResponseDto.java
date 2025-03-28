package com.bangjwo.register.application.dto.response;

import com.bangjwo.register.domain.entity.RegistryDocument;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 등기부 문서 응답 DTO
 * <p>
 * 이 DTO는 MongoDB에 저장된 RegistryDocument 엔티티 데이터를 감싸서 반환합니다.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@Schema(description = "등기부 문서 응답 DTO, 저장된 등기부 문서 데이터를 포함합니다.")
public class RegistryResponseDto {

	@Schema(description = "저장된 등기부 문서 데이터")
	private RegistryDocument registryDocument;
}
