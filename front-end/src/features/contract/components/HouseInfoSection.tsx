import EditableInputBox from "./EditableInputBox";
import NoticeGray from "../../../components/notices/NoticeGray";
import ContractTypeSelector from "./ContractTypeSelector";
import { useState } from "react";
import DisabledInputBox from "./DisabledInputBox";
import { openAddressSearch } from "../../../utils/openAddressSearch";

interface HouseInfoSectionProps {
  mode: "lessor" | "lessee";
  contractType: string;
  setContractType: (value: string) => void;
  rentalPartDetailAddress: string;

  address: string;
  detailAddress: string;
  landPurpose: string;
  landArea: string;
  buildingStructure: string;
  buildingUsage: string;
  buildingArea: string;
  leaseDetail: string;
  leaseArea: string;

  unpaidTaxOption: string | null;
  priorityDateOption: string | null;

  unpaidTaxSignature: string | null;
  priorityDateSignature: string | null;

  onChange: (field: string, value: string) => void;
  onOptionChange: (field: string, value: string) => void;

  openSignatureModal: (type: "unpaid" | "priority" | "receipt") => void;
}

const HouseInfoSection = ({
  mode,
  contractType,
  setContractType,
  rentalPartDetailAddress,
  address,
  detailAddress,
  landPurpose,
  landArea,
  buildingStructure,
  buildingUsage,
  buildingArea,
  leaseArea,
  unpaidTaxOption,
  priorityDateOption,
  unpaidTaxSignature,
  priorityDateSignature,
  onChange,
  onOptionChange,
  openSignatureModal,
}: HouseInfoSectionProps) => {
  const [showLandNotice, setShowLandNotice] = useState(false);
  const [showBuildingNotice, setShowBuildingNotice] = useState(false);

  const isLessee = mode === "lessee";

  return (
    <div className="mt-10">
      <h3 className="text-lg font-extrabold">[임차주택의 표시]</h3>

      <div className="mt-4 flex items-center gap-3">
        <span className="text-base font-medium whitespace-nowrap">소재지</span>
        <div className="flex gap-2">
          {/* 도로명주소 클릭 시에만 주소 검색 팝업 실행 */}
          <div
            className={isLessee ? "cursor-default" : "cursor-pointer"}
            onClick={() => {
              if (!isLessee) {
                openAddressSearch((data) =>
                  onChange("address", data.roadAddress)
                );
              }
            }}
          >
            {isLessee ? (
              <DisabledInputBox
                value={address}
                placeholder="도로명주소"
                customWidth="w-[340px]"
              />
            ) : (
              <EditableInputBox
                value={address}
                onChange={(val) => onChange("address", val)}
                placeholder="도로명주소"
                maxLength={50}
                customWidth="w-[340px]"
              />
            )}
          </div>

          {/* 상세주소는 일반 입력 필드로 별도 분리 */}
          {isLessee ? (
            <DisabledInputBox
              value={detailAddress}
              placeholder="상세주소"
              customWidth="w-[300px]"
            />
          ) : (
            <EditableInputBox
              value={detailAddress}
              onChange={(val) => onChange("detailAddress", val)}
              placeholder="상세주소"
              maxLength={50}
              customWidth="w-[300px]"
            />
          )}
        </div>
      </div>

      <div className="mt-6 flex flex-col gap-3">
        <div className="flex flex-wrap items-center gap-2">
          <span className="text-base font-medium whitespace-nowrap">토 지</span>
          <button
            type="button"
            onClick={() => setShowLandNotice((prev) => !prev)}
            className="flex items-center justify-center -ml-1 relative top-[0.4px] cursor-pointer"
          >
            <span
              className="material-symbols-rounded text-neutral-dark200"
              style={{
                fontVariationSettings: `'FILL' ${
                  showLandNotice ? 1 : 0
                }, 'wght' 400, 'GRAD' 0, 'opsz' 24`,
                fontSize: "18px",
                lineHeight: "1",
              }}
            >
              help
            </span>
          </button>
          <span className="text-base font-medium">지목</span>
          {isLessee ? (
            <DisabledInputBox
              value={landPurpose}
              placeholder="예) 대"
              customWidth="w-[160px]"
            />
          ) : (
            <EditableInputBox
              value={landPurpose}
              onChange={(val) => onChange("landPurpose", val)}
              placeholder="예) 대"
              customWidth="w-[160px]"
            />
          )}
          <span className="text-base font-medium ml-4">면적</span>
          {isLessee ? (
            <DisabledInputBox
              value={landArea}
              placeholder="0"
              customWidth="w-[160px]"
            />
          ) : (
            <EditableInputBox
              value={landArea}
              onChange={(val) => onChange("landArea", val)}
              placeholder="0"
              customWidth="w-[160px]"
            />
          )}
          <span className="text-sm font-medium">m²</span>
        </div>
        {showLandNotice && (
          <NoticeGray>
            토지·건물 관련 항목은 건축물대장을 확인해주세요.
          </NoticeGray>
        )}
      </div>

      <div className="mt-6 flex flex-col gap-3">
        <div className="flex flex-wrap items-center gap-2">
          <span className="text-base font-medium whitespace-nowrap">건 물</span>
          <button
            type="button"
            onClick={() => setShowBuildingNotice((prev) => !prev)}
            className="flex items-center justify-center -ml-1 relative top-[0.4px] cursor-pointer"
          >
            <span
              className="material-symbols-rounded text-neutral-dark200"
              style={{
                fontVariationSettings: `'FILL' ${
                  showBuildingNotice ? 1 : 0
                }, 'wght' 400, 'GRAD' 0, 'opsz' 24`,
                fontSize: "18px",
                lineHeight: "1",
              }}
            >
              help
            </span>
          </button>
          <span className="text-base font-medium">구조</span>
          {isLessee ? (
            <DisabledInputBox
              value={buildingStructure}
              placeholder="예) 철근콘크리트"
              customWidth="w-[160px]"
            />
          ) : (
            <EditableInputBox
              value={buildingStructure}
              onChange={(val) => onChange("buildingStructure", val)}
              placeholder="예) 철근콘크리트"
              customWidth="w-[160px]"
            />
          )}
          <span className="text-base font-medium ml-4">용도</span>
          {isLessee ? (
            <DisabledInputBox
              value={buildingUsage}
              placeholder="예) 공동주택"
              customWidth="w-[160px]"
            />
          ) : (
            <EditableInputBox
              value={buildingUsage}
              onChange={(val) => onChange("buildingUsage", val)}
              placeholder="예) 공동주택"
              customWidth="w-[160px]"
            />
          )}
          <span className="text-base font-medium ml-4">면적</span>
          {isLessee ? (
            <DisabledInputBox
              value={buildingArea}
              placeholder="0"
              customWidth="w-[160px]"
            />
          ) : (
            <EditableInputBox
              value={buildingArea}
              onChange={(val) => onChange("buildingArea", val)}
              placeholder="0"
              customWidth="w-[160px]"
            />
          )}
          <span className="text-sm font-medium">m²</span>
        </div>
        {showBuildingNotice && (
          <NoticeGray>
            토지·건물 관련 항목은 건축물대장을 확인해주세요.
          </NoticeGray>
        )}
      </div>

      <div className="mt-6 flex flex-wrap items-center gap-3">
        <span className="text-base font-medium whitespace-nowrap">
          임차할부분
        </span>
        {isLessee ? (
          <DisabledInputBox
            value={rentalPartDetailAddress}
            placeholder="상세주소가 있는 경우 동·층·호를 정확히 기재하세요"
            customWidth="w-[400px]"
          />
        ) : (
          <EditableInputBox
            value={rentalPartDetailAddress}
            onChange={(val) => onChange("rentalPartDetailAddress", val)} // ✅ 올바른 키로 변경
            placeholder="상세주소가 있는 경우 동·층·호를 정확히 기재하세요"
            maxLength={100}
            customWidth="w-[400px]"
          />
        )}
        <span className="text-base font-medium ml-4">면적</span>
        {isLessee ? (
          <DisabledInputBox
            value={leaseArea}
            placeholder="0"
            customWidth="w-[160px]"
          />
        ) : (
          <EditableInputBox
            value={leaseArea}
            onChange={(val) => onChange("leaseArea", val)}
            placeholder="0"
            customWidth="w-[160px]"
          />
        )}
        <span className="text-sm font-medium">m²</span>
      </div>

      {/* 계약의 종류 */}
      <div className="mt-6 flex items-start gap-4">
        <span className="text-base font-medium whitespace-nowrap mt-[6px]">
          계약의종류
        </span>
        <ContractTypeSelector
          mode={mode}
          contractType={contractType}
          setContractType={setContractType}
        />
      </div>

      {/* 미납 국세·지방세 */}
      <div className="mt-6 flex items-start gap-4">
        <span className="text-base font-medium whitespace-nowrap mt-[6px]">
          미납 국세·지방세
        </span>
        <div
          className={`border-3 rounded-sm p-4 flex flex-col gap-3 w-fit
    ${
      isLessee
        ? "bg-neutral-light200 border-neutral-light100"
        : "bg-white " +
          (unpaidTaxOption === null ? "border-green" : "border-neutral-gray")
    }`}
        >
          <label
            className={`flex items-center gap-2 text-sm font-bold ${
              isLessee ? "cursor-not-allowed" : "cursor-pointer"
            }`}
          >
            <input
              type="radio"
              name="unpaidTax"
              value="none"
              checked={unpaidTaxOption === "none"}
              onChange={() =>
                !isLessee && onOptionChange("unpaidTaxOption", "none")
              }
              disabled={isLessee}
              className="w-[16px] h-[16px] border-2 border-neutral-dark200 bg-white appearance-none rounded-none checked:bg-neutral-dark200 transition-colors disabled:cursor-not-allowed"
            />
            없음 ( 임대인 서명
            <div
              onClick={() => !isLessee && openSignatureModal("unpaid")}
              className={`w-[100px] h-[32px] border-2 border-neutral-light100 bg-neutral-light200 ${
                isLessee ? "cursor-not-allowed" : "cursor-pointer"
              }`}
            >
              {unpaidTaxSignature ? (
                <img src={unpaidTaxSignature} alt="서명" />
              ) : null}
            </div>
            (인) )
          </label>

          <label
            className={`flex items-center gap-2 text-sm font-bold ${
              isLessee ? "cursor-not-allowed" : "cursor-pointer"
            }`}
          >
            <input
              type="radio"
              name="unpaidTax"
              value="exist"
              checked={unpaidTaxOption === "exist"}
              onChange={() =>
                !isLessee && onOptionChange("unpaidTaxOption", "exist")
              }
              disabled={isLessee}
              className="w-[16px] h-[16px] border-2 border-neutral-dark200 bg-white appearance-none rounded-none checked:bg-neutral-dark200 transition-colors disabled:cursor-not-allowed"
            />
            있음
          </label>
        </div>
      </div>

      {/* 선순위 확정일자 현황 */}
      <div className="mt-6 flex items-start gap-4">
        <span className="text-base font-medium whitespace-nowrap mt-[6px]">
          선순위 확정일자 현황
        </span>
        <div
          className={`border-3 rounded-sm p-4 flex flex-col gap-3 w-fit
    ${
      isLessee
        ? "bg-neutral-light200 border-neutral-light100"
        : "bg-white " +
          (priorityDateOption === null ? "border-green" : "border-neutral-gray")
    }`}
        >
          <label
            className={`flex items-center gap-2 text-sm font-bold ${
              isLessee ? "cursor-not-allowed" : "cursor-pointer"
            }`}
          >
            <input
              type="radio"
              name="priorityDate"
              value="none"
              checked={priorityDateOption === "none"}
              onChange={() =>
                !isLessee && onOptionChange("priorityDateOption", "none")
              }
              disabled={isLessee}
              className="w-[16px] h-[16px] border-2 border-neutral-dark200 bg-white appearance-none rounded-none checked:bg-neutral-dark200 transition-colors disabled:cursor-not-allowed"
            />
            해당 없음 ( 임대인 서명
            <div
              onClick={() => !isLessee && openSignatureModal("priority")}
              className={`w-[100px] h-[32px] border-2 border-neutral-light100 bg-neutral-light200 ${
                isLessee ? "cursor-not-allowed" : "cursor-pointer"
              }`}
            >
              {priorityDateSignature ? (
                <img src={priorityDateSignature} alt="서명" />
              ) : null}
            </div>
            (인) )
          </label>

          <label
            className={`flex items-center gap-2 text-sm font-bold ${
              isLessee ? "cursor-not-allowed" : "cursor-pointer"
            }`}
          >
            <input
              type="radio"
              name="priorityDate"
              value="exist"
              checked={priorityDateOption === "exist"}
              onChange={() =>
                !isLessee && onOptionChange("priorityDateOption", "exist")
              }
              disabled={isLessee}
              className="w-[16px] h-[16px] border-2 border-neutral-dark200 bg-white appearance-none rounded-none checked:bg-neutral-dark200 transition-colors disabled:cursor-not-allowed"
            />
            해당 있음
          </label>
        </div>
      </div>
    </div>
  );
};

export default HouseInfoSection;
