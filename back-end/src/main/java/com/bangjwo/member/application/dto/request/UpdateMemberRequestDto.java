package com.bangjwo.member.application.dto.request;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회원 정보 수정 요청 DTO")
public class UpdateMemberRequestDto {

	@Schema(description = "변경할 닉네임", example = "길동핑", required = false)
	private String nickname;

	@Schema(description = "프로필 이미지 파일 (multipart/form-data 형식)", type = "string", format = "binary", required = false)
	private MultipartFile profileImage;

}
