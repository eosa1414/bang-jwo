import { ethers } from "ethers";
import dotenv from "dotenv";

dotenv.config();

// ✅ Sepolia 네트워크 RPC 설정 (공개 RPC 사용)
const provider = new ethers.JsonRpcProvider("https://ethereum-sepolia-rpc.publicnode.com");

// ✅ 트랜잭션 해시 입력 (실제 배포된 트랜잭션 해시로 변경)
const txHash = process.env.TX_HASH;

// ✅ 트랜잭션 확인을 위한 최대 대기 시간 설정
const MAX_RETRIES = 5;
const RETRY_DELAY_MS = 5000; // 5초 대기 후 재시도

async function getTransactionDetails(retries = 0) {
    try {
        console.log(`🔍 Checking transaction: ${txHash}`);
        const txReceipt = await provider.getTransactionReceipt(txHash);

        // 🚨 트랜잭션이 아직 확인되지 않음 → 일정 시간 후 다시 시도
        if (!txReceipt) {
            if (retries < MAX_RETRIES) {
                console.log(`⏳ Transaction not yet confirmed. Retrying in ${RETRY_DELAY_MS / 1000} seconds...`);
                setTimeout(() => getTransactionDetails(retries + 1), RETRY_DELAY_MS);
            } else {
                console.log("🚨 Transaction still not found after multiple retries. Check again later.");
            }
            return;
        }

        // ✅ 블록 번호 확인 (blockNumber === null이면 블록에 포함되지 않음)
        if (!txReceipt.blockNumber) {
            console.log("🚨 Transaction is pending. Waiting for confirmation...");
            if (retries < MAX_RETRIES) {
                setTimeout(() => getTransactionDetails(retries + 1), RETRY_DELAY_MS);
            }
            return;
        }

        console.log(`✅ Transaction confirmed in block: ${txReceipt.blockNumber}`);
        console.log(`✅ Gas Used: ${txReceipt.gasUsed.toString()}`);

        // ✅ Gas Price 계산 (EIP-1559 적용 여부 고려)
        let gasPrice;
        if (txReceipt.effectiveGasPrice) {
            gasPrice = txReceipt.effectiveGasPrice;
        } else if (txReceipt.gasPrice) {
            gasPrice = txReceipt.gasPrice;
        } else {
            const block = await provider.getBlock(txReceipt.blockNumber);
            gasPrice = (block.baseFeePerGas ?? 0) + (txReceipt.maxPriorityFeePerGas ?? 0);
        }

        if (!gasPrice) {
            console.error("🚨 Error: Could not retrieve gas price!");
            return;
        }

        console.log(`✅ Gas Price: ${ethers.formatUnits(gasPrice, "gwei")} Gwei`);
        console.log(`✅ Total Cost: ${ethers.formatEther(txReceipt.gasUsed * gasPrice)} ETH`);

    } catch (error) {
        console.error("🚨 Error retrieving transaction details:", error);
    }
}

// 🚀 실행
getTransactionDetails();
