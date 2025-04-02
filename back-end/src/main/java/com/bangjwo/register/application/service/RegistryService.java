package com.bangjwo.register.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bangjwo.global.common.page.PageResponse;
import com.bangjwo.global.common.page.PaginationRequest;
import com.bangjwo.register.application.convert.RegistryConverter;
import com.bangjwo.register.application.dto.RegistryHyphenDto;
import com.bangjwo.register.application.dto.request.RegistryRequestDto;
import com.bangjwo.register.application.dto.response.AnalysisResultDto;
import com.bangjwo.register.application.dto.response.RegistrySummaryDto;
import com.bangjwo.register.application.dto.response.RiskDetailDto;
import com.bangjwo.register.domain.entity.RegistryDocument;
import com.bangjwo.register.domain.repository.RegistryDocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistryService {

	private final HyphenParsingInterface hyphenParsing;
	private final RegistryDocumentRepository registryRepo;

	// S3 폴더 접두어 (JSON만 필요)
	private static final String JSON_FOLDER = "hyphen/";

	/**
	 * S3에서 JSON을 파싱한 후, RegistryRequestDto의 추가 정보를 반영하여
	 * RegistryDocument 엔티티로 변환(RegistryConverter 사용) 후 저장하고,
	 * RegistryResponseDto로 반환합니다.
	 *
	 * @param request 등기부 등록 요청 DTO (결제ID, 회원ID, 매물ID, JSON URL, PDF URL 포함)
	 * @return 변환된 RegistryResponseDto
	 */
	public void parseAndSave(RegistryRequestDto request) {
		// S3 JSON URL을 올바른 키로 변환
		String jsonKey = request.getJsonUrl();
		if (!jsonKey.startsWith(JSON_FOLDER)) {
			jsonKey = JSON_FOLDER + jsonKey;
		}
		RegistryHyphenDto dto = hyphenParsing.parseHyphenJson(jsonKey);

		// 변환 로직을 RegistryConverter로 위임
		RegistryDocument doc = RegistryConverter.convertToEntity(dto, request);
		registryRepo.save(doc);
	}

	/**
	 * 주어진 ID로 RegistryDocument를 조회하고, RegistryResponseDto로 반환합니다.
	 *
	 * @param id 등기부 문서 ID
	 * @return RegistryResponseDto (문서가 없으면 null 반환)
	 */
	public RegistryDocument findById(String id) {
		return registryRepo.findById(id).orElse(null);
	}

	/**
	 * 특정 회원(memberId)의 등기부 등록 정보 요약을 페이지네이션 형태로 조회하여 반환합니다.
	 *
	 * @param memberId 회원 ID
	 * @param page     조회할 페이지 번호 (1부터 시작)
	 * @return PageResponse containing RegistrySummaryDto 리스트
	 */
	public PageResponse<RegistrySummaryDto> getRegistrySummariesByMemberId(Long memberId, Integer page) {
		Pageable pageable = PaginationRequest.toPageable(page);
		Page<RegistryDocument> docs = registryRepo.findByServerDataMemberId(memberId, pageable);
		List<RegistrySummaryDto> dtos = docs.getContent().stream()
			.map(RegistryConverter::convertSummary)
			.collect(Collectors.toList());
		return new PageResponse<>((int)docs.getTotalElements(), docs.getNumber() + 1, docs.getSize(), dtos);
	}

	public RegistryDocument findByRoomId(Long roomId) {
		RegistryDocument registry = registryRepo.findByServerDataRoomId(roomId);
		if (registry == null) {
			throw new RuntimeException("해당 매물에 대한 등기부 정보가 존재하지 않습니다. Room ID: " + roomId);
		}

		return registry;
	}

	public AnalysisResultDto analyze(RegistryHyphenDto dto) {
		AnalysisResultDto result = new AnalysisResultDto();
		result.setDetails(new java.util.HashMap<>());

		RegistryHyphenDto.DataDto data = dto.getData();
		if (data == null || data.getOutList() == null) {
			result.getDetails().put("error", "입력 데이터의 outList가 존재하지 않습니다.");
			return result;
		}
		RegistryHyphenDto.OutListDto outList = data.getOutList();

		// 이미지에 나온 항목들만 체크:
		// 1. 가처분
		analyzeExclusionItem(result, outList, "가처분", "가처분",
			"가처분 항목은 소유권 처분 제한을 의미하며, 존재할 경우 소유권 이전에 제약이 있을 수 있습니다.");

		// 2. 가압류
		analyzeExclusionItem(result, outList, "가압류", "가압류",
			"가압류는 채권자가 재산 처분을 막기 위해 설정하는 임시 조치로, 존재할 경우 재산 처분에 제약이 따릅니다.");

		// 3. 압류
		analyzeExclusionItem(result, outList, "압류", "압류",
			"압류는 국세 체납 등으로 인한 강제 조치로, 존재할 경우 재산 압류 위험이 있습니다.");

		// 4. 가등기
		analyzeExclusionItem(result, outList, "가등기", "가등기",
			"가등기는 향후 소유권 이전 예약을 위한 등기로, 미이행 시 소유권 이전에 문제가 생길 수 있습니다.");

		// 5. 경매개시결정
		analyzeExclusionItem(result, outList, "경매", "경매",
			"경매개시결정이 있으면 해당 부동산이 강제 경매에 들어갈 가능성이 있으므로 매우 위험합니다.");

		// 6. 임차권등기
		analyzeExclusionItem(result, outList, "임차권", "임차권등기",
			"임차권등기는 임차인의 권리를 보호하기 위한 등기이지만, 존재할 경우 소유권 행사에 제한이 있을 수 있습니다.");

		// 7. 신탁부동산
		analyzeExclusionItem(result, outList, "신탁", "신탁부동산",
			"신탁부동산은 신탁 조건에 따라 소유권 행사에 제약이 있을 수 있으므로 주의가 필요합니다.");

		// 8. 민간임대주택등기 (갑구에 포함)
		analyzeExclusionItem(result, outList, "민간임대주택등기", "민간임대주택등기",
			"민간임대주택등기는 임대사업자의 의무를 포함하며, 해당 조건에 따라 임대료 제한 등 추가 위험이 존재할 수 있습니다.");

		// 9. 근저당권 – mortgageInfoList로 판단
		if (outList.getMortgageInfoList() != null && !outList.getMortgageInfoList().isEmpty()) {
			result.getDetails().put("근저당권", "총 " + outList.getMortgageInfoList().size() + "건 등록됨");
			StringBuilder mortgageSummary = new StringBuilder();
			for (RegistryHyphenDto.MortgageInfo mi : outList.getMortgageInfoList()) {
				mortgageSummary.append(mi.getRegistrationPurpose()).append(" (")
					.append(mi.getReceiptInfo()).append("), ");
			}
			result.addRiskDetail(RiskDetailDto.builder()
				.category("근저당권")
				.info(mortgageSummary.toString())
				.riskLevel("중간")
				.description("근저당권은 대출 상환 불이행 시 부동산 처분 위험을 내포합니다.")
				.build());
		} else {
			result.getDetails().put("근저당권", "없음");
			result.addRiskDetail(RiskDetailDto.builder()
				.category("근저당권")
				.info("없음")
				.riskLevel("낮음")
				.description("근저당권 설정 기록이 없습니다.")
				.build());
		}

		// 메타 정보: 열람일시, 요약정보
		result.getDetails().put("열람일시", outList.getViewDateTime());
		result.getDetails().put("요약정보", outList.getSummaryInfo().toString());

		return result;
	}

	/**
	 * ownershipExclusionList 내에서 특정 키워드가 존재하는지 확인하고, 그 결과에 따라 details와 riskDetails를 추가
	 *
	 * @param result     분석 결과 DTO
	 * @param outList    OutListDto
	 * @param keyword    검색할 키워드 (대소문자 구분 없이 포함 여부 체크)
	 * @param category   결과에 기록할 항목명
	 * @param description 위험 설명 (항목이 존재할 경우)
	 */
	private void analyzeExclusionItem(AnalysisResultDto result,
		RegistryHyphenDto.OutListDto outList,
		String keyword,
		String category,
		String description) {
		boolean exists = false;
		if (outList.getOwnershipExclusionList() != null) {
			exists = outList.getOwnershipExclusionList().stream()
				.anyMatch(e -> e.getRegistrationPurpose() != null && e.getRegistrationPurpose().contains(keyword));
		}
		result.getDetails().put(category, exists ? "존재함" : "없음");

		if (exists) {
			// 위험 수준은 단순 예시: 경매는 매우 높음, 가압류/압류는 높음, 나머지는 중간
			String riskLevel = "중간";
			if (keyword.equals("경매")) {
				riskLevel = "매우 높음";
			} else if (keyword.equals("가압류") || keyword.equals("압류")) {
				riskLevel = "높음";
			}
			result.addRiskDetail(RiskDetailDto.builder()
				.category(category)
				.info("해당 항목 기록 있음")
				.riskLevel(riskLevel)
				.description(description)
				.build());
		} else {
			result.addRiskDetail(RiskDetailDto.builder()
				.category(category)
				.info("기록 없음")
				.riskLevel("낮음")
				.description("해당 항목에 관한 등기 기록이 없습니다.")
				.build());
		}
	}
}
