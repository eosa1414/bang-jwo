import React from "react";

const ContractNotice = () => {
  return (
    <div className="flex items-center gap-2 px-4 py-3 bg-neutral-dark200 text-white rounded-xl text-sm shadow-lg w-fit">
      <i className="material-symbols-rounded text-white text-base">info</i>
      <span className="font-bold">
        토지·건물 관련 항목은 건축물대장을 확인해주세요.
      </span>
    </div>
  );
};

export default ContractNotice;
