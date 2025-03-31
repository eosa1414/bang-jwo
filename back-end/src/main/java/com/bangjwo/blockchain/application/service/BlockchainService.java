package com.bangjwo.blockchain.application.service;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import com.bangjwo.blockchain.DocumentContract;

@Service
public class BlockchainService {

	private final DocumentContract contract;
	private final NotificationService notificationService;

	public BlockchainService(NotificationService notificationService) {
		this.notificationService = notificationService;
		String infuraUrl = "https://sepolia.infura.io/v3/" + System.getenv("INFURA_API_KEY");
		Web3j web3j = Web3j.build(new HttpService(infuraUrl));

		Credentials credentials = Credentials.create(System.getenv("PRIVATE_KEY"));
		String contractAddress = System.getenv("CONTRACT_ADDRESS");
		this.contract = DocumentContract.load(contractAddress, web3j, credentials, new DefaultGasProvider());
	}

	public CompletableFuture<TransactionReceipt> registerContract(BigInteger contractId, String ipfsHash,
		BigInteger landlord, BigInteger tenant) {
		notificationService.notifyClients("Internal: registerContract submitted and pending confirmation.");

		CompletableFuture<TransactionReceipt> future = contract.registerContract(contractId, ipfsHash, landlord, tenant)
			.sendAsync();
		future.thenAccept(receipt -> {
			String txHash = receipt.getTransactionHash();
			notificationService.notifyClients("Internal: registerContract confirmed. TxHash: " + txHash);
		}).exceptionally(ex -> {
			notificationService.notifyClients("Internal: registerContract failed: " + ex.getMessage());
			return null;
		});
		return future;
	}

	public String getContractData(BigInteger contractId, BigInteger requesterId) {
		try {
			return contract.getContract(contractId, requesterId).send();
		} catch (Exception e) {
			notificationService.notifyClients("Internal: getContractData failed: " + e.getMessage());
			return e.getMessage();
		}
	}
}

// package com.bangjwo.blockchain.application.service;
//
// import java.math.BigInteger;
//
// import org.springframework.stereotype.Service;
// import org.web3j.crypto.Credentials;
// import org.web3j.protocol.Web3j;
// import org.web3j.protocol.core.methods.response.TransactionReceipt;
// import org.web3j.protocol.http.HttpService;
// import org.web3j.tx.gas.DefaultGasProvider;
//
// import com.bangjwo.blockchain.DocumentContract;
//
// @Service
// public class BlockchainService {
//
// 	private final Web3j web3j;
// 	private final DocumentContract contract;
//
// 	public BlockchainService() {
// 		// Sepolia 네트워크 연결 (Infura 예시)
// 		String infuraUrl = "https://sepolia.infura.io/v3/" + System.getenv("INFURA_API_KEY");
// 		this.web3j = Web3j.build(new HttpService(infuraUrl));
//
// 		// Credentials 생성 (환경 변수 등으로 관리할 것을 권장)
// 		Credentials credentials = Credentials.create(System.getenv("PRIVATE_KEY"));
//
// 		// 배포된 컨트랙트 주소
// 		String contractAddress = System.getenv("CONTRACT_ADDRESS");
// 		this.contract = DocumentContract.load(contractAddress, web3j, credentials, new DefaultGasProvider());
// 	}
//
// 	/**
// 	 * 컨트랙트의 getContract 함수 호출
// 	 *
// 	 * @param contractId 조회할 계약 ID
// 	 * @param requesterId 호출하는 사용자의 ID
// 	 * @return 컨트랙트로부터 반환받은 데이터 (예시: IPFS 해시 등)
// 	 * @throws Exception 스마트 컨트랙트 호출 시 발생하는 예외
// 	 */
// 	public String getContractData(BigInteger contractId, BigInteger requesterId) throws Exception {
// 		return contract.getContract(contractId, requesterId).send();
// 	}
//
// 	/**
// 	 * 컨트랙트의 registerContract 함수 호출 (상태 변경 트랜잭션)
// 	 *
// 	 * @param id         등록할 계약 ID
// 	 * @param ipfsHash   계약 관련 데이터의 IPFS 해시
// 	 * @param landlord   임대인 ID (예시: uint64)
// 	 * @param tenant     임차인 ID (예시: uint64)
// 	 * @return 트랜잭션 결과를 담은 TransactionReceipt
// 	 * @throws Exception 스마트 컨트랙트 호출 시 발생하는 예외
// 	 */
// 	public TransactionReceipt registerContract(BigInteger id, String ipfsHash, BigInteger landlord, BigInteger tenant)
// 		throws Exception {
// 		return contract.registerContract(id, ipfsHash, landlord, tenant).send();
// 	}
// }
