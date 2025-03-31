package com.bangjwo.blockchain.presentation;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.bangjwo.blockchain.application.service.BlockchainService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/blockchain")
@RequiredArgsConstructor
public class DocumentContractController {

	private final BlockchainService contractService;

	@GetMapping("/compare")
	public String getContract(@RequestParam BigInteger contractId, @RequestParam BigInteger requesterId) {
		// 서버의 값과 비교하여 결과 반환 구현
		return contractService.getContractData(contractId, requesterId);
	}

	// 테스트용 추후 삭제
	@PostMapping("/registerContract")
	public CompletableFuture<TransactionReceipt> registerContract(@RequestParam BigInteger contractId,
		@RequestParam String ipfsHash,
		@RequestParam BigInteger landlord,
		@RequestParam BigInteger tenant) {
		return contractService.registerContract(contractId, ipfsHash, landlord, tenant);
	}

	// @PostMapping("/registerContract")
	// public TransactionReceipt registerContract(@RequestParam BigInteger id,
	// 	@RequestParam String ipfsHash,
	// 	@RequestParam BigInteger landlord,
	// 	@RequestParam BigInteger tenant)
	// 	throws Exception {
	// 	return contractService.registerContract(id, ipfsHash, landlord, tenant);
	// }
}
