import { useState, useEffect } from "react";
import EditableInputBox from "./EditableInputBox";
import DisabledInputBox from "./DisabledInputBox";
import NoticeGray from "../../../components/notices/NoticeGray";

interface ContractFooterSectionProps {
  mode: "lessor" | "lessee";
}

const ContractFooterSection = ({ mode }: ContractFooterSectionProps) => {
  const isLessor = mode === "lessor";
  const isLessee = mode === "lessee";

  // 날짜
  const [contractDate, setContractDate] = useState("");

  useEffect(() => {
    const today = new Date();
    const formatted = `${today.getFullYear()}년 ${String(
      today.getMonth() + 1
    ).padStart(2, "0")}월 ${String(today.getDate()).padStart(2, "0")}일`;
    setContractDate(formatted);
  }, []);

  // 인적사항 상태들을 객체로 묶기
  const [info, setInfo] = useState({
    lessor: {
      address: "",
      ssn: "",
      phone: "",
      name: "",
    },
    lessee: {
      address: "",
      ssn: "",
      phone: "",
      name: "",
    },
  });

  const handleChange = (
    role: "lessor" | "lessee",
    key: string,
    value: string
  ) => {
    setInfo((prev) => ({
      ...prev,
      [role]: {
        ...prev[role],
        [key]: value,
      },
    }));
  };

  const [showSignatureNotice, setShowSignatureNotice] = useState(false);

  // 공통 입력박스 렌더 함수
  const renderInputBox = (
    role: "lessor" | "lessee",
    key: keyof typeof info.lessor,
    placeholder?: string,
    customWidth?: string
  ) => {
    const editable =
      (role === "lessor" && isLessor) || (role === "lessee" && isLessee);
    const value = info[role][key];
    return editable ? (
      <EditableInputBox
        value={value}
        onChange={(val) => handleChange(role, key, val)}
        placeholder={placeholder}
        customWidth={customWidth}
      />
    ) : (
      <DisabledInputBox
        value={value}
        placeholder={placeholder}
        customWidth={customWidth}
      />
    );
  };

  return (
    <div className="mt-10 text-base leading-relaxed">
      <div className="mt-10 text-base leading-relaxed">
        {/* 문구 + 날짜 */}
        <p className="mb-6 font-bold">
          본 계약을 증명하기 위하여 임대인, 임차인은 이의 없음을 확인하고 각자
          서명한 후 1통씩 보관한다.
        </p>

        <div className="flex items-center gap-4 mb-6">
          <span className="w-[60px] font-bold">날짜</span>
          <DisabledInputBox value={contractDate} customWidth="w-[220px]" />
        </div>
      </div>

      {/* 임대인, 임차인 정보 */}
      {["lessor", "lessee"].map((roleKey) => {
        const role = roleKey as "lessor" | "lessee";
        const label = role === "lessor" ? "임대인" : "임차인";
        const editable =
          (role === "lessor" && isLessor) || (role === "lessee" && isLessee);

        return (
          <div key={role} className="mt-6">
            <div className="flex items-center gap-14 mb-4">
              <span className="w-[51px] font-bold">{label}</span>

              {/* 주소 */}
              <div className="flex items-center gap-4 w-full">
                <span className="w-[100px]">주소</span>
                {renderInputBox(role, "address", "", "w-full")}
              </div>
            </div>

            <div className="ml-[100px] flex flex-col gap-4">
              {/* 주민등록번호 */}
              <div className="flex items-center gap-4">
                <span className="w-[100px]">주민등록번호</span>
                {renderInputBox(
                  role,
                  "ssn",
                  "하이픈(-) 없이 숫자만 입력해주세요",
                  "w-[300px]"
                )}
              </div>

              {/* 전화 */}
              <div className="flex items-center gap-4">
                <span className="w-[100px]">전화</span>
                {renderInputBox(
                  role,
                  "phone",
                  "하이픈(-) 없이 숫자만 입력해주세요",
                  "w-[300px]"
                )}
              </div>

              {/* 성명 */}
              <div className="flex items-center gap-4">
                <span className="w-[100px]">성명</span>
                {renderInputBox(role, "name", "", "w-[160px]")}
              </div>

              {/* 서명 */}
              <div className="flex items-center gap-4">
                <div className="w-[100px] flex items-center gap-1">
                  <span>서명</span>
                  {editable && (
                    <button
                      type="button"
                      onClick={() => setShowSignatureNotice((prev) => !prev)}
                      className="material-symbols-rounded text-lg text-neutral-dark100 hover:text-neutral-black cursor-pointer mt-0.5"
                    >
                      help
                    </button>
                  )}
                </div>
                <div className="flex items-center gap-2">
                  <DisabledInputBox placeholder="" />
                  <span className="text-sm font-medium">(인)</span>
                </div>
              </div>

              {showSignatureNotice && editable && (
                <NoticeGray arrowOffsetLeft="28px">
                  서명은 계약서 최종 확인 후에 작성해요.
                </NoticeGray>
              )}
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default ContractFooterSection;
