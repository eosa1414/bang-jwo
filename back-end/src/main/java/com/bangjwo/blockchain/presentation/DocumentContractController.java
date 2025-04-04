package com.bangjwo.blockchain.presentation;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bangjwo.auth.resolver.MemberHeader;
import com.bangjwo.blockchain.application.service.BlockchainService;
import com.bangjwo.contract.application.service.ContractService;
import com.bangjwo.global.common.error.blockchain.BlockchainErrorCode;
import com.bangjwo.global.common.exception.BusinessException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/blockchain")
@RequiredArgsConstructor
public class DocumentContractController {

	private final BlockchainService blockchainService;
	private final ContractService contractService;

	@Operation(
		summary = "블록체인과 확인",
		description = "블록체인에 저장된 IPFS값과 비교",
		security = @SecurityRequirement(name = "JWT")
	)
	@GetMapping("/compare")
	public ResponseEntity<Map<String, Boolean>> getContract(@RequestParam BigInteger contractId,
		@MemberHeader BigInteger memberId) {
		// 서버의 값과 비교하여 결과 반환 구현
		String onBlockchain = blockchainService.getContractData(contractId, memberId);
		if (onBlockchain == null) {
			throw new BusinessException(BlockchainErrorCode.BLOCKCHAIN_NOT_FOUND);
		}
		String onDatabae = contractService.findContract(contractId.longValue()).getIpfsKey();
		return ResponseEntity.ok(Map.of("isMatch", onBlockchain.equals(onDatabae)));
	}
}
