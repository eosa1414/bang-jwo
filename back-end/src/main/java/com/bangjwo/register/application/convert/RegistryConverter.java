package com.bangjwo.register.application.convert;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bangjwo.register.application.dto.RegistryHyphenDto;
import com.bangjwo.register.application.dto.response.RegistrySummaryDto;
import com.bangjwo.register.domain.entity.RegistryDocument;

public class RegistryConverter {

	/**
	 * RegistryHyphenDto와 RegistryRequestDto를 사용하여 RegistryDocument 엔티티를 빌더 패턴으로 생성합니다.
	 */
	public static RegistryDocument convertToEntity(RegistryHyphenDto dto,
		com.bangjwo.register.application.dto.request.RegistryRequestDto request) {
		// BangJwoData 생성 (요청에서 받은 값들을 그대로 저장)
		RegistryDocument.BangJwoData serverData = RegistryDocument.BangJwoData.builder()
			.paymentId(request.getPaymentId())
			.memberId(request.getMemberId())
			.roomId(request.getRoomId())
			.jsonUrl(request.getJsonUrl())  // 상대 경로 저장
			.pdfUrl(request.getPdfUrl())     // 상대 경로 저장
			.build();

		// RegistryData 생성
		RegistryHyphenDto.DataDto dataDto = dto.getData();
		RegistryDocument.RegistryData regData = RegistryDocument.RegistryData.builder()
			.kindcls(dataDto.getKindcls())
			.createdAt(new Date())
			.printXml(dataDto.getPrintXml())
			.pdfHexString(dataDto.getPdfHexString())
			.regtNo(dataDto.getRegtNo())
			.clsFlag(dataDto.getClsFlag())
			.outList(convertOutList(dataDto.getOutList()))
			.build();

		// 최종 엔티티 생성
		return RegistryDocument.builder()
			.serverData(serverData)
			.data(regData)
			.build();
	}

	private static RegistryDocument.OutList convertOutList(RegistryHyphenDto.OutListDto dto) {
		if (dto == null)
			return null;
		return RegistryDocument.OutList.builder()
			.excessiveRegistration(dto.getExcessiveReg())
			.duplicateRegistration(dto.getDuplicateReg())
			.uniqueNumber(dto.getUniqueNumber())
			.transactionAmount(dto.getTransactionAmount())
			.landRightRatio(dto.getLandRightRatio())
			.lotNumber(dto.getLotNumber())
			.landCategory(dto.getLandCategory())
			.area(dto.getArea())
			.buildingInfo(dto.getBuildingInfo())
			.ownerShares(convertOwnerShareList(dto.getOwnerShareList()))
			.ownershipExclusions(convertOwnershipExclusionList(dto.getOwnershipExclusionList()))
			.mortgageInfos(convertMortgageInfoList(dto.getMortgageInfoList()))
			.landTitle(dto.getLandTitle())
			.buildingTitles(convertTitleInfoList(dto.getBuildingTitles()))
			.landPurposeTitles(convertTitleInfoList(dto.getLandPurposeTitles()))
			.exclusiveBuildingTitles(convertTitleInfoList(dto.getExclusiveBuildingTitles()))
			.landTitleDisplays(convertTitleInfoList(dto.getLandTitleDisplays()))
			.ownershipDetails(convertOwnershipDetailList(dto.getOwnershipDetailList()))
			.otherRights(convertOtherRightsDetailList(dto.getOtherRightsList()))
			.rightsChangePlan(dto.getRightsChangePlan())
			.viewDateTime(dto.getViewDateTime())
			.summaryInfo(dto.getSummaryInfo())
			.build();
	}

	private static List<RegistryDocument.OwnerShare> convertOwnerShareList(List<RegistryHyphenDto.OwnerShare> list) {
		if (list == null)
			return null;
		return list.stream().map(o ->
			RegistryDocument.OwnerShare.builder()
				.registrantName(o.getRegistrantName())
				.residentRegistrationNumber(o.getResidentRegistrationNumber())
				.finalShare(o.getFinalShare())
				.address(o.getAddress())
				.rankNumber(o.getRankNumber())
				.build()
		).collect(Collectors.toList());
	}

