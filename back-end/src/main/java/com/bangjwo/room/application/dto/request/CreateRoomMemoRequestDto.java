package com.bangjwo.room.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomMemoRequestDto {
	private Long memberId;
	private String content;
}
