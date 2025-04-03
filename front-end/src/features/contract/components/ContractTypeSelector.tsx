import { useState } from "react";
import EditableInputBox from "./EditableInputBox";

const ContractTypeSelector = () => {
  const [selectedType, setSelectedType] = useState<string | null>(null);
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [deposit, setDeposit] = useState("");
  const [monthlyRent, setMonthlyRent] = useState("");

  const contractOptions = [
    {
      label: "신규 계약",
      value: "new",
    },
    {
      label: "합의에 의한 재계약",
      value: "renewal",
    },
    {
      label: "주택임대차보호법 제6조의3의 계약갱신요구권 행사에 의한 갱신계약",
      value: "legal-renewal",
    },
  ];

  return (
    <div
      className={`border-3 p-4 bg-white rounded-sm w-fit flex flex-col gap-3
        ${selectedType === null ? "border-green" : "border-neutral-gray"}`}
    >
      <fieldset className="flex flex-col gap-3">
        {contractOptions.map((option) => (
          <label
            key={option.value}
            className="flex flex-col text-sm font-bold cursor-pointer"
          >
            <span className="flex items-center gap-2">
              <input
                type="radio"
                name="contractType"
                value={option.value}
                checked={selectedType === option.value}
                onChange={() => setSelectedType(option.value)}
                className="w-[16px] h-[16px] border-2 border-neutral-dark200 bg-white
                           appearance-none cursor-pointer rounded-none
                           checked:bg-neutral-dark200 transition-colors"
              />
              {option.label}
            </span>

            {/* 갱신 계약 선택 시에만 표시 */}
            {selectedType === "legal-renewal" &&
              option.value === "legal-renewal" && (
                <div className="mt-3 flex flex-col gap-3 pl-6">
                  <h4 className="text-sm font-bold">
                    갱신 전 임대차계약 기간 및 금액
                  </h4>

                  <div className="flex items-center gap-2">
                    <span className="text-sm font-medium">계약 기간</span>
                    <EditableInputBox
                      value={startDate}
                      onChange={setStartDate}
                      placeholder="YYYY.MM.DD"
                      customWidth="w-[140px]"
                    />
                    <span className="text-sm">부터</span>
                    <EditableInputBox
                      value={endDate}
                      onChange={setEndDate}
                      placeholder="YYYY.MM.DD"
                      customWidth="w-[140px]"
                    />
                    <span className="text-sm">까지</span>
                  </div>

                  <div className="flex items-center gap-2">
                    <span className="text-sm font-medium">보증금</span>
                    <EditableInputBox
                      value={deposit}
                      onChange={setDeposit}
                      placeholder="0"
                      customWidth="w-[160px]"
                    />
                    <span className="text-sm">원</span>
                  </div>

                  <div className="flex items-center gap-2">
                    <span className="text-sm font-medium">차임 월</span>
                    <EditableInputBox
                      value={monthlyRent}
                      onChange={setMonthlyRent}
                      placeholder="0"
                      customWidth="w-[160px]"
                    />
                    <span className="text-sm">원</span>
                  </div>
                </div>
              )}
          </label>
        ))}
      </fieldset>
    </div>
  );
};

export default ContractTypeSelector;
