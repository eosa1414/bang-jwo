package com.bangjwo.contract.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.contract.application.dto.request.CreateContractRequestDto;
import com.bangjwo.contract.application.service.ContractService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/contract")
@RequiredArgsConstructor
public class ContractController {
	private final ContractService contractService;

	@PostMapping
	public ResponseEntity<Long> createContract(@RequestBody CreateContractRequestDto requestDto) {
		Long contractId = contractService.createContract(requestDto);
		return ResponseEntity.ok(contractId);
	}
}
