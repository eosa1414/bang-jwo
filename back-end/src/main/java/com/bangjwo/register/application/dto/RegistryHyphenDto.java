package com.bangjwo.register.application.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * S3 하이픈.json을 역직렬화하기 위한 DTO
 * 자바 필드는 영문/카멜케이스로 구성하며,
 * JSON 키에 한글/특수문자 포함된 경우 @JsonProperty 사용
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class RegistryHyphenDto {

	private CommonDto common;
	private DataDto data;

	@Getter
	@Setter
	public static class CommonDto {
		private String userTrNo;
		private String hyphenTrNo;
		private String errYn;
		private String errCd;
		private String errMsg;
	}

	@Getter
	@Setter
	public static class DataDto {
		private String dealNo;
		private String dealDate;
		private String apprNo;
		private String kindcls;
		private OutListDto outList;
		private String printXml;
		private String pdfHexString;
		@JsonProperty("regt_no")
		private String regtNo;
		@JsonProperty("cls_flag")
		private String clsFlag;
	}

	@Getter
	@Setter
	public static class OutListDto {
		@JsonProperty("get과다등기여부")
		private String excessiveReg;
		@JsonProperty("get중복등기부")
		private String duplicateReg;
		@JsonProperty("get고유번호")
		private String uniqueNumber;
		@JsonProperty("get거래가액")
		private String transactionAmount;
		@JsonProperty("get대지권비율")
		private String landRightRatio;
		@JsonProperty("get지번_및_번호")
		private String lotNumber;
		@JsonProperty("get지목")
		private String landCategory;
		@JsonProperty("get면적")
		private String area;
		@JsonProperty("get건물내역")
		private String buildingInfo;

		@JsonProperty("get소유지분현황_갑구")
		private List<OwnerShare> ownerShareList;
		@JsonProperty("get소유지분을_제외한_소유권에_관한_사항_갑구")
		private List<OwnershipExclusion> ownershipExclusionList;
		@JsonProperty("get근_저당권_및_전세권_등_을구")
		private List<MortgageInfo> mortgageInfoList;

		@JsonProperty("get표제부_토지의_표시")
		private List<Map<String, Object>> landTitle;
		@JsonProperty("get표제부_1동의_건물의_표시")
		private List<TitleInfo> buildingTitles;
		@JsonProperty("get표제부_대지권의_목적인_토지의_표시")
		private List<TitleInfo> landPurposeTitles;
		@JsonProperty("get표제부_전유부분_건물의_표시")
		private List<TitleInfo> exclusiveBuildingTitles;
		@JsonProperty("get표제부_대지권의_표시")
		private List<TitleInfo> landTitleDisplays;

		@JsonProperty("get소유권에_관한_사항_갑구")
		private List<OwnershipDetail> ownershipDetailList;
		@JsonProperty("get소유권_이외의_권리에_관한_사항_을구")
		private List<OtherRightsDetail> otherRightsList;

		@JsonProperty("get권리변동_예정사항")
		private String rightsChangePlan;
		@JsonProperty("get열람일시")
		private String viewDateTime;
		@JsonProperty("get요약정보")
		private Map<String, Object> summaryInfo;
	}

	@Getter
	@Setter
	public static class OwnerShare {
		@JsonProperty("get등기명의인")
		private String registrantName;
		@JsonProperty("get주민_등록번호")
		private String residentRegistrationNumber;
		@JsonProperty("get최종지분")
		private String finalShare;
		@JsonProperty("get주소")
		private String address;
		@JsonProperty("get순위번호")
		private String rankNumber;
	}

	@Getter
	@Setter
	public static class OwnershipExclusion {
		@JsonProperty("get순위번호")
		private String rankNumber;
		@JsonProperty("get등기목적")
		private String registrationPurpose;
		@JsonProperty("get접수정보")
		private String receiptInfo;
		@JsonProperty("get주요등기사항")
		private String mainRegistrationInfo;
		@JsonProperty("get주요등기사항밑줄")
		private String registrationUnderline;
		@JsonProperty("get대상소유자")
		private String targetOwner;
	}

	@Getter
	@Setter
	public static class MortgageInfo {
		@JsonProperty("get순위번호")
		private String rankNumber;
		@JsonProperty("get등기목적")
		private String registrationPurpose;
		@JsonProperty("get근저당권설정")
		private String mortgageSetting;
		@JsonProperty("get접수정보")
		private String receiptInfo;
		@JsonProperty("get주요등기사항")
		private String mainRegistrationInfo;
		@JsonProperty("get주요등기사항밑줄")
		private String registrationUnderline;
		@JsonProperty("get대상소유자")
		private String targetOwner;
	}

	@Getter
	@Setter
	public static class TitleInfo {
		@JsonProperty("get표시번호")
		private String displayNumber;
		@JsonProperty("get접수")
		private String receipt;
		@JsonProperty("get소재지번_건물명칭_및_번호")
		private String locationAndBuilding;
		@JsonProperty("get건물내역")
		private String buildingInfo;
		@JsonProperty("get등기원인_및_기타사항")
		private String registrationCauseAndEtc;
	}

	@Getter
	@Setter
	public static class OwnershipDetail {
		@JsonProperty("get순위번호")
		private String rankNumber;
		@JsonProperty("get등기목적")
		private String registrationPurpose;
		@JsonProperty("get접수")
		private String receipt;
		@JsonProperty("get등기원인")
		private String registrationCause;
		@JsonProperty("get권리자_및_기타사항")
		private String ownerAndDetails;
	}

	@Getter
	@Setter
	public static class OtherRightsDetail {
		@JsonProperty("get순위번호")
		private String rankNumber;
		@JsonProperty("get등기목적")
		private String registrationPurpose;
		@JsonProperty("get접수")
		private String receipt;
		@JsonProperty("get등기원인")
		private String registrationCause;
		@JsonProperty("get권리자_및_기타사항")
		private String ownerAndDetails;
	}
}
