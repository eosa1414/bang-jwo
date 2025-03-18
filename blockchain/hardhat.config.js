require("@nomicfoundation/hardhat-toolbox");
require("dotenv").config();

module.exports = {
    solidity: "0.8.20",
    networks: {
        sepolia: {
            url: `https://sepolia.infura.io`,
            accounts: [`0x${process.env.PRIVATE_KEY}`]
        }
    }
};
