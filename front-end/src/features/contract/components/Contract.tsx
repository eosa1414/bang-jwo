// Contract.tsx

import { useState } from "react";
import ContractHeader from "./ContractHeader";
import HouseInfoSection from "./HouseInfoSection";
import ContractBody from "./ContractBody";
import SpecialTerms from "./SpecialTerms";
import SignatureModal from "./SignatureModal";
import ContractFooterSection from "./ContractFooterSection";

interface ContractProps {
  mode: "lessor" | "lessee";
}

const Contract = ({ mode }: ContractProps) => {
  const [lessorName, setLessorName] = useState("");
  const [lesseeName, setLesseeName] = useState("");

  const [leaseDetail, setLeaseDetail] = useState("");
  const [leaseArea, setLeaseArea] = useState("");

  const [address, setAddress] = useState("");
  const [detailAddress, setDetailAddress] = useState("");
  const [landPurpose, setLandPurpose] = useState("");
  const [landArea, setLandArea] = useState("");
  const [buildingStructure, setBuildingStructure] = useState("");
  const [buildingUsage, setBuildingUsage] = useState("");
  const [buildingArea, setBuildingArea] = useState("");

  const [unpaidTaxOption, setUnpaidTaxOption] = useState<string | null>(null);
  const [priorityDateOption, setPriorityDateOption] = useState<string | null>(
    null
  );

  const [unpaidTaxSignature, setUnpaidTaxSignature] = useState<string | null>(
    null
  );
  const [priorityDateSignature, setPriorityDateSignature] = useState<
    string | null
  >(null);
  const [receiptSignature, setReceiptSignature] = useState<string | null>(null);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [activeSignatureType, setActiveSignatureType] = useState<
    "unpaid" | "priority" | "receipt" | null
  >(null);

  const openSignatureModal = (type: "unpaid" | "priority" | "receipt") => {
    setActiveSignatureType(type);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
    setActiveSignatureType(null);
  };

  const handleSaveSignature = (signatureData: string) => {
    if (activeSignatureType === "unpaid") setUnpaidTaxSignature(signatureData);
    if (activeSignatureType === "priority")
      setPriorityDateSignature(signatureData);
    if (activeSignatureType === "receipt") setReceiptSignature(signatureData);
    closeModal();
  };

  return (
    <section className="w-full max-w-[800px] pt-6 pb-4">
      <ContractHeader
        mode={mode}
        lessorName={lessorName}
        lesseeName={lesseeName}
        onLessorNameChange={setLessorName}
        onLesseeNameChange={setLesseeName}
      />

      <HouseInfoSection
        mode={mode}
        leaseDetail={leaseDetail}
        leaseArea={leaseArea}
        address={address}
        detailAddress={detailAddress}
        landPurpose={landPurpose}
        landArea={landArea}
        buildingStructure={buildingStructure}
        buildingUsage={buildingUsage}
        buildingArea={buildingArea}
        unpaidTaxOption={unpaidTaxOption}
        priorityDateOption={priorityDateOption}
        unpaidTaxSignature={unpaidTaxSignature}
        priorityDateSignature={priorityDateSignature}
        onChange={(field, value) => {
          const setterMap = {
            leaseDetail: setLeaseDetail,
            leaseArea: setLeaseArea,
            address: setAddress,
            detailAddress: setDetailAddress,
            landPurpose: setLandPurpose,
            landArea: setLandArea,
            buildingStructure: setBuildingStructure,
            buildingUsage: setBuildingUsage,
            buildingArea: setBuildingArea,
          };
          const setter = setterMap[field as keyof typeof setterMap];
          setter(value);
        }}
        onOptionChange={(field, value) => {
          const setterMap = {
            unpaidTaxOption: setUnpaidTaxOption,
            priorityDateOption: setPriorityDateOption,
          };
          const setter = setterMap[field as keyof typeof setterMap];
          setter(value);
        }}
        openSignatureModal={openSignatureModal}
      />

      <hr className="mt-10 mb-6 border-t-[2px] border-neutral-light200" />

      <ContractBody
        mode={mode}
        receiptSignature={receiptSignature}
        openSignatureModal={openSignatureModal}
      />

      <hr className="mt-10 mb-6 border-t-[2px] border-neutral-light200" />

      <SpecialTerms mode={mode} />

      <hr className="mt-10 mb-6 border-t-[2px] border-neutral-light200" />

      <ContractFooterSection mode={mode} />

      <SignatureModal
        isOpen={isModalOpen}
        onClose={closeModal}
        onSave={handleSaveSignature}
      />
    </section>
  );
};

export default Contract;
