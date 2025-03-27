import { ReactNode } from "react";
import HeaderDefault from "../components/headers/HeaderDefault";
import FooterDefault from "../components/footers/FooterDefault";

interface LayoutMainProps {
  children: ReactNode;
  mainClassName?: string; //main 추가 스타일
  hasFooter?: boolean;
}

const LayoutMain = ({
  children,
  wrapperClassName = "",
  mainClassName = "",
  hasFooter = true,
}: LayoutMainProps) => {
  return (
    <div className={`w-full min-h-screen flex flex-col ${wrapperClassName}`}>
      <HeaderDefault />
      <main className={`flex-grow ${mainClassName}`}>{children}</main>
      {hasFooter && <FooterDefault />}
    </div>
  );
};

export default LayoutMain;
