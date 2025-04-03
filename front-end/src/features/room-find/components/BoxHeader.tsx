import { forwardRef } from "react";

interface BoxHeaderProps {
  isHeaderColorChange: boolean;
  isTitleScrolled: boolean;
  onClose: () => void;
}

const BoxHeader = forwardRef<HTMLDivElement, BoxHeaderProps>(
  ({ isHeaderColorChange, isTitleScrolled, onClose }, ref) => {
    return (
      <div
        ref={ref}
        className={`absolute z-1 w-full flex justify-between items-center p-[12px_14px] h-[48px] transition-colors duration-300 ${
          isHeaderColorChange
            ? "bg-real-white text-neutral-black"
            : "text-neutral-white"
        }`}
      >
        <i className="material-symbols-rounded">arrow_back_ios</i>
        {isTitleScrolled && <span className="font-bold">월세 500/40</span>}
        <i
          className="material-symbols-rounded cursor-pointer"
          onClick={onClose}
        >
          close
        </i>
      </div>
    );
  }
);

export default BoxHeader;
