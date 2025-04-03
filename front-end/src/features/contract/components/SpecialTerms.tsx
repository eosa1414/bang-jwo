import { useState } from "react";
import EditableInputBox from "./EditableInputBox";
import Button from "../../../components/buttons/Button";

const SpecialTerms = () => {
  const [moveInDate, setMoveInDate] = useState("");
  const [taxAmount, setTaxAmount] = useState("");

  const [disputeConsent, setDisputeConsent] = useState<
    "agree" | "disagree" | null
  >(null);
  const [rebuildPlan, setRebuildPlan] = useState<"none" | "exist" | null>(null);
  const [constructionPeriod, setConstructionPeriod] = useState("");
  const [constructionDuration, setConstructionDuration] = useState("");
  const [ownerConsent, setOwnerConsent] = useState<"agree" | "disagree" | null>(
    null
  );

  const [customTerms, setCustomTerms] = useState<string[]>([]);
  const [isAdding, setIsAdding] = useState(false);
  const [newTerm, setNewTerm] = useState("");

  const handleAddNewTerm = () => {
    if (newTerm.trim()) {
      setCustomTerms((prev) => [...prev, newTerm.trim()]);
      setNewTerm("");
      setIsAdding(false);
    }
  };

  const handleDeleteTerm = (index: number) => {
    setCustomTerms((prev) => prev.filter((_, i) => i !== index));
  };

  return (
    <div className="mt-10 text-base font-normal leading-relaxed">
      <h3 className="text-lg font-extrabold mb-4">[특약사항]</h3>

      <ul className="flex flex-col gap-4">
        <li>
          • 주택을 인도받은 임차인은{" "}
          <EditableInputBox
            value={moveInDate}
            onChange={setMoveInDate}
            placeholder="날짜"
            customWidth="w-[140px] mx-1"
          />
          까지 주민등록(전입신고)과 주택임대차계약서상 확정일자를 받기로 하고,
          임대인은 위 약정일자의 다음날까지 임차주택에 저당권 등 담보권을 설정할
          수 없다.
        </li>

        <li>
          • 임대인이 위 특약에 위반하여 임차주택에 저당권 등 담보권을 설정한
          경우에는 임차인은 임대차계약을 해제 또는 해지할 수 있다. 이 경우
          임대인은 임차인에게 위 특약 위반으로 인한 손해를 배상하여야 한다.
        </li>

        <li>
          • 임대차계약을 체결한 임차인은 계약 시 기준으로 임대인이 고지하지 않은
          선순위 임대차 정보 또는 체납 세금이{" "}
          <EditableInputBox
            value={taxAmount}
            onChange={setTaxAmount}
            placeholder="금액"
            customWidth="w-[120px] mx-1"
          />
          원을 초과할 경우, 계약 해제가 가능하다.
        </li>

        {/* 분쟁조정 동의 여부 */}
        <li>
          • 주택 임대차 계약과 관련한 분쟁이 있는 경우 먼저 분쟁조정위원회에
          조정을 신청한다
          <div
            className={`mt-2 px-4 py-3 rounded-sm border-3 flex gap-4 w-fit bg-white ${
              disputeConsent === null ? "border-green" : "border-neutral-gray"
            }`}
          >
            {(["agree", "disagree"] as const).map((option) => (
              <label
                key={option}
                className="flex items-center gap-2 text-sm font-bold cursor-pointer"
              >
                <input
                  type="radio"
                  name="disputeConsent"
                  value={option}
                  checked={disputeConsent === option}
                  onChange={() => setDisputeConsent(option)}
                  className="w-[16px] h-[16px] appearance-none border-2 border-neutral-dark200 checked:bg-neutral-dark200 cursor-pointer"
                />
                {option === "agree" ? "동의" : "미동의"}
              </label>
            ))}
          </div>
        </li>

        {/* 재건축 계획 */}
        <li>
          • 주택의 철거 또는 재건축에 대한 계획
          <div
            className={`mt-2 px-4 py-3 rounded-sm border-3 flex gap-4 flex-wrap items-center w-fit bg-white ${
              rebuildPlan === null ? "border-green" : "border-neutral-gray"
            }`}
          >
            {(["none", "exist"] as const).map((option) => (
              <label
                key={option}
                className="flex items-center gap-2 text-sm font-bold cursor-pointer"
              >
                <input
                  type="radio"
                  name="rebuildPlan"
                  value={option}
                  checked={rebuildPlan === option}
                  onChange={() => setRebuildPlan(option)}
                  className="w-[16px] h-[16px] appearance-none border-2 border-neutral-dark200 checked:bg-neutral-dark200 cursor-pointer"
                />
                {option === "none" ? (
                  "없음"
                ) : (
                  <>
                    있음 (공사시기:
                    <EditableInputBox
                      value={constructionPeriod}
                      onChange={setConstructionPeriod}
                      placeholder="예: 2025.06"
                      customWidth="w-[120px] mx-1"
                    />
                    , 소요기간:
                    <EditableInputBox
                      value={constructionDuration}
                      onChange={setConstructionDuration}
                      placeholder="개월"
                      customWidth="w-[80px] mx-1"
                    />
                    )
                  </>
                )}
              </label>
            ))}
          </div>
        </li>

        {/* 상세주소 동의 */}
        <li>
          • 상세주소가 없는 경우 소유자의 동의 여부
          <div
            className={`mt-2 px-4 py-3 rounded-sm border-3 flex gap-4 w-fit bg-white ${
              ownerConsent === null ? "border-green" : "border-neutral-gray"
            }`}
          >
            {(["agree", "disagree"] as const).map((option) => (
              <label
                key={option}
                className="flex items-center gap-2 text-sm font-bold cursor-pointer"
              >
                <input
                  type="radio"
                  name="ownerConsent"
                  value={option}
                  checked={ownerConsent === option}
                  onChange={() => setOwnerConsent(option)}
                  className="w-[16px] h-[16px] appearance-none border-2 border-neutral-dark200 checked:bg-neutral-dark200 cursor-pointer"
                />
                {option === "agree" ? "동의" : "미동의"}
              </label>
            ))}
          </div>
        </li>

        {/* 사용자 정의 특약사항 목록 */}
        {customTerms.map((term, idx) => (
          <li key={idx} className="relative pr-10">
            <p className="whitespace-pre-wrap break-words pr-2">• {term}</p>
            <button
              onClick={() => handleDeleteTerm(idx)}
              className="absolute top-0 right-0 text-[13px] text-coral-red font-medium mt-0.5 cursor-pointer"
            >
              삭제
            </button>
          </li>
        ))}

        {/* 입력창 (조건부 표시) */}
        {isAdding && (
          <li className="flex flex-col gap-2">
            <EditableInputBox
              value={newTerm}
              onChange={setNewTerm}
              placeholder="추가할 특약사항을 입력하세요"
              customWidth="w-full"
              maxLength={300}
            />
            <Button size="small" variant="default" onClick={handleAddNewTerm}>
              확인
            </Button>
          </li>
        )}
      </ul>

      {!isAdding && (
        <div className="mt-4">
          <Button
            size="small"
            variant="default"
            onClick={() => setIsAdding(true)}
          >
            추가하기
          </Button>
        </div>
      )}
    </div>
  );
};

export default SpecialTerms;
