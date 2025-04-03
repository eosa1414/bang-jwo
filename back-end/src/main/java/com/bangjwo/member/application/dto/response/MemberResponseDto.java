package com.bangjwo.member.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "회원 상세 정보 DTO")
public class MemberResponseDto {

	@Schema(description = "회원 고유 ID", example = "1")
	private Long id;

	@Schema(description = "본인 인증 여부", example = "false")
	private Boolean isAuth;

	@Schema(description = "이름", example = "홍길동")
	private String name;

	@Schema(description = "닉네임", example = "길동이짱")
	private String nickName;

	@Schema(description = "생년월일 (yyyy-MM-dd)", example = "1995-07-15")
	private String birthday;

	@Schema(description = "전화번호", example = "010-1234-5678")
	private String phone;

	@Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
	private String profileUrl;
}
