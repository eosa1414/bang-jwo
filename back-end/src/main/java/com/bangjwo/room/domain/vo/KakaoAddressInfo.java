package com.bangjwo.room.domain.vo;

import java.math.BigDecimal;

public record KakaoAddressInfo(
	BigDecimal lat,
	BigDecimal lng,
	String region1depthName,
	String region2depthName,
	String region3depthName
) {
}
