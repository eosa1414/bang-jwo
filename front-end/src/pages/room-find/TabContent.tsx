import { ReactNode } from "react";

interface TabContentProps {
  title?: string;
  children: ReactNode;
}

const TabContent = ({ title = "", children }: TabContentProps) => {
  return (
    <div className="flex flex-col gap-3">
      {title && <div className="font-bold text-base">{title}</div>}
      {children}
    </div>
  );
};

export default TabContent;
