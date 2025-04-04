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