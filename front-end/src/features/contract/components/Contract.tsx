import {
  useState,
  forwardRef,
  useImperativeHandle,
  Dispatch,
  SetStateAction,
} from "react";
import ContractHeader from "./ContractHeader";
import HouseInfoSection from "./HouseInfoSection";
import ContractBody from "./ContractBody";
import SpecialTerms from "./SpecialTerms";
import SignatureModal from "./SignatureModal";
import ContractFooterSection from "./ContractFooterSection";
import {
  UpdateLandlordInfoDto,
  ContractType,
  LeaseType,
  MonthlyRentType,
} from "../data/contract.dto";

export interface ContractRefType {
  getFormData: () => UpdateLandlordInfoDto;
}

interface ContractProps {
  mode: "lessor" | "lessee";
}

const Contract = forwardRef<ContractRefType, ContractProps>(({ mode }, ref) => {
  const [leaseType, setLeaseType] = useState<LeaseType | null>(null);
  const [lessorName, setLessorName] = useState("");
  const [lesseeName, setLesseeName] = useState("");

  const [rentalPropertyAddress, setRentalPropertyAddress] = useState("");
  const [rentalPartAddress, setRentalPartAddress] = useState("");
  const [rentalHousingLandType, setRentalHousingLandType] = useState("");
  const [rentalHousingLandArea, setRentalHousingLandArea] = useState("");
  const [propertyStructure, setPropertyStructure] = useState("");
  const [propertyPurpose, setPropertyPurpose] = useState("");
  const [propertyArea, setPropertyArea] = useState("");
  const [rentalPartDetailAddress, setRentalPartDetailAddress] = useState("");
  const [rentalPartArea, setRentalPartArea] = useState("");
  const [contractType, setContractType] = useState<ContractType | null>(null);

  const [taxArrears, setTaxArrears] = useState(false);
  const [priorityConfirmedDateYn, setPriorityConfirmedDateYn] = useState(false);

  const [depositAmount, setDepositAmount] = useState(0);
  const [contractFee, setContractFee] = useState(0);
  const [middleFee, setMiddleFee] = useState(0);
  const [interimPaymentDate, setInterimPaymentDate] = useState("");
  const [balance, setBalance] = useState(0);
  const [balancePaymentDate, setBalancePaymentDate] = useState("");
  const [monthlyRent, setMonthlyRent] = useState(0);
  const [monthlyRentPaymentDate, setMonthlyRentPaymentDate] = useState("");
  const [monthlyRentType, setMonthlyRentType] =
    useState<MonthlyRentType | null>(null);
  const [monthlyRentAccountBank, setMonthlyRentAccountBank] = useState("");
  const [monthlyRentAccountNumber, setMonthlyRentAccountNumber] = useState("");
  const [fixedManagementFee, setFixedManagementFee] = useState(0);
  const [unfixedManagementFee, setUnfixedManagementFee] = useState("");
  const [leaseStartDate, setLeaseStartDate] = useState("");
  const [leaseEndDate, setLeaseEndDate] = useState("");
  const [facilitiesRepairStatus, setFacilitiesRepairStatus] = useState(false);
  const [facilitiesRepairContent, setFacilitiesRepairContent] = useState("");
  const [repairCompletionByBalanceDate, setRepairCompletionByBalanceDate] =
    useState("");
  const [repairCompletionEtc, setRepairCompletionEtc] = useState("");
  const [notRepairedByBalanceDate, setNotRepairedByBalanceDate] = useState("");
  const [notRepairedEtc, setNotRepairedEtc] = useState("");
  const [landlordBurden, setLandlordBurden] = useState("");
  const [tenantBurden, setTenantBurden] = useState("");

  const [moveInRegistrationDate, setMoveInRegistrationDate] = useState("");
  const [unpaidAmount, setUnpaidAmount] = useState(0);
  const [disputeResolution, setDisputeResolution] = useState(false);
  const [isHousingReconstructionPlanned, setIsHousingReconstructionPlanned] =
    useState(false);
  const [constructionPeriod, setConstructionPeriod] = useState("");
  const [estimatedConstructionDuration, setEstimatedConstructionDuration] =
    useState(0);
  const [isDetailedAddressConsentGiven, setIsDetailedAddressConsentGiven] =
    useState(false);
  const [etc, setEtc] = useState<string[]>([]);

  const [contractWrittenDate, setContractWrittenDate] = useState("");
  const [address, setAddress] = useState("");
  const [residentRegistrationNumber, setResidentRegistrationNumber] =
    useState("");
  const [phoneNumber, setPhoneNumber] = useState("");

  useImperativeHandle(ref, () => ({
    getFormData: (): UpdateLandlordInfoDto => ({
      contractId: 0,
      leaseType,
      rentalPropertyAddress,
      rentalPartAddress,
      rentalHousingLandType,
      rentalHousingLandArea: Number(rentalHousingLandArea),
      propertyStructure,
      propertyPurpose,
      propertyArea: Number(propertyArea),
      rentalPartDetailAddress,
      rentalPartArea: Number(rentalPartArea),
      contractType,
      previousLeaseStartDate: "",
      previousLeaseEndDate: "",
      previousDepositAmount: 0,
      previousMonthlyRent: 0,
      taxArrears,
      priorityConfirmedDateYn,
      depositAmount,
      contractFee,
      middleFee,
      interimPaymentDate,
      balance,
      balancePaymentDate,
      monthlyRent,
      monthlyRentPaymentDate,
      monthlyRentType,
      monthlyRentAccountBank,
      monthlyRentAccountNumber,
      fixedManagementFee,
      unfixedManagementFee,
      leaseStartDate,
      leaseEndDate,
      facilitiesRepairStatus,
      facilitiesRepairContent,
      repairCompletionByBalanceDate,
      repairCompletionEtc,
      notRepairedByBalanceDate,
      notRepairedEtc,
      landlordBurden,
      tenantBurden,
      moveInRegistrationDate,
      unpaidAmount,
      disputeResolution,
      isHousingReconstructionPlanned,
      constructionPeriod,
      estimatedConstructionDuration,
      isDetailedAddressConsentGiven,
      etc,
      contractWrittenDate,
      address,
      residentRegistrationNumber,
      phoneNumber,
      name: lessorName,
    }),
  }));

  return (
    <section className="w-full max-w-[800px] pt-6 pb-4">
      <ContractHeader
        mode={mode}
        lessorName={lessorName}
        lesseeName={lesseeName}
        onLessorNameChange={setLessorName}
        onLesseeNameChange={setLesseeName}
        leaseType={leaseType}
        setLeaseType={setLeaseType}
      />

      <HouseInfoSection
        mode={mode}
        contractType={contractType}
        setContractType={setContractType}
        rentalPartDetailAddress={rentalPartDetailAddress}
        leaseDetail={rentalPartDetailAddress}
        address={rentalPropertyAddress}
        detailAddress={rentalPartAddress}
        landPurpose={rentalHousingLandType}
        landArea={rentalHousingLandArea}
        buildingStructure={propertyStructure}
        buildingUsage={propertyPurpose}
        buildingArea={propertyArea}
        leaseArea={rentalPartArea}
        unpaidTaxOption={taxArrears ? "exist" : "none"}
        priorityDateOption={priorityConfirmedDateYn ? "exist" : "none"}
        unpaidTaxSignature={null}
        priorityDateSignature={null}
        onChange={(field, value) => {
          const map: Record<string, Dispatch<SetStateAction<string>>> = {
            leaseDetail: setRentalPartDetailAddress,
            rentalPartDetailAddress: setRentalPartDetailAddress,
            address: setRentalPropertyAddress,
            detailAddress: setRentalPartAddress,
            landPurpose: setRentalHousingLandType,
            landArea: setRentalHousingLandArea,
            buildingStructure: setPropertyStructure,
            buildingUsage: setPropertyPurpose,
            buildingArea: setPropertyArea,
            leaseArea: setRentalPartArea,
          };
          if (map[field]) map[field](value);
        }}
        onOptionChange={(field, value) => {
          if (field === "unpaidTaxOption") setTaxArrears(value === "exist");
          if (field === "priorityDateOption")
            setPriorityConfirmedDateYn(value === "exist");
        }}
        openSignatureModal={() => {}}
      />

      <ContractBody
        mode={mode}
        receiptSignature={null}
        openSignatureModal={() => {}}
        paymentMethod={monthlyRentType === "PREPAID" ? "선불" : "후불"}
        setPaymentMethod={(value) =>
          setMonthlyRentType(value === "선불" ? "PREPAID" : "POSTPAID")
        }
      />

      <SpecialTerms mode={mode} />
      <ContractFooterSection mode={mode} />
      <SignatureModal isOpen={false} onClose={() => {}} onSave={() => {}} />
    </section>
  );
});

export default Contract;
