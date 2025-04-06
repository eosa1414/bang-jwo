import EditableInputBox from "./EditableInputBox";
import NoticeGray from "../../../components/notices/NoticeGray";
import ContractTypeSelector from "./ContractTypeSelector";
import { useState } from "react";
import { openAddressSearch } from "../../../utils/openAddressSearch";

interface HouseInfoSectionProps {
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
  address,
  detailAddress,
  landPurpose,
  landArea,
  buildingStructure,
  buildingUsage,
  buildingArea,
  leaseDetail,
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

  return (
    <div className="mt-10">
      <h3 className="text-lg font-extrabold">[임차주택의 표시]</h3>

      {/* ✅ 소재지 입력창 */}
      <div className="mt-4 flex items-center gap-3">
        <span className="text-base font-medium whitespace-nowrap">소재지</span>
        <div className="flex gap-2">
          <div
            className="cursor-pointer"
            onClick={() =>
              openAddressSearch((data) => onChange("address", data.roadAddress))
            }
          >
            <EditableInputBox
              value={address}
              onChange={(val) => onChange("address", val)}
              placeholder="도로명주소"
              maxLength={50}
              customWidth="w-[340px]"
            />
          </div>
          <EditableInputBox
            value={detailAddress}
            onChange={(val) => onChange("detailAddress", val)}
            placeholder="상세주소"
            maxLength={50}
            customWidth="w-[300px]"
          />
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
          <EditableInputBox
            value={landPurpose}
            onChange={(val) => onChange("landPurpose", val)}
            placeholder="예) 대"
            customWidth="w-[140px]"
          />
          <span className="text-base font-medium ml-4">면적</span>
          <EditableInputBox
            value={landArea}
            onChange={(val) => onChange("landArea", val)}
            placeholder="0"
            customWidth="w-[140px]"
          />
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
          <EditableInputBox
            value={buildingStructure}
            onChange={(val) => onChange("buildingStructure", val)}
            placeholder="예) 철근콘크리트"
            customWidth="w-[140px]"
          />
          <span className="text-base font-medium ml-4">용도</span>
          <EditableInputBox
            value={buildingUsage}
            onChange={(val) => onChange("buildingUsage", val)}
            placeholder="예) 공동주택"
            customWidth="w-[140px]"
          />
          <span className="text-base font-medium ml-4">면적</span>
          <EditableInputBox
            value={buildingArea}
            onChange={(val) => onChange("buildingArea", val)}
            placeholder="0"
            customWidth="w-[140px]"
          />
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
        <EditableInputBox
          value={leaseDetail}
          onChange={(val) => onChange("leaseDetail", val)}
          placeholder="상세주소가 있는 경우 동·층·호를 정확히 기재하세요"
          maxLength={100}
          customWidth="w-[400px]"
        />
        <span className="text-base font-medium ml-4">면적</span>
        <EditableInputBox
          value={leaseArea}
          onChange={(val) => onChange("leaseArea", val)}
          placeholder="0"
          customWidth="w-[140px]"
        />
        <span className="text-sm font-medium">m²</span>
      </div>

      {/* 계약의 종류 */}
      <div className="mt-6 flex items-start gap-4">
        <span className="text-base font-medium whitespace-nowrap mt-[6px]">
          계약의종류
        </span>
        <ContractTypeSelector />
      </div>

      {/* 미납 국세·지방세 */}
      <div className="mt-6 flex items-start gap-4">
        <span className="text-base font-medium whitespace-nowrap mt-[6px]">
          미납 국세·지방세
        </span>
        <div
          className={`border-3 rounded-sm p-4 flex flex-col gap-3 bg-white w-fit ${
            unpaidTaxOption === null ? "border-green" : "border-neutral-gray"
          }`}
        >
          <label className="flex items-center gap-2 text-sm font-bold cursor-pointer">
            <input
              type="radio"
              name="unpaidTax"
              value="none"
              checked={unpaidTaxOption === "none"}
              onChange={() => onOptionChange("unpaidTaxOption", "none")}
              className="w-[16px] h-[16px] border-2 border-neutral-dark200 bg-white appearance-none cursor-pointer rounded-none checked:bg-neutral-dark200 transition-colors"
            />
            없음 ( 임대인 서명
            <div
              onClick={() => openSignatureModal("unpaid")}
              className="w-[100px] h-[32px] border-2 border-neutral-light100 bg-neutral-light200 cursor-pointer"
            >
              {unpaidTaxSignature ? (
                <img src={unpaidTaxSignature} alt="서명" />
              ) : null}
            </div>
            (인) )
          </label>

          <label className="flex items-center gap-2 text-sm font-bold cursor-pointer">
            <input
              type="radio"
              name="unpaidTax"
              value="exist"
              checked={unpaidTaxOption === "exist"}
              onChange={() => onOptionChange("unpaidTaxOption", "exist")}
              className="w-[16px] h-[16px] border-2 border-neutral-dark200 bg-white appearance-none cursor-pointer rounded-none checked:bg-neutral-dark200 transition-colors"
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
          className={`border-3 rounded-sm p-4 flex flex-col gap-3 bg-white w-fit ${
            priorityDateOption === null ? "border-green" : "border-neutral-gray"
          }`}
        >
          <label className="flex items-center gap-2 text-sm font-bold cursor-pointer">
            <input
              type="radio"
              name="priorityDate"
              value="none"
              checked={priorityDateOption === "none"}
              onChange={() => onOptionChange("priorityDateOption", "none")}
              className="w-[16px] h-[16px] border-2 border-neutral-dark200 bg-white appearance-none cursor-pointer rounded-none checked:bg-neutral-dark200 transition-colors"
            />
            해당 없음 ( 임대인 서명
            <div
              onClick={() => openSignatureModal("priority")}
              className="w-[100px] h-[32px] border-2 border-neutral-light100 bg-neutral-light200 cursor-pointer"
            >
              {priorityDateSignature ? (
                <img src={priorityDateSignature} alt="서명" />
              ) : null}
            </div>
            (인) )
          </label>

          <label className="flex items-center gap-2 text-sm font-bold cursor-pointer">
            <input
              type="radio"
              name="priorityDate"
              value="exist"
              checked={priorityDateOption === "exist"}
              onChange={() => onOptionChange("priorityDateOption", "exist")}
              className="w-[16px] h-[16px] border-2 border-neutral-dark200 bg-white appearance-none cursor-pointer rounded-none checked:bg-neutral-dark200 transition-colors"
            />
            해당 있음
          </label>
        </div>
      </div>
    </div>
  );
};

export default HouseInfoSection;
