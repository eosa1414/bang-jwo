import { Dispatch, SetStateAction, useState } from "react";
import EditableInputBox from "./EditableInputBox";
import DisabledInputBox from "./DisabledInputBox";
import NoticeGray from "../../../components/notices/NoticeGray";

interface FooterInfo {
  address: string; // 임대인/임차인 주소
  ssn: string; // 임대인/임차인 주민등록번호
  phone: string; // 임대인/임차인 전화번호
  name: string; // 임대인/임차인 이름
}

interface FooterState {
  lessor: FooterInfo; // 임대인 정보
  lessee: FooterInfo; // 임차인 정보
}

// Props 인터페이스에 contractWrittenDate 추가
interface ContractFooterSectionProps {
  mode: "lessor" | "lessee";
  footerInfo: FooterState;
  setFooterInfo: Dispatch<SetStateAction<FooterState>>;
  contractWrittenDate: string;
  setContractWrittenDate: Dispatch<SetStateAction<string>>;
}

const ContractFooterSection = ({
  mode,
  footerInfo,
  setFooterInfo,
  contractWrittenDate,
  setContractWrittenDate,
}: ContractFooterSectionProps) => {
  // 현재 로그인된(혹은 편집 권한이 있는) 사람이 임대인인지 임차인인지에 따라
  // 어떤 필드를 수정할 수 있을지 결정
  const isLessor = mode === "lessor";
  const isLessee = mode === "lessee";
  setContractWrittenDate("2025.04.11");

  /**
   * FooterInfo 갱신 함수
   * role: "lessor" | "lessee"
   * key: FooterInfo의 키(address, ssn, phone, name)
   * value: 변경할 값
   */
  const handleChange = (
    role: "lessor" | "lessee",
    key: keyof FooterInfo,
    value: string
  ) => {
    setFooterInfo((prev) => ({
      ...prev,
      [role]: {
        ...prev[role],
        [key]: value,
      },
    }));
  };

  /**
   * role(임대인/임차인)에 따라 Editable 또는 Disabled InputBox를 그려줍니다.
   * mode === role 인 경우에만 수정 가능하도록 설정 (임대인모드 -> 임대인 필드만 수정)
   */
  const renderInputBox = (
    role: "lessor" | "lessee",
    key: keyof FooterInfo,
    placeholder?: string,
    customWidth?: string
  ) => {
    const editable =
      (role === "lessor" && isLessor) || (role === "lessee" && isLessee);
    const value = footerInfo[role][key];

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
        <p className="mb-6 font-bold">
          본 계약을 증명하기 위하여 임대인, 임차인은 이의 없음을 확인하고 각자
          서명한 후 1통씩 보관한다.
        </p>

        {/* 계약서 작성일 */}
        <div className="flex items-center gap-4 mb-6">
          <span className="w-[60px] font-bold">날짜</span>
          {mode === "lessor" ? (
            <EditableInputBox
              value={contractWrittenDate}
              onChange={(val) => setContractWrittenDate(val)}
              customWidth="w-[220px]"
            />
          ) : (
            <DisabledInputBox
              value={contractWrittenDate}
              customWidth="w-[220px]"
            />
          )}
        </div>
      </div>

      {/* 임대인, 임차인 정보 입력 */}
      {["lessor", "lessee"].map((roleKey) => {
        const [showSignatureHelp, setShowSignatureHelp] = useState(false);
        const role = roleKey;
        const label = role === "lessor" ? "임대인" : "임차인";
        const editable =
          (role === "lessor" && isLessor) || (role === "lessee" && isLessee);

        if (role !== "lessor" && role !== "lessee") {
          return; //임시 타입스크립트 오류 해결
        }

        return (
          <div key={role} className="mt-6">
            <div className="flex items-center gap-14 mb-4">
              <span className="w-[51px] font-bold">{label}</span>
              <div className="flex items-center gap-4 w-full">
                <span className="w-[100px]">주소</span>
                {renderInputBox(role, "address", "", "w-full")}
              </div>
            </div>
            <div className="ml-[100px] flex flex-col gap-4">
              <div className="flex items-center gap-4">
                <span className="w-[100px]">주민등록번호</span>
                {renderInputBox(role, "ssn", "", "w-[300px]")}
              </div>
              <div className="flex items-center gap-4">
                <span className="w-[100px]">전화</span>
                {renderInputBox(role, "phone", "", "w-[300px]")}
              </div>
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
                      onClick={() => setShowSignatureHelp((prev) => !prev)}
                      className="material-symbols-rounded text-lg text-neutral-dark100 hover:text-neutral-black cursor-pointer mt-0.5"
                    >
                      <span
                        className="material-symbols-rounded text-neutral-dark200"
                        style={{
                          fontVariationSettings: `'FILL' ${
                            showSignatureHelp ? 1 : 0
                          }, 'wght' 400, 'GRAD' 0, 'opsz' 24`,
                          fontSize: "18px",
                          lineHeight: "1",
                        }}
                      >
                        help
                      </span>
                    </button>
                  )}
                </div>
                <div className="flex items-center gap-2">
                  <DisabledInputBox placeholder="" />
                  <span className="text-sm font-medium">(인)</span>
                </div>
              </div>

              {/* 안내 문구 */}
              {showSignatureHelp && (
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
