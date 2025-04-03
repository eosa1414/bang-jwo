package com.bangjwo.contract.application.dto.request;

import java.time.LocalDate;

import com.bangjwo.contract.application.dto.validation.FinalSave;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "임차인 계약 정보 업데이트 요청 DTO (임시 저장 또는 최종 저장용)")
public class UpdateTenantInfoDto {

	@Schema(description = "계약서 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull
	@Min(1)
	private Long contractId;

	@Schema(description = "이름", example = "김철수")
	@NotNull(groups = FinalSave.class)
	private String name;

	@Schema(description = "전화번호", example = "010-1111-2222")
	@NotNull(groups = FinalSave.class)
	private String phone;

	@Schema(description = "주소", example = "서울특별시 마포구 성산동")
	@NotNull(groups = FinalSave.class)
	private String address;

	@Schema(description = "암호화된 주민등록번호", example = "123456-1234567")
	@NotNull(groups = FinalSave.class)
	private String residentRegistrationNumber;

	@Schema(description = "입주일", example = "2025-05-01")
	@NotNull(groups = FinalSave.class)
	private LocalDate moveInDate;
}
