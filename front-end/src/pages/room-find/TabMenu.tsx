import { forwardRef } from "react";

interface TabMenuProps {
  AddClassName?: string;
}

const TabMenu = ({ AddClassName }: TabMenuProps) => {
  return (
    <div
      className={`w-full flex overflow-x-auto bg-real-white ${AddClassName}`}
    >
      <div>기본 정보</div>
      <div>관리비</div>
      <div>집 소개</div>
      <div>집 정보</div>
    </div>
  );
};

export default TabMenu;
