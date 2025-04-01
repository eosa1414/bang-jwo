import { ReactNode } from "react";

interface LineBoxProps {
  children: ReactNode;
  addClassName?: string;
}

const LineBox = ({ children, addClassName = "" }: LineBoxProps) => {
  return (
    <div
      className={`p-[2.125rem_1.5rem] w-full border-1 border-neutral-light100 rounded-md ${addClassName}`}
    >
      {children}
    </div>
  );
};

export default LineBox;