	private static List<RegistryDocument.OwnershipExclusion> convertOwnershipExclusionList(
		List<RegistryHyphenDto.OwnershipExclusion> list) {
		if (list == null)
			return null;
		return list.stream().map(o ->
			RegistryDocument.OwnershipExclusion.builder()
				.rankNumber(o.getRankNumber())
				.registrationPurpose(o.getRegistrationPurpose())
				.receiptInfo(o.getReceiptInfo())
				.mainRegistrationInfo(o.getMainRegistrationInfo())
				.registrationUnderline(o.getRegistrationUnderline())
				.targetOwner(o.getTargetOwner())
				.build()
		).collect(Collectors.toList());
	}

	private static List<RegistryDocument.MortgageInfo> convertMortgageInfoList(
		List<RegistryHyphenDto.MortgageInfo> list) {
		if (list == null)
			return null;
		return list.stream().map(m ->
			RegistryDocument.MortgageInfo.builder()
				.rankNumber(m.getRankNumber())
				.registrationPurpose(m.getRegistrationPurpose())
				.mortgageSetting(m.getMortgageSetting())
				.receiptInfo(m.getReceiptInfo())
				.mainRegistrationInfo(m.getMainRegistrationInfo())
				.registrationUnderline(m.getRegistrationUnderline())
				.targetOwner(m.getTargetOwner())
				.build()
		).collect(Collectors.toList());
	}

	private static List<RegistryDocument.TitleInfo> convertTitleInfoList(List<RegistryHyphenDto.TitleInfo> list) {
		if (list == null)
			return null;
		return list.stream().map(t ->
			RegistryDocument.TitleInfo.builder()
				.displayNumber(t.getDisplayNumber())
				.receipt(t.getReceipt())
				.locationAndBuilding(t.getLocationAndBuilding())
				.buildingInfo(t.getBuildingInfo())
				.registrationCauseAndEtc(t.getRegistrationCauseAndEtc())
				.build()
		).collect(Collectors.toList());
	}

	private static List<RegistryDocument.OwnershipDetail> convertOwnershipDetailList(
		List<RegistryHyphenDto.OwnershipDetail> list) {
		if (list == null)
			return null;
		return list.stream().map(o ->
			RegistryDocument.OwnershipDetail.builder()
				.rankNumber(o.getRankNumber())
				.registrationPurpose(o.getRegistrationPurpose())
				.receipt(o.getReceipt())
				.registrationCause(o.getRegistrationCause())
				.ownerAndDetails(o.getOwnerAndDetails())
				.build()
		).collect(Collectors.toList());
	}

	private static List<RegistryDocument.OtherRightsDetail> convertOtherRightsDetailList(
		List<RegistryHyphenDto.OtherRightsDetail> list) {
		if (list == null)
			return null;
		return list.stream().map(o ->
			RegistryDocument.OtherRightsDetail.builder()
				.rankNumber(o.getRankNumber())
				.registrationPurpose(o.getRegistrationPurpose())
				.receipt(o.getReceipt())
				.registrationCause(o.getRegistrationCause())
				.ownerAndDetails(o.getOwnerAndDetails())
				.build()
		).collect(Collectors.toList());
	}

	/**
	 * RegistryDocument 엔티티를 RegistrySummaryDto로 변환합니다.
	 */
	public static RegistrySummaryDto convertSummary(RegistryDocument document) {
		RegistryDocument.BangJwoData data = document.getServerData();
		return RegistrySummaryDto.builder()
			.id(document.getId())
			.paymentId(data.getPaymentId())
			.memberId(data.getMemberId())
			.roomId(data.getRoomId())
			.jsonUrl(data.getJsonUrl())
			.pdfUrl(data.getPdfUrl())
			.build();
	}
}
