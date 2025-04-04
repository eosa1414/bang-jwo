package com.bangjwo.contract.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bangjwo.contract.domain.vo.MonthlyRentType;
import com.bangjwo.room.domain.vo.ContractType;
import com.bangjwo.room.domain.vo.LeaseType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "임대인 계약 정보 응답 DTO")
public class LandlordInfoResponseDto {

	@Schema(description = "임대인 이름", example = "홍길동")
	private String name;

	@Schema(description = "임대인 전화번호", example = "010-1234-5678")
	private String phoneNumber;

	@Schema(description = "임대인 주소", example = "서울특별시 강남구 역삼동 123-45")
	private String address;

	@Schema(description = "암호화된 주민등록번호", example = "123456-1234567")
	private String residentRegistrationNumber;

	@Schema(description = "임대 부동산 주소", example = "서울특별시 강남구 역삼동 123-45 아파트")
	private String rentalPropertyAddress;

	@Schema(description = "부동산 구조", example = "철근콘크리트")
	private String propertyStructure;

	@Schema(description = "부동산 용도", example = "공동주택")
	private String propertyPurpose;

	@Schema(description = "부동산 면적 (평방미터)", example = "85.50")
	private BigDecimal propertyArea;

	@Schema(description = "우선 확정 일자 여부", example = "true")
	private Boolean priorityConfirmedDateYn;

	@Schema(description = "체납 여부", example = "false")
	private Boolean taxArrears;

	@Schema(description = "임대 주택 토지 유형", example = "대지")
	private String rentalHousingLandType;

	@Schema(description = "임대 주택 토지 면적", example = "120.00")
	private BigDecimal rentalHousingLandArea;

	@Schema(description = "임대 부분 주소", example = "서울특별시 강남구 역삼동 123-45")
	private String rentalPartAddress;

	@Schema(description = "임대 부분 상세 주소 (동, 층, 호)", example = "5동 5층 502호")
	private String rentalPartDetailAddress;

	@Schema(description = "임대 부분 면적", example = "25.00")
	private BigDecimal rentalPartArea;

	@Schema(description = "계약 유형", example = "NEW", allowableValues = {"NEW", "RENEW_BY_AGREEMENT", "EXTENSION"})
	private ContractType contractType;

	@Schema(description = "갱신 전 임대차 계약 시작일", example = "2023-04-01")
	private LocalDate previousLeaseStartDate;

	@Schema(description = "갱신 전 임대차 계약 종료일", example = "2025-03-31")
	private LocalDate previousLeaseEndDate;

	@Schema(description = "갱신 전 보증금", example = "30000000")
	private Long previousDepositAmount;

	@Schema(description = "갱신 전 차임(월세)", example = "1200000")
	private Long previousMonthlyRent;

	@Schema(description = "임대 유형", example = "MONTHLY_WITH_DEPOSIT", allowableValues = {"MONTHLY_WITH_DEPOSIT",
		"PURE_MONTHLY"})
	private LeaseType leaseType;

	@Schema(description = "보증금 금액", example = "50000000")
	private Long depositAmount;

	@Schema(description = "월세 금액", example = "1500000")
	private Long monthlyRent;

	@Schema(description = "임대 시작일", example = "2025-04-01")
	private LocalDate leaseStartDate;

	@Schema(description = "임대 종료일", example = "2035-03-31")
	private LocalDate leaseEndDate;

	@Schema(description = "계약금", example = "5000000")
	private Integer contractFee;

	@Schema(description = "중도금", example = "2000000")
	private Integer middleFee;

	@Schema(description = "계약금 지급일", example = "2025-03-25")
	private LocalDate downPaymentDate;

	@Schema(description = "중도금 지급일", example = "2025-04-15")
	private LocalDate interimPaymentDate;

	@Schema(description = "잔금", example = "10000000")
	private Integer balance;

	@Schema(description = "잔금 지급일", example = "2025-05-01")
	private LocalDate balancePaymentDate;

	@Schema(description = "월세 지급일 (일)", example = "15")
	private String monthlyRentPaymentDate;

	@Schema(description = "월세 선불/후불 여부", example = "PREPAID", allowableValues = {"PREPAID", "POSTPAID"})
	private MonthlyRentType monthlyRentType;

	@Schema(description = "고정 관리비", example = "500000")
	private Integer fixedManagementFee;

	@Schema(description = "변동 관리비", example = "여름에는 2만원 추가로 더 받습니다.")
	private String unfixedManagementFee;

	@Schema(description = "월세 입금 은행", example = "국민은행")
	private String monthlyRentAccountBank;

	@Schema(description = "월세 입금 계좌번호", example = "123-456-7890")
	private String monthlyRentAccountNumber;

	@Schema(description = "시설 수리 여부", example = "true")
	private Boolean facilitiesRepairStatus;

	@Schema(description = "시설 수리 내용", example = "전기, 수도 수리")
	private String facilitiesRepairContent;

	@Schema(description = "수리 완료 시기 - 잔금 지급 기일", example = "2025-04-30")
	private LocalDate repairCompletionByBalanceDate;

	@Schema(description = "수리 완료 시기 - 기타", example = "입주 전까지 완료 예정")
	private String repairCompletionEtc;

	@Schema(description = "미수리 시 잔금 지급 기일", example = "2025-05-05")
	private LocalDate notRepairedByBalanceDate;

	@Schema(description = "미수리 시 기타", example = "수리 완료 전까지 전세금 일부 유보")
	private String notRepairedEtc;

	@Schema(description = "임대인 부담 사항", example = "관리비 일부")
	private String landlordBurden;

	@Schema(description = "임차인 부담 사항", example = "임대료")
	private String tenantBurden;

	@Schema(description = "계약서 작성일", example = "2025-03-30")
	private LocalDate contractWrittenDate;

	@Schema(description = "임대인 서명 URL 1", example = "https://example.com/sign1.png", nullable = true)
	private String landlordSignatureUrl1;

	@Schema(description = "임대인 서명 URL 2", example = "https://example.com/sign2.png", nullable = true)
	private String landlordSignatureUrl2;

	@Schema(description = "임대인 서명 URL 3", example = "https://example.com/sign3.png", nullable = true)
	private String landlordSignatureUrl3;

	@Schema(description = "임대인 서명 URL 4", example = "https://example.com/sign4.png", nullable = true)
	private String landlordSignatureUrl4;
}
