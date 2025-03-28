import React from "react";

// InputBox Component
export const InputBox = ({
  width = "w-full",
  placeholder = "",
}: {
  width?: string;
  placeholder?: string;
}) => (
  <input
    type="text"
    className={`border-2 border-green-500 rounded-sm ${width} h-8 px-2 whitespace-nowrap`}
    placeholder={placeholder}
  />
);

// Checkbox Component
export const Checkbox = ({ label }: { label: string }) => {
  const [checked, setChecked] = React.useState(false);
  return (
    <div
      className="flex items-center space-x-1 cursor-pointer select-none whitespace-nowrap"
      onClick={() => setChecked(!checked)}
    >
      <div
        className={`w-4 h-4 border-2 border-green-500 flex items-center justify-center transition-colors duration-150 ${
          checked ? "bg-green-500" : "bg-white"
        }`}
      />
      <span>{label}</span>
    </div>
  );
};

// Main Contract Component
const SellerContract = () => {
  return (
    <div className="max-w-4xl mx-auto p-10 space-y-6 bg-gray-50">
      <h1 className="text-center text-xl font-bold">주택임대차계약서</h1>

      <div className="flex items-center flex-wrap gap-2">
        <span className="whitespace-nowrap">임대인</span>
        <InputBox width="w-32" />
        <span className="whitespace-nowrap">와/과 임차인</span>
        <InputBox width="w-32" />
        <span className="whitespace-nowrap">
          은/는 아래와 같이 임대차 계약을 체결한다.
        </span>

        <div className="ml-auto border-2 border-green-500 p-2 space-y-2">
          <Checkbox label="보증금 없는 월세" />
          <Checkbox label="월세" />
        </div>
      </div>

      <div>
        <h2 className="font-bold">[임차주택의 표시]</h2>
        <div className="border-2 border-green-500 p-4 space-y-2 mt-2">
          <div className="flex items-center space-x-2">
            <span>소재지</span>
            <InputBox />
          </div>
          <div className="flex items-center space-x-2">
            <span>토지 지목</span>
            <InputBox width="w-24" />
            <span>면적</span>
            <InputBox width="w-24" />
            <span>m²</span>
          </div>
          <div className="flex items-center space-x-2">
            <span>건물 구조</span>
            <InputBox width="w-24" />
            <span>용도</span>
            <InputBox width="w-24" />
            <span>면적</span>
            <InputBox width="w-24" />
            <span>m²</span>
          </div>
          <div className="flex items-center space-x-2">
            <span>임차부분</span>
            <InputBox />
            <span>면적</span>
            <InputBox width="w-24" />
            <span>m²</span>
          </div>
        </div>
      </div>

      <div className="border-2 border-green-500 p-4 space-y-2">
        <h2 className="font-bold">계약의 종류</h2>
        <Checkbox label="신규 계약" />
        <Checkbox label="합의에 의한 재계약" />
        <Checkbox label="주택임대차보호법 제6조의3의 계약갱신요구권 행사에 의한 갱신계약" />

        <div className="space-y-1">
          <div className="flex items-center space-x-2">
            <span>계약 기간</span>
            <InputBox width="w-24" />
            <span>부터</span>
            <InputBox width="w-24" />
            <span>까지</span>
          </div>
          <div className="flex items-center space-x-2">
            <span>보증금</span>
            <InputBox width="w-32" />
            <span>원</span>
          </div>
          <div className="flex items-center space-x-2">
            <span>차임 월</span>
            <InputBox width="w-24" />
            <span>원</span>
          </div>
        </div>
      </div>

      <div className="border-2 border-green-500 p-4 space-y-2">
        <h2 className="font-bold">미납 국세·지방세</h2>
        <Checkbox label="없음 (임대인 서명 (인) )" />
        <Checkbox label="있음" />
      </div>

      <div className="border-2 border-green-500 p-4 space-y-2">
        <h2 className="font-bold">선순위 확정일자 현황</h2>
        <Checkbox label="해당 없음 ( 임대인 서명 (인) )" />
        <Checkbox label="해당 있음" />
      </div>
    </div>
  );
};

export default SellerContract;
