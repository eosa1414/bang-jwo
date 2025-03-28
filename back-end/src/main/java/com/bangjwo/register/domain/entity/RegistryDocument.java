package com.bangjwo.register.domain.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * MongoDB에 저장할 등기부 문서(등기부등본 PDF 데이터 + 파싱 정보)
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "registry_pdf")
@Schema(description = "등기부 문서 엔티티 (등기부등본 PDF 데이터 및 파싱 정보)")
public class RegistryDocument {

	private final static String JSON_PATH_PREFIX = "https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/hyphen/";
	private final static String PDF_IMG_PATH_PREFIX = "https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/pdf-img/";

	@Id
	@Schema(description = "MongoDB 문서 ID", example = "67e6332b68f0cb1ff92bf31e")
	private String id;

	@Schema(description = "서버 메타데이터")
	private BangJwoData serverData;

	@Schema(description = "실제 등기부등본 상세 데이터")
	private RegistryData data;

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@Schema(description = "서버 메타데이터 (결제, 회원, 매물, S3 파일 경로 정보)")
	public static class BangJwoData {
		@Schema(description = "결제 ID", example = "12")
		private String paymentId;

		@Schema(description = "회원 ID", example = "1")
		private Long memberId;

		@Schema(description = "매물 ID", example = "1")
		private Long roomId;

		@Schema(description = "S3 JSON 파일 상대 경로", example = "hyphen.json")
		private String jsonUrl;

		@Schema(description = "S3 PDF 이미지 파일 상대 경로", example = "registry.pdf")
		private String pdfUrl;

		// getter를 오버라이드하여 풀 URL 반환 (접두어 추가)
		public String getJsonUrl() {
			if (jsonUrl != null && !jsonUrl.startsWith("http")) {
				return JSON_PATH_PREFIX + jsonUrl;
			}
			return jsonUrl;
		}

		public String getPdfUrl() {
			if (pdfUrl != null && !pdfUrl.startsWith("http")) {
				return PDF_IMG_PATH_PREFIX + pdfUrl;
			}
			return pdfUrl;
		}
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@Schema(description = "등기부 데이터 (상세 데이터)")
	public static class RegistryData {
		@Schema(description = "등기부 종류", example = "전부증명서")
		private String kindcls;

		@Schema(description = "생성 일시", example = "2025-03-17T17:36:49.000Z")
		private Date createdAt;

		@Schema(description = "상세 항목 데이터")
		private OutList outList;

		@Schema(description = "XML 출력 내용", example = "<xml>...</xml>")
		private String printXml;

		@Schema(description = "PDF 헥스 문자열")
		private String pdfHexString;

		@Schema(description = "등기 번호", example = "2602-2012-004143")
		private String regtNo;

		@Schema(description = "분류 플래그", example = "A")
		private String clsFlag;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@Schema(description = "등기부 상세 항목 데이터")
	public static class OutList {
		@Schema(description = "과다등기여부", example = "Y")
		private String excessiveRegistration;

		@Schema(description = "중복등기부", example = "N")
		private String duplicateRegistration;

		@Schema(description = "고유번호", example = "2602-2012-004143")
		private String uniqueNumber;

		@Schema(description = "거래가액", example = "980000000")
		private String transactionAmount;

		@Schema(description = "대지권비율", example = "0.5")
		private String landRightRatio;

		@Schema(description = "지번 및 번호", example = "503-3")
		private String lotNumber;

		@Schema(description = "지목 (토지의 용도)", example = "주거용")
		private String landCategory;

		@Schema(description = "면적", example = "51.2㎡")
		private String area;

		@Schema(description = "건물내역", example = "철근콘크리트구조, 3층")
		private String buildingInfo;

		@Schema(description = "소유지분 현황(갑구)")
		private List<OwnerShare> ownerShares;

		@Schema(description = "소유지분 제외한 소유권(갑구)")
		private List<OwnershipExclusion> ownershipExclusions;

		@Schema(description = "근저당(을구)")
		private List<MortgageInfo> mortgageInfos;

		@Schema(description = "표제부 - 토지의 표시")
		private List<Map<String, Object>> landTitle;

		@Schema(description = "표제부 - 1동의 건물의 표시")
		private List<TitleInfo> buildingTitles;

		@Schema(description = "표제부 - 대지권의 목적인 토지의 표시")
		private List<TitleInfo> landPurposeTitles;

		@Schema(description = "표제부 - 전유부분 건물의 표시")
		private List<TitleInfo> exclusiveBuildingTitles;

		@Schema(description = "표제부 - 대지권의 표시")
		private List<TitleInfo> landTitleDisplays;

