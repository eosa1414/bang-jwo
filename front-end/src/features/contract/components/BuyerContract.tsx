// BuyerContract.tsx

import ContractHeader from "./ContractHeader";
import HouseInfoSection from "./HouseInfoSection";
import ContractBody from "./ContractBody";
import SpecialTerms from "./SpecialTerms";
import ContractFooterSection from "./ContractFooterSection";
import SignatureModal from "./SignatureModal";
import { useState } from "react";

const BuyerContract = () => {
  const [lessorName] = useState("");
  const [lesseeName, setLesseeName] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [activeSignatureType, setActiveSignatureType] = useState<
    "unpaid" | "priority" | "receipt" | null
  >(null);
  const [leaseDetail, setLeaseDetail] = useState("");
  const [leaseArea, setLeaseArea] = useState("");

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

  const [address, setAddress] = useState("");
  const [detailAddress, setDetailAddress] = useState("");
  const [landPurpose, setLandPurpose] = useState("");
  const [landArea, setLandArea] = useState("");
  const [buildingStructure, setBuildingStructure] = useState("");
  const [buildingUsage, setBuildingUsage] = useState("");
  const [buildingArea, setBuildingArea] = useState("");

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
        mode="lessee"
        lessorName={lessorName}
        lesseeName={lesseeName}
        onLesseeNameChange={setLesseeName}
      />

      <HouseInfoSection
        mode="lessee"
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
          const fieldSetters = {
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
          const typedField = field as keyof typeof fieldSetters;
          fieldSetters[typedField](value);
        }}
        onOptionChange={(field, value) => {
          const optionSetters = {
            unpaidTaxOption: setUnpaidTaxOption,
            priorityDateOption: setPriorityDateOption,
          };
          const typedField = field as keyof typeof optionSetters;
          optionSetters[typedField](value);
        }}
        openSignatureModal={openSignatureModal}
      />

      <hr className="mt-10 mb-6 border-t-[2px] border-neutral-light200" />

      <ContractBody
        mode="lessee"
        receiptSignature={receiptSignature}
        openSignatureModal={openSignatureModal}
      />

      <hr className="mt-10 mb-6 border-t-[2px] border-neutral-light200" />

      <SpecialTerms mode="lessee" />

      <hr className="mt-10 mb-6 border-t-[2px] border-neutral-light200" />

      <ContractFooterSection mode="lessee" />

      <SignatureModal
        isOpen={isModalOpen}
        onClose={closeModal}
        onSave={handleSaveSignature}
      />
    </section>
  );
};

export default BuyerContract;
