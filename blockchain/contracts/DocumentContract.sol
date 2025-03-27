// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract DocumentContract {
    address private immutable owner;

    struct Contract {
        string ipfsHash;  // 계약서 CID (IPFS)
        uint64 landlord;  // 임대인 ID (DB 관리)
        uint64 tenant;    // 임차인 ID (DB 관리)
    }

    mapping(uint256 => Contract) private contracts;

    event ContractCreated(uint256 indexed contractId, string ipfsHash, uint64 landlord, uint64 tenant);

    modifier onlyOwner() {
        require(msg.sender == owner, "Not authorized");
        _;
    }

    constructor(address _owner) {
        require(_owner != address(0), "Invalid owner address");
        owner = _owner;
    }

    function registerContract(
        uint256 _id,
        string calldata _ipfsHash,
        uint64 _landlord,
        uint64 _tenant
    ) external onlyOwner {
        require(contracts[_id].landlord == 0, "Contract ID exists");
        require(isValidCID(_ipfsHash), "Invalid IPFS CID");

        contracts[_id] = Contract(_ipfsHash, _landlord, _tenant);
        emit ContractCreated(_id, _ipfsHash, _landlord, _tenant);
    }

    function getContract(uint256 _contractId, uint64 _requesterId) external view returns (string memory) {
        Contract memory c = contracts[_contractId];
        require(c.landlord != 0, "Contract not found");
        require(_requesterId == c.landlord || _requesterId == c.tenant, "Unauthorized");

        return c.ipfsHash;
    }

    function isValidCID(string calldata _cid) internal pure returns (bool) {
        bytes memory cidBytes = bytes(_cid);
        uint256 length = cidBytes.length;

        if (length == 46) {
            // CIDv0 검증 (Base58, 항상 "Qm"으로 시작)
            return (cidBytes[0] == 'Q' && cidBytes[1] == 'm');
        } else if (length >= 49 && length <= 59) {
            // CIDv1 검증 (Base32, 항상 "bafy"로 시작)
            return (
                cidBytes[0] == 'b' &&
                cidBytes[1] == 'a' &&
                cidBytes[2] == 'f' &&
                cidBytes[3] == 'y'
            );
        }
        return false;
    }

}
