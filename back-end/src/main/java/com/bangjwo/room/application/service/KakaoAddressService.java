package com.bangjwo.room.application.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bangjwo.global.common.error.room.RoomErrorCode;
import com.bangjwo.global.common.exception.RoomException;
import com.bangjwo.room.domain.vo.KakaoAddressInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoAddressService {

	@Value("${kakao.rest.api.key}")
	private String kakaoApiKey;

	private final WebClient.Builder webClientBuilder;

	/**
	 * 도로명 주소(roadAddress)를 이용해 카카오 API를 호출하고, 응답 데이터에서
	 * 위도, 경도, region_1depth_name, region_2depth_name, region_3depth_name 만을 추출하여 반환합니다.
	 *
	 * @param roadAddress 도로명 주소 (예: "서울특별시 강남구 테헤란로 123")
	 * @return KakaoAddressInfo (응답값이 없으면 예외 출력)
	 */
	public KakaoAddressInfo fetchAddressInfoByRoadName(String roadAddress) {
		WebClient webClient = webClientBuilder.baseUrl("https://dapi.kakao.com").build();

		String authorizationHeader = "KakaoAK " + kakaoApiKey;

		KakaoAddressResponse response = webClient.get()
			.uri(uriBuilder -> uriBuilder
				.path("/v2/local/search/address.json")
				.queryParam("query", roadAddress)
				.build())
			.header("Authorization", authorizationHeader)
			.retrieve()
			.bodyToMono(KakaoAddressResponse.class)
			.block();

		if (response != null && response.documents != null && !response.documents.isEmpty()) {
			KakaoAddressDocument document = response.documents.get(0);
			KakaoAddressData data = document.address;

			BigDecimal lat = new BigDecimal(data.y);
			BigDecimal lng = new BigDecimal(data.x);
			String region1 = data.region_1depth_name;
			String region2 = data.region_2depth_name;
			String region3 = data.region_3depth_name;

			return new KakaoAddressInfo(lat, lng, region1, region2, region3);
		}

		throw new RoomException(RoomErrorCode.KAKAO_PLACE_API_ERROR);
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class KakaoAddressResponse {
		public List<KakaoAddressDocument> documents;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class KakaoAddressDocument {
		public KakaoAddressData address;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class KakaoAddressData {
		public String x; // 경도
		public String y; // 위도
		public String region_1depth_name;
		public String region_2depth_name;
		public String region_3depth_name;
	}
}
