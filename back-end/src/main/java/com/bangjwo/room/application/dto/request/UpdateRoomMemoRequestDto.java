package com.bangjwo.room.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoomMemoRequestDto {
	private Long memberId;
	private String content;
}
