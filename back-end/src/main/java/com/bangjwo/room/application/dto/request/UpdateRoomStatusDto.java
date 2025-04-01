package com.bangjwo.room.application.dto.request;

import com.bangjwo.room.domain.vo.RoomStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "매물 상태 변경 임시 요청. 개발 당시 매물 상태를 변경하기 위한 API로 이후 삭제 예정")
public class UpdateRoomStatusDto {
	@Schema(description = "매물 ID", example = "1")
	@Min(1)
	private Long roomId;

	@Schema(description = "변경할 매물 상태", example = "ON_SALE")
	private RoomStatus status;
}