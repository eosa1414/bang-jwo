package com.bangjwo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.auth.resolver.MemberHeader;

@RestController
public class TestController {
	@GetMapping
	public String test() {
		return "bangjwo-service";
	}

	@GetMapping("/test")
	public ResponseEntity<String> test2(@MemberHeader Long memberId) {
		return ResponseEntity.ok("Current memberId: " + memberId);
	}
}