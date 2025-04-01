package com.bangjwo.contract.application.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.bangjwo.contract.application.dto.validation.FinalSave;
import com.bangjwo.room.domain.vo.ContractType;
import com.bangjwo.room.domain.vo.LeaseType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "임대인 계약 정보 업데이트 요청 DTO (임시저장 또는 최종 저장용)")
public class UpdateLandlordInfoDto {

	@Schema(description = "수정하는 계약서 ID", example = "1")
	@NotNull
	@Min(1)
	private Long contractId;

	@Schema(description = "임대인 유저 ID", example = "1")
	@NotNull
	@Min(1)
	private Long landlordId;    // 해당 임대인 유저 ID는 로그인 이후 삭제 예정

	@Schema(description = "임대인 이름", example = "홍길동")
	@NotNull(groups = FinalSave.class)
	private String name;

	@Schema(description = "임대인 전화번호", example = "010-1234-5678")
	@NotNull(groups = FinalSave.class)
	private String phoneNumber;

	@Schema(description = "임대인 주소", example = "서울특별시 강남구 역삼동 123-45")
	@NotNull(groups = FinalSave.class)
	private String address;

	@Schema(description = "암호화된 주민등록번호", example = "123456-1234567")
	@NotNull(groups = FinalSave.class)
	private String residentRegistrationNumber;

	@Schema(description = "임대 부동산 주소", example = "서울특별시 강남구 역삼동 123-45 아파트")
	@NotNull(groups = FinalSave.class)
	private String rentalPropertyAddress;

	@Schema(description = "부동산 구조", example = "아파트")
	@NotNull(groups = FinalSave.class)
	private String propertyStructure;

	@Schema(description = "부동산 용도", example = "주거용")
	@NotNull(groups = FinalSave.class)
	private String propertyPurpose;

	@Schema(description = "부동산 면적 (평방미터)", example = "85.50")
	@NotNull(groups = FinalSave.class)
	private BigDecimal propertyArea;

	@Schema(description = "수리 책임 내용", example = "임대인이 수리 책임을 집니다.")
	@NotNull(groups = FinalSave.class)
	private String repairResponsibility;

	@Schema(description = "우선 확정 일자 여부", example = "false")
	@NotNull(groups = FinalSave.class)
	private Boolean priorityConfirmedDateYn;

	@Schema(description = "체납 여부", example = "false")
	@NotNull(groups = FinalSave.class)
	private Boolean taxArrears;

	@Schema(description = "임대 주택 위치", example = "서울특별시 강남구")
	@NotNull(groups = FinalSave.class)
	private String locationOfRentalHousing;

	@Schema(description = "임대 주택 토지 유형", example = "대지")
	@NotNull(groups = FinalSave.class)
	private String rentalHousingLandType;

	@Schema(description = "임대 주택 토지 면적", example = "120.00")
	@NotNull(groups = FinalSave.class)
	private BigDecimal rentalHousingLandArea;

	@Schema(description = "임대 주택 사용 용도", example = "주거")
	@NotNull(groups = FinalSave.class)
	private String rentalHousingUsage;

	@Schema(description = "임대 주택 면적", example = "85.50")
	@NotNull(groups = FinalSave.class)
	private BigDecimal rentalHousingArea;

	@Schema(description = "임대 부분 주소", example = "서울특별시 강남구 역삼동 123-45")
	@NotNull(groups = FinalSave.class)
	private String rentalPartAddress;

	@Schema(description = "임대 부분 면적", example = "25.00")
	@NotNull(groups = FinalSave.class)
	private BigDecimal rentalPartArea;

	@Schema(
		description = "계약 유형 (최종저장 시 필수)",
		example = "NEW",
		allowableValues = {"신규 계약", "합의에 의한 재계약", "갱신계약"}
	)
	@NotNull(groups = FinalSave.class)
	private ContractType contractType;

	@Schema(
		description = "임대 유형 (최종저장 시 필수)",
		example = "MONTHLY_WITH_DEPOSIT",
		allowableValues = {"보증금 있는 월세", "월세"}
	)
	@NotNull(groups = FinalSave.class)
	private LeaseType leaseType;

