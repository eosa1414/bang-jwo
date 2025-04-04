require("dotenv").config();
const hre = require("hardhat");

async function main() {
    const [deployer] = await hre.ethers.getSigners();
    console.log("Deploying contract with the account:", deployer.address);
    const proposedOwner = process.env.PROPOSED_OWNER;
    console.log("📦 Raw proposed owner from env:", proposedOwner);

    const DocumentContract = await hre.ethers.getContractFactory("DocumentContract");
    const contract = await DocumentContract.deploy(deployer.address, proposedOwner);
    console.log(await deployer.getAddress());
    await contract.waitForDeployment();  // ✅ 수정 완료

    console.log("✅ Contract deployed at:", await contract.getAddress());
}

main().catch((error) => {
    console.error(error);
    process.exitCode = 1;
});
