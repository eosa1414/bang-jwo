import { useState } from "react";
import EditableInputBox from "./EditableInputBox";
import DisabledInputBox from "./DisabledInputBox";
import NoticeGray from "../../../components/notices/NoticeGray";

const LessorInfoSection = () => {
  const [date, setDate] = useState("");
  const [address, setAddress] = useState("");
  const [ssn, setSSN] = useState("");
  const [phone, setPhone] = useState("");
  const [name, setName] = useState("");

  const [showSignatureNotice, setShowSignatureNotice] = useState(false); // 🔥 서명 ? 툴팁 토글용 상태

  return (
    <div className="mt-10 text-base leading-relaxed">
      <p className="mb-6 font-bold">
        본 계약을 증명하기 위하여 임대인, 임차인은 이의 없음을 확인하고 각자
        서명한 후 1통씩 보관한다.
      </p>

      {/* 날짜 */}
      <div className="flex items-center gap-4 mb-6">
        <span className="w-[60px] font-bold">날짜</span>
        <EditableInputBox
          value={date}
          onChange={setDate}
          placeholder="계약서 작성 날짜를 입력해주세요"
          customWidth="w-[220px]"
        />
      </div>

      {/* 주소 */}
      <div className="flex items-center gap-14 mb-4">
        <span className="w-[60px] font-bold">임대인</span>
        <span className="w-[82px]">주소</span>
        <EditableInputBox
          value={address}
          onChange={setAddress}
          customWidth="w-full"
        />
      </div>

      {/* 주민등록번호 */}
      <div className="flex items-center gap-4 mb-4 ml-[100px]">
        <span className="w-[100px]">주민등록번호</span>
        <EditableInputBox
          value={ssn}
          onChange={setSSN}
          placeholder="하이픈(-) 없이 숫자만 입력해주세요"
          customWidth="w-[300px]"
        />
      </div>

      {/* 전화 */}
      <div className="flex items-center gap-4 mb-4 ml-[100px]">
        <span className="w-[100px]">전화</span>
        <EditableInputBox
          value={phone}
          onChange={setPhone}
          placeholder="하이픈(-) 없이 숫자만 입력해주세요"
          customWidth="w-[300px]"
        />
      </div>

      {/* 성명 */}
      <div className="flex items-center gap-4 mb-4 ml-[100px]">
        <span className="w-[100px]">성명</span>
        <EditableInputBox
          value={name}
          onChange={setName}
          customWidth="w-[160px]"
        />
      </div>

      <div className="flex flex-col gap-2 ml-[100px]">
        <div className="flex items-center gap-4">
          <div className="flex items-center gap-1 w-[100px]">
            <span>서명</span>
            <button
              type="button"
              onClick={() => setShowSignatureNotice((prev) => !prev)}
              className="material-symbols-rounded text-lg text-neutral-dark100 hover:text-neutral-black cursor-pointer mt-0.5"
            >
              help
            </button>
          </div>

          <div className="flex items-center gap-2">
            <DisabledInputBox placeholder="" />
            <span className="text-sm font-medium">(인)</span>
          </div>
        </div>

        {showSignatureNotice && (
          <NoticeGray arrowOffsetLeft="28px">
            서명은 계약서 최종 확인 후에 작성해요.
          </NoticeGray>
        )}
      </div>
    </div>
  );
};

export default LessorInfoSection;
