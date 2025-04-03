import DisabledInputBox from "./DisabledInputBox";

const LesseeInfoSection = () => {
  return (
    <div className="mt-10 text-base leading-relaxed">
      {/* 임차인 + 주소 */}
      <div className="flex items-center gap-10 mb-4">
        <span className="w-[60px] font-bold">임차인</span>
        <span className="w-[76px]">주소</span>
        <DisabledInputBox placeholder="" />
      </div>

      {/* 아래 항목 들여쓰기 맞추기 */}
      <div className="ml-[100px] flex flex-col gap-4">
        {/* 주민등록번호 */}
        <div className="flex items-center gap-4">
          <span className="w-[100px]">주민등록번호</span>
          <DisabledInputBox placeholder="" />
        </div>

        {/* 전화 */}
        <div className="flex items-center gap-4">
          <span className="w-[100px]">전화</span>
          <DisabledInputBox placeholder="" />
        </div>

        {/* 성명 */}
        <div className="flex items-center gap-4">
          <span className="w-[100px]">성명</span>
          <DisabledInputBox placeholder="" />
        </div>

        {/* 서명 */}
        <div className="flex items-center gap-4">
          <span className="w-[100px]">서명</span>
          <div className="flex items-center gap-2">
            <DisabledInputBox placeholder="" />
            <span className="text-sm font-medium">(인)</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LesseeInfoSection;
