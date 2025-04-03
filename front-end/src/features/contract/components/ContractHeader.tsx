import RentTypeSelector from "./RentTypeSelector";
import EditableInputBox from "./EditableInputBox";
import DisabledInputBox from "./DisabledInputBox";
import NoticeDefault from "../../../components/notices/NoticeDefault";
import hasBatchim from "../utils/hasBatchim";

interface ContractHeaderProps {
  lessorName: string;
  lesseeName: string;
  onLessorNameChange: (value: string) => void;
}

const ContractHeader = ({
  lessorName,
  lesseeName,
  onLessorNameChange,
}: ContractHeaderProps) => {
  const waGwa =
    lessorName.length >= 2 ? (hasBatchim(lessorName) ? "과" : "와") : "와";
  const eunNeun =
    lesseeName.length >= 2 ? (hasBatchim(lesseeName) ? "은" : "는") : "는";

  return (
    <>
      <NoticeDefault>
        이 계약서는 법무부에서 제공하는 주택임대차표준계약서를 중개인 항목을
        제외하여 재구성한 것입니다.
        <br />
        법의 보호를 받기 위해 계약서 하단의 중요확인사항을 꼭 확인하시기
        바랍니다.
      </NoticeDefault>

      <h2 className="mt-10 text-2xl font-extrabold text-center">
        주택임대차계약서
      </h2>

      <div className="mt-4 flex justify-end">
        <RentTypeSelector />
      </div>

      {/* 계약 문장 */}
      <div className="mt-10 text-base font-medium flex flex-wrap items-center gap-2">
        <span>임대인</span>
        <EditableInputBox
          value={lessorName}
          onChange={onLessorNameChange}
          placeholder="성명"
          minLength={2}
          maxLength={10}
        />
        <span>{waGwa} 임차인</span>
        <DisabledInputBox value={lesseeName} placeholder="성명" />
        <span>{eunNeun} 아래와 같이 임대차 계약을 체결한다.</span>
      </div>
    </>
  );
};

export default ContractHeader;
