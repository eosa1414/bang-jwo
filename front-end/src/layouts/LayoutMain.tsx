import { ReactNode } from "react";
import HeaderDefault from "../components/headers/HeaderDefault";
import FooterDefault from "../components/footers/FooterDefault";

interface LayoutMainProps {
  children: ReactNode;
  wrapperClassName?: string;
  mainClassName?: string;
  darkHeader?: boolean;
  hasFooter?: boolean;
}

const LayoutMain = ({
  children,
  wrapperClassName = "",
  mainClassName = "",
  darkHeader,
  hasFooter = true,
}: LayoutMainProps) => {
  return (
    <div className={`w-full min-h-screen flex flex-col ${wrapperClassName}`}>
      <HeaderDefault variant={darkHeader ? "dark" : "light"} />
      <main className={`flex-grow ${mainClassName}`}>{children}</main>
      {hasFooter && <FooterDefault />}
    </div>
  );
};

export default LayoutMain;