	@Schema(description = "보증금 금액", example = "50000000")
	@NotNull(groups = FinalSave.class)
	private Long depositAmount;

	@Schema(description = "월세 금액", example = "1500000")
	@NotNull(groups = FinalSave.class)
	private Long monthlyRent;

	@Schema(description = "임대 시작일", example = "2025-04-01")
	@NotNull(groups = FinalSave.class)
	private LocalDate leaseStartDate;

	@Schema(description = "임대 종료일", example = "2035-03-31")
	@NotNull(groups = FinalSave.class)
	private LocalDate leaseEndDate;

	@Schema(description = "계약금", example = "5000000")
	@NotNull(groups = FinalSave.class)
	private Integer contractFee;

	@Schema(description = "중도금", example = "2000000")
	@NotNull(groups = FinalSave.class)
	private Integer middleFee;

	@Schema(description = "계약금 지급일", example = "2025-03-25")
	@NotNull(groups = FinalSave.class)
	private LocalDate downPaymentDate;

	@Schema(description = "중도금 지급일", example = "2025-04-15")
	@NotNull(groups = FinalSave.class)
	private LocalDate interimPaymentDate;

	@Schema(description = "잔금", example = "10000000")
	@NotNull(groups = FinalSave.class)
	private Integer balance;

	@Schema(description = "잔금 지급일", example = "2025-05-01")
	private LocalDate balancePaymentDate;

	@Schema(description = "월세 지급일 (일)", example = "15")
	@NotNull(groups = FinalSave.class)
	private String monthlyRentPaymentDate;

	@Schema(description = "고정 관리비", example = "500000")
	@NotNull(groups = FinalSave.class)
	private Integer fixedManagementFee;

	@Schema(description = "변동 관리비", example = "300000")
	@NotNull(groups = FinalSave.class)
	private String unfixedManagementFee;

	@Schema(description = "월세 입금 은행", example = "국민은행")
	@NotNull(groups = FinalSave.class)
	private String monthlyRentAccountBank;

	@Schema(description = "월세 입금 계좌번호", example = "123-456-7890")
	@NotNull(groups = FinalSave.class)
	private String monthlyRentAccountNumber;

	@Schema(description = "시설 수리 여부", example = "true")
	@NotNull(groups = FinalSave.class)
	private Boolean facilitiesRepairStatus;

	@Schema(description = "시설 수리 내용", example = "전기, 수도 수리")
	@NotNull(groups = FinalSave.class)
	private String facilitiesRepairContent;

	@Schema(description = "임대인 부담 사항", example = "관리비 일부")
	@NotNull(groups = FinalSave.class)
	private String landlordBurden;

	@Schema(description = "임차인 부담 사항", example = "임대료")
	@NotNull(groups = FinalSave.class)
	private String tenantBurden;

	@Schema(description = "전입신고일자", example = "2025-05-01")
	@NotNull(groups = FinalSave.class)
	private LocalDate moveInRegistrationDate;

	@Schema(description = "미납금", example = "200000")
	@NotNull(groups = FinalSave.class)
	private Integer unpaidAmount;

	@Schema(description = "분쟁 조정위원회 소정 여부", example = "true")
	@NotNull(groups = FinalSave.class)
	private Boolean disputeResolution;

	@Schema(description = "주택 철거 및 재건축 계획 여부", example = "false")
	@NotNull(groups = FinalSave.class)
	private Boolean isHousingReconstructionPlanned;

	@Schema(description = "철거 및 재건축 시 공사 시기", example = "2030-01-01")
	private LocalDate constructionPeriod;

	@Schema(description = "철거 및 재건축 시 소요 기간", example = "12")
	private Integer estimatedConstructionDuration;

	@Schema(description = "상세 주소 제공 동의 여부", example = "true")
	@NotNull(groups = FinalSave.class)
	private Boolean isDetailedAddressConsentGiven;

	@Schema(description = "기타 특약 사항 리스트", example = "[\"현 상태 유지 조건\", \"애완동물 금지\"]")
	private List<String> etc;
}