		@Schema(description = "소유권 관련 사항(갑구)")
		private List<OwnershipDetail> ownershipDetails;

		@Schema(description = "소유권 이외 권리(을구)")
		private List<OtherRightsDetail> otherRights;

		@Schema(description = "권리변동 예정사항", example = "변경 예정")
		private String rightsChangePlan;

		@Schema(description = "열람일시", example = "2025-03-17T17:36:49.000Z")
		private String viewDateTime;

		@Schema(description = "요약 정보")
		private Map<String, Object> summaryInfo;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@Schema(description = "소유지분 현황 (갑구) 항목")
	public static class OwnerShare {
		@Schema(description = "등기 명의인", example = "김태실")
		private String registrantName;
		@Schema(description = "주민등록번호", example = "801102-*******")
		private String residentRegistrationNumber;
		@Schema(description = "최종 지분", example = "100%")
		private String finalShare;
		@Schema(description = "주소", example = "서울특별시 영등포구 도림로112길 24, 301호")
		private String address;
		@Schema(description = "순위 번호", example = "1")
		private String rankNumber;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@Schema(description = "소유지분 제외한 소유권 관련 사항 (갑구) 항목")
	public static class OwnershipExclusion {
		@Schema(description = "순위 번호", example = "1")
		private String rankNumber;
		@Schema(description = "등기 목적", example = "주소변경")
		private String registrationPurpose;
		@Schema(description = "접수 정보", example = "2025-03-17T17:36:49.000Z")
		private String receiptInfo;
		@Schema(description = "주요 등기 사항", example = "주요 사항 내용")
		private String mainRegistrationInfo;
		@Schema(description = "등기사항 밑줄", example = "밑줄 내용")
		private String registrationUnderline;
		@Schema(description = "대상 소유자", example = "김태실")
		private String targetOwner;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@Schema(description = "근저당(을구) 항목")
	public static class MortgageInfo {
		@Schema(description = "순위 번호", example = "1")
		private String rankNumber;
		@Schema(description = "등기 목적", example = "근저당권 설정")
		private String registrationPurpose;
		@Schema(description = "근저당권 설정 정보", example = "설정 정보 내용")
		private String mortgageSetting;
		@Schema(description = "접수 정보", example = "2025-03-17T17:36:49.000Z")
		private String receiptInfo;
		@Schema(description = "주요 등기 사항", example = "주요 등기 사항 내용")
		private String mainRegistrationInfo;
		@Schema(description = "등기사항 밑줄", example = "밑줄 내용")
		private String registrationUnderline;
		@Schema(description = "대상 소유자", example = "김태실")
		private String targetOwner;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@Schema(description = "표제부 정보 항목 (건물, 토지 등)")
	public static class TitleInfo {
		@Schema(description = "표시 번호", example = "1")
		private String displayNumber;
		@Schema(description = "접수", example = "2025-03-17T17:36:49.000Z")
		private String receipt;
		@Schema(description = "소재지번, 건물명칭 및 번호", example = "서울특별시 노원구 공릉동 503-3")
		private String locationAndBuilding;
		@Schema(description = "건물 내역", example = "철근콘크리트구조, 3층")
		private String buildingInfo;
		@Schema(description = "등기원인 및 기타사항", example = "등기원인 내용")
		private String registrationCauseAndEtc;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@Schema(description = "소유권 관련 사항 (갑구) 항목")
	public static class OwnershipDetail {
		@Schema(description = "순위 번호", example = "1")
		private String rankNumber;
		@Schema(description = "등기 목적", example = "소유권 보존")
		private String registrationPurpose;
		@Schema(description = "접수", example = "2025-03-17T17:36:49.000Z")
		private String receipt;
		@Schema(description = "등기 원인", example = "등기 원인 내용")
		private String registrationCause;
		@Schema(description = "권리자 및 기타사항", example = "김태실, 기타사항")
		private String ownerAndDetails;
	}

	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@Schema(description = "소유권 이외의 권리 (을구) 항목")
	public static class OtherRightsDetail {
		@Schema(description = "순위 번호", example = "1")
		private String rankNumber;
		@Schema(description = "등기 목적", example = "기타 권리 설정")
		private String registrationPurpose;
		@Schema(description = "접수", example = "2025-03-17T17:36:49.000Z")
		private String receipt;
		@Schema(description = "등기 원인", example = "기타 원인 내용")
		private String registrationCause;
		@Schema(description = "권리자 및 기타사항", example = "김태실, 기타사항")
		private String ownerAndDetails;
	}
}
