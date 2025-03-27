package com.bangjwo.room.application.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.bangjwo.room.domain.entity.Address;
import com.bangjwo.room.domain.entity.Room;
import com.bangjwo.room.domain.vo.RoomAreaType;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Subquery;

public class RoomSpecification {

	public static Specification<Room> monthlyRentLessThanOrEqual(Integer price) {
		return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("monthlyRent"), price);
	}

	public static Specification<Room> exclusiveAreaIn(List<RoomAreaType> areaTypes) {
		return (root, query, cb) -> {
			// areaTypes가 null이거나 비어있거나 ALL이 포함되면 조건 없이 전체 조회
			if (areaTypes == null || areaTypes.isEmpty() || areaTypes.contains(RoomAreaType.ALL)) {
				return cb.conjunction();
			}
			final BigDecimal conversionFactor = BigDecimal.valueOf(3.3); // 1평 = 3.3 m²
			List<Predicate> predicates = new ArrayList<>();
			for (RoomAreaType areaType : areaTypes) {
				BigDecimal min = areaType.getMin();
				BigDecimal max = areaType.getMax();
				// 평 단위를 m²로 변환
				if (min != null && max != null) {
					BigDecimal minM2 = min.multiply(conversionFactor);
					BigDecimal maxM2 = max.multiply(conversionFactor);
					predicates.add(cb.between(root.get("exclusiveArea"), minM2, maxM2));
				} else if (min != null) {
					BigDecimal minM2 = min.multiply(conversionFactor);
					predicates.add(cb.greaterThanOrEqualTo(root.get("exclusiveArea"), minM2));
				} else if (max != null) {
					BigDecimal maxM2 = max.multiply(conversionFactor);
					predicates.add(cb.lessThanOrEqualTo(root.get("exclusiveArea"), maxM2));
				}
			}
			// OR 조건으로 결합
			return cb.or(predicates.toArray(new Predicate[0]));
		};
	}

	public static Specification<Room> roomInAddressBounds(BigDecimal minLat, BigDecimal maxLat, BigDecimal minLng,
		BigDecimal maxLng) {
		return (root, query, cb) -> {
			// Address 엔티티에 대한 서브쿼리 작성
			Subquery<Long> subquery = query.subquery(Long.class);
			var addressRoot = subquery.from(Address.class);
			subquery.select(addressRoot.get("room").get("roomId"))
				.where(
					cb.between(addressRoot.get("lat"), minLat, maxLat),
					cb.between(addressRoot.get("lng"), minLng, maxLng)
				);

			return root.get("roomId").in(subquery);
		};
	}
}
