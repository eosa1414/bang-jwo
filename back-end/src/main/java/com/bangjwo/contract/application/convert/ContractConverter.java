package com.bangjwo.contract.application.convert;

import com.bangjwo.contract.domain.entity.Contract;
import com.bangjwo.contract.domain.entity.LandlordInfo;
import com.bangjwo.contract.domain.entity.TenantInfo;
import com.bangjwo.room.domain.entity.Room;

public class ContractConverter {

	public static Contract convert(Long roomId, LandlordInfo landlordInfo, TenantInfo tenantInfo, String ipfsKey) {
		return new Contract(roomId, landlordInfo, tenantInfo, ipfsKey);
	}

	public static LandlordInfo createLandlordInfo(Room room, Long landlordId) {
		return LandlordInfo.builder()
			.landlordId(landlordId)
			.build();
	}

	public static TenantInfo createTenantInfo(Long tenantId) {
		return TenantInfo.builder()
			.tenantId(tenantId)
			.build();
	}
}
