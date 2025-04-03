import { useState } from "react";
import EditableInputBox from "./EditableInputBox";
import NoticeGray from "../../../components/notices/NoticeGray";
import DatePickerInput from "./DatePickerInput";

interface ContractBodyProps {
  receiptSignature: string | null;
  openSignatureModal: (type: "receipt") => void;
}

const ContractBody = ({
  receiptSignature,
  openSignatureModal,
}: ContractBodyProps) => {
  const [repairFacility, setRepairFacility] = useState<
    "none" | "needed" | null
  >(null);
  const [repairDetail, setRepairDetail] = useState("");

  const [completionOption, setCompletionOption] = useState<
    "balance-day" | "custom" | null
  >(null);
  const [unrepairedOption, setUnrepairedOption] = useState<
    "balance-day" | "custom" | null
  >(null);
  const [lessorDuty, setLessorDuty] = useState("");
  const [lesseeDuty, setLesseeDuty] = useState("");

  const [showLessorNotice, setShowLessorNotice] = useState(false);
  const [showLesseeNotice, setShowLesseeNotice] = useState(false);

  const [middleDate, setMiddleDate] = useState<Date | null>(null); // 중도금
  const [balanceDate, setBalanceDate] = useState<Date | null>(null); // 잔금
  const [leaseStartDate, setLeaseStartDate] = useState<Date | null>(null); // 임대 시작일
  const [leaseEndDate, setLeaseEndDate] = useState<Date | null>(null); // 임대 종료일

  const [repairCompletionDate, setRepairCompletionDate] = useState<Date | null>(
    null
  ); // 수리 완료 날짜
  const [repairCustomEtc, setRepairCustomEtc] = useState(""); // 수리 완료 기타 내용

  const [unrepairedDeadline, setUnrepairedDeadline] = useState<Date | null>(
    null
  ); // 미수리한 경우 날짜
  const [unrepairedCustomEtc, setUnrepairedCustomEtc] = useState(""); // 미수리 기타 내용

  return (
    <div className="mt-10">
      <h3 className="text-lg font-extrabold">[계약내용]</h3>

      <p className="mt-4 text-base font-bold">
        제1조(보증금과 차임 및 관리비){" "}
        <span className="font-normal">
          위 부동산의 임대차에 관하여 임대인과 임차인은 합의에 의하여 보증금과
          차임 및 관리비를 아래와 같이 지불하기로 한다.
        </span>
      </p>

      <div className="mt-6 flex flex-col gap-3">
        <div className="flex items-center gap-2">
          <span className="w-24 text-base font-medium">보증금</span>
          <EditableInputBox
            value=""
            onChange={() => {}}
            placeholder="0"
            customWidth="w-[160px]"
          />
          <span className="text-sm font-medium">원</span>
        </div>

        <div className="flex items-center gap-2">
          <span className="w-24 text-base font-medium">계약금</span>
          <EditableInputBox
            value=""
            onChange={() => {}}
            placeholder="0"
            customWidth="w-[160px]"
          />
          <span className="text-sm font-medium">
            원은 계약시에 지불하고 영수함. 영수자
          </span>
          <div
            onClick={() => openSignatureModal("receipt")}
            className="w-[100px] h-[32px] border-2 border-neutral-light100 bg-neutral-light200 cursor-pointer"
          >
            {receiptSignature ? (
              <img src={receiptSignature} alt="서명" />
            ) : null}
          </div>
          <span className="text-sm font-medium">(인)</span>
        </div>

        {/* 중도금 */}
        <div className="flex items-center gap-2">
          <span className="w-24 text-base font-medium">중도금</span>
          <EditableInputBox
            value=""
            onChange={() => {}}
            placeholder="0"
            customWidth="w-[160px]"
          />
          <span className="text-sm font-medium">원은</span>
          <DatePickerInput
            selectedDate={middleDate}
            onChange={(date) => setMiddleDate(date)}
          />
          <span className="text-sm font-medium">에 지불하고</span>
        </div>

        {/* 잔금 */}
        <div className="flex items-center gap-2">
          <span className="w-24 text-base font-medium">잔금</span>
          <EditableInputBox
            value=""
            onChange={() => {}}
            placeholder="0"
            customWidth="w-[160px]"
          />
          <span className="text-sm font-medium">원은</span>
          <DatePickerInput
            selectedDate={balanceDate}
            onChange={(date) => setBalanceDate(date)}
          />
          <span className="text-sm font-medium">에 지불한다</span>
        </div>

        <div className="flex items-center gap-2">
          <span className="w-24 text-base font-medium">차임(월세)</span>
          <EditableInputBox
            value=""
            onChange={() => {}}
            placeholder="0"
            customWidth="w-[160px]"
          />
          <span className="text-sm font-medium">원은 매월</span>
          <EditableInputBox
            value=""
            onChange={() => {}}
            placeholder="일"
            customWidth="w-[40px]"
          />
          <span className="text-sm font-medium">일</span>
          <EditableInputBox
            value=""
            onChange={() => {}}
            placeholder="선불/후불"
            customWidth="w-[100px]"
          />
          <span className="text-sm font-medium">지불한다</span>
        </div>

        <div className="flex items-center gap-2 ml-24">
          <span className="w-24 text-base font-medium">입금계좌</span>
          <EditableInputBox
            value=""
            onChange={() => {}}
            placeholder="하이픈(-) 없이 숫자만 입력해주세요"
            customWidth="w-[400px]"
          />
        </div>

        <div className="flex items-center gap-2">
          <span className="w-22 text-base font-medium">관리비</span>
          <span className="w-32 text-base font-medium">정액인 경우</span>{" "}
          <EditableInputBox
            value=""
            onChange={() => {}}
            placeholder="0"
            customWidth="w-[160px]"
          />
          <span className="text-sm font-medium">원</span>
        </div>

        <div className="flex items-center gap-2 ml-24">
          {" "}
          <span className="w-32 text-base font-medium">
            정액이 아닌 경우
          </span>{" "}
          <EditableInputBox
            value=""
            onChange={() => {}}
            placeholder="관리비의 항목 및 산정방식을 기재해주세요"
            customWidth="w-[600px]"
          />
        </div>
        <p className="mt-4 text-base font-bold">
          제2조(임대차기간){" "}
          <span className="font-normal">
            임대인은 임차주택을 임대차 목적대로 사용·수익할 수 있는 상태로
          </span>
          <span className="inline-block ml-2">
            <DatePickerInput
              selectedDate={leaseStartDate}
              onChange={(date) => setLeaseStartDate(date)}
            />
          </span>
          <span className="font-normal ml-2">
            까지 임차인에게 인도하고, 임대차기간은 인도일로부터
          </span>
          <span className="inline-block ml-2">
            <DatePickerInput
              selectedDate={leaseEndDate}
              onChange={(date) => setLeaseEndDate(date)}
            />
          </span>
          <span className="font-normal ml-2">까지로 한다.</span>
        </p>

        <p className="mt-4 text-base font-bold">
          제3조(입주 전 수리){" "}
          <span className="font-normal">
            임대인과 임차인은 임차주택의 수리가 필요한 시설물 및 비용부담에
            관하여 다음과 같이 합의한다.
          </span>
        </p>

        {/* 수리 필요 시설 */}
        <div className="mt-4 flex gap-4 ml-2">
          <div className="w-32 text-base font-medium mt-2">수리 필요 시설</div>
          <div
            className={`border-3 px-4 py-3 rounded-sm flex flex-col gap-2 w-fit ${
              repairFacility === null ? "border-green" : "border-neutral-gray"
            }`}
          >
            <label className="flex items-center gap-2 text-sm font-bold cursor-pointer">
              <input
                type="radio"
                name="repairFacility"
                value="none"
                checked={repairFacility === "none"}
                onChange={() => setRepairFacility("none")}
                className="w-[16px] h-[16px] appearance-none border-2 border-neutral-dark200 
                   checked:bg-neutral-dark200 cursor-pointer"
              />
              없음
            </label>
            <label className="flex items-center gap-2 text-sm font-bold cursor-pointer">
              <input
                type="radio"
                name="repairFacility"
                value="needed"
                checked={repairFacility === "needed"}
                onChange={() => setRepairFacility("needed")}
                className="w-[16px] h-[16px] appearance-none border-2 border-neutral-dark200 
                   checked:bg-neutral-dark200 cursor-pointer"
              />
              있음 (수리할 내용:
              <EditableInputBox
                value={repairDetail}
                onChange={setRepairDetail}
                placeholder=""
                customWidth="w-[240px]"
              />
              )
            </label>
          </div>
        </div>

        {/* 수리 완료 시기 */}
        <div className="mt-4 flex gap-4 ml-2">
          <div className="w-32 text-base font-medium mt-2">수리 완료 시기</div>
          <div
            className={`border-3 px-4 py-3 rounded-sm flex flex-col gap-2 w-fit ${
              completionOption === null ? "border-green" : "border-neutral-gray"
            }`}
          >
            <label className="flex items-center gap-2 text-sm font-bold cursor-pointer">
              <input
                type="radio"
                name="completionOption"
                value="balance-day"
                checked={completionOption === "balance-day"}
                onChange={() => setCompletionOption("balance-day")}
                className="w-[16px] h-[16px] appearance-none border-2 border-neutral-dark200 
          checked:bg-neutral-dark200 cursor-pointer"
              />
              잔금지급 기일인
              <DatePickerInput
                selectedDate={repairCompletionDate}
                onChange={(date) => setRepairCompletionDate(date)}
              />
              까지
            </label>

            <label className="flex items-center gap-2 text-sm font-bold cursor-pointer">
              <input
                type="radio"
                name="completionOption"
                value="custom"
                checked={completionOption === "custom"}
                onChange={() => setCompletionOption("custom")}
                className="w-[16px] h-[16px] appearance-none border-2 border-neutral-dark200 
          checked:bg-neutral-dark200 cursor-pointer"
              />
              기타 (
              <EditableInputBox
                value={repairCustomEtc}
                onChange={(val) => setRepairCustomEtc(val)}
                placeholder=""
                customWidth="w-[280px]"
              />
              )
            </label>
          </div>
        </div>

        {/* 약정한 수리 완료 시기까지 미수리한 경우 */}
        <div className="mt-4 flex gap-4 ml-2">
          <div className="w-80 text-base font-medium mt-2">
            약정한 수리 완료 시기까지 미 수리한 경우
          </div>
          <div
            className={`border-3 px-4 py-3 rounded-sm flex flex-col gap-2 w-fit ${
              unrepairedOption === null ? "border-green" : "border-neutral-gray"
            }`}
          >
            <label className="flex items-center gap-2 text-sm font-bold cursor-pointer">
              <input
                type="radio"
                name="unrepairedOption"
                value="balance-day"
                checked={unrepairedOption === "balance-day"}
                onChange={() => setUnrepairedOption("balance-day")}
                className="w-[16px] h-[16px] appearance-none border-2 border-neutral-dark200 
          checked:bg-neutral-dark200 cursor-pointer"
              />
              잔금지급 기일인
              <DatePickerInput
                selectedDate={unrepairedDeadline}
                onChange={(date) => setUnrepairedDeadline(date)}
              />
              까지
            </label>

            <label className="flex items-center gap-2 text-sm font-bold cursor-pointer">
              <input
                type="radio"
                name="unrepairedOption"
                value="custom"
                checked={unrepairedOption === "custom"}
                onChange={() => setUnrepairedOption("custom")}
                className="w-[16px] h-[16px] appearance-none border-2 border-neutral-dark200 
          checked:bg-neutral-dark200 cursor-pointer"
              />
              기타 (
              <EditableInputBox
                value={unrepairedCustomEtc}
                onChange={(val) => setUnrepairedCustomEtc(val)}
                placeholder=""
                customWidth="w-[280px]"
              />
              )
            </label>
          </div>
        </div>

        {/* 제4조(임차주택의 사용·관리·수선) */}
        <div className="mt-10">
          <p className="text-base font-bold">
            제4조(임차주택의 사용·관리·수선){" "}
            <span className="font-normal">
              ① 임차인은 임대인의 동의 없이 임차주택의 구조 변경 및 전대나
              임차권 양도를 할 수 없으며, 임대차 목적 외 주거 이외의 용도로
              사용할 수 없다.
              <br />
              ② 임대인은 계약 존속 중 임차주택을 사용·수익에 필요한 상태로
              유지하여야 하고, 임차인은 임대인이 임차주택의 보존에 필요한 행위를
              하는 때 이를 거절하지 못한다.
              <br />③ 임대인과 임차인은 계약 존속 중에 발생하는 임차주택의 수리
              및 비용부담에 관하여 다음과 같이 합의한다. 다만, 합의되지 아니한
              기타 수선비용에 관한 부담은 민법, 판례 기타 관습에 따른다.
            </span>
          </p>

          {/* 임대인부담 */}
          <div className="mt-6 flex items-center gap-3">
            <span className="text-base font-medium whitespace-nowrap">
              임대인부담
            </span>
            <button
              type="button"
              onClick={() => setShowLessorNotice((prev) => !prev)}
              className="flex items-center justify-center relative top-[0.4px] cursor-pointer"
            >
              <span
                className="material-symbols-rounded text-neutral-dark200"
                style={{
                  fontVariationSettings: `'FILL' ${
                    showLessorNotice ? 1 : 0
                  }, 'wght' 400, 'GRAD' 0, 'opsz' 24`,
                  fontSize: "18px",
                  lineHeight: "1",
                }}
              >
                help
              </span>
            </button>
            <EditableInputBox
              value={lessorDuty}
              onChange={(val) => setLessorDuty(val)}
              placeholder=""
              customWidth="w-[600px]"
            />
          </div>
          {showLessorNotice && (
            <NoticeGray>
              난방, 상·하수도, 전기시설 등 임차주택의 주요설비에 대한
              노후·불량으로 인한 수선은 주로 임대인이 부담해요.
            </NoticeGray>
          )}

          {/* 임차인부담 */}
          <div className="mt-4 flex items-center gap-3">
            <span className="text-base font-medium whitespace-nowrap">
              임차인부담
            </span>
            <button
              type="button"
              onClick={() => setShowLesseeNotice((prev) => !prev)}
              className="flex items-center justify-center relative top-[0.4px] cursor-pointer"
            >
              <span
                className="material-symbols-rounded text-neutral-dark200"
                style={{
                  fontVariationSettings: `'FILL' ${
                    showLesseeNotice ? 1 : 0
                  }, 'wght' 400, 'GRAD' 0, 'opsz' 24`,
                  fontSize: "18px",
                  lineHeight: "1",
                }}
              >
                help
              </span>
            </button>
            <EditableInputBox
              value={lesseeDuty}
              onChange={(val) => setLesseeDuty(val)}
              placeholder=""
              customWidth="w-[600px]"
            />
          </div>
          {showLesseeNotice && (
            <NoticeGray>
              임차인의 고의·과실에 의한 파손, 전구 등의 간단한 수선, 소모품 교체
              비용은 주로 임차인이 부담해요.
            </NoticeGray>
          )}

          <p className="mt-4 text-base font-normal">
            ④ 임차인이 임대인의 부담에 속하는 수선비용을 지출한 때에는
            임대인에게 그 상환을 청구할 수 있다.
          </p>
        </div>
        <div className="mt-10 text-base font-normal leading-relaxed">
          <p className="font-bold">제5조(계약의 해제)</p>
          <p>
            임차인이 임대인에게 중도금(중도금이 없을 때는 잔금)을 지급하기
            전까지, 임대인은 계약금의 배액을 상환하고, 임차인은 계약금을
            포기하고 이 계약을 해제할 수 있다.
          </p>

          <p className="mt-4 font-bold">제6조(채무불이행과 손해배상)</p>
          <p>
            당사자 일방이 채무를 이행하지 아니하는 때에는 상대방은 상당한 기간을
            정하여 그 이행을 최고하고 계약을 해제할 수 있으며, 그로 인한
            손해배상을 청구할 수 있다. 다만, 채무자가 미리 이행하지 아니할
            의사를 표시한 경우의 계약해제는 최고를 요하지 아니한다.
          </p>

          <p className="mt-4 font-bold">제7조(계약의 해지)</p>
          <p>
            ① 임차인은 본인의 과실 없이 임차주택의 일부가 멸실 기타 사유로
            인하여 임대차의 목적대로 사용할 수 없는 경우에는 계약을 해지할 수
            있다.
            <br />② 임대인은 임차인이 2기의 차임액에 달할도록 연체하거나, 제4조
            제1항을 위반한 경우 계약을 해지할 수 있다.
          </p>

          <p className="mt-4 font-bold">제8조(갱신요구와 거절)</p>
          <p>
            ① 임차인은 임대차기간이 끝나기 6개월 전부터 2개월 전까지의 기간에
            계약갱신을 요구할 수 있다. 다만, 임대인은 자신 또는 그
            직계존속·직계비속의 실거주 등 주택임대차보호법 제6조의3 제1항 각
            호의 사유가 있는 경우에 한하여 계약갱신의 요구를 거절할 수 있다.
            <span className="font-medium text-sm ml-2">
              ※ 법무부에서 제공하는 계약갱신 거절통지서 양식 사용 가능
            </span>
            <br />
            ② 임대인이 주택임대차보호법 제6조의3 제1항 제2호에 따른 실거주를
            사유로 갱신을 거절하였음에도 불구하고 갱신요구가 거절되지
            아니하였더라면 갱신되었을 기간이 만료되기 전에 정당한 사유 없이
            제3자에게 주택을 임대할 경우, 임대인은 갱신거절로 인하여 임차인이
            입은 손해를 배상하여야 한다.
            <br />③ 제2항에 따른 손해배상액은 주택임대차보호법 제6조의3 제6항에
            의한다.
          </p>

          <p className="mt-4 font-bold">제9조(계약의 종료)</p>
          <p>
            임대차계약이 종료된 경우에 임차인은 임차주택을 원래의 상태로
            복구하여 임대인에게 반환하고, 이와 동시에 임대인은 보증금을
            임차인에게 반환하여야 한다. 다만, 시설물의 노후화나 통상 생길 수
            있는 파손 등은 임차인의 원상복구의무에 포함되지 아니한다.
          </p>

          <p className="mt-4 font-bold">제10조(비용의 정산)</p>
          <p>
            ① 임차인은 계약종료 시 공과금과 관리비를 정산하여야 한다.
            <br />② 임차인은 이미 납부한 관리비 등 장기수선충당금을
            임대인(소유자인 경우)에 반환 청구할 수 있다. 다만, 관리사무소 등
            관리주체가 장기수선충당금을 정산하는 경우에는 그 관리주체에게 청구할
            수 있다.
          </p>

          <p className="mt-4 font-bold">제11조(분쟁의 해결)</p>
          <p>
            임대인과 임차인은 본 임대차계약과 관련한 분쟁이 발생하는 경우,
            당사자 간의 협의 또는 주택임대차분쟁조정위원회의 조정을 통해
            호혜적으로 해결하기 위해 노력한다.
          </p>
        </div>
      </div>
    </div>
  );
};

export default ContractBody;
