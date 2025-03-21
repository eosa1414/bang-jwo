import { ReactNode } from "react";
import HeaderDefault from "../components/headers/HeaderDefault";
import FooterDefault from "../components/footers/FooterDefault";

interface LayoutMainProps {
  children: ReactNode;
}

const LayoutMain = ({ children }: LayoutMainProps) => {
  return (
    <div className="w-full min-h-screen flex flex-col">
      <HeaderDefault />
      <main className="flex-grow">{children}</main>
      <FooterDefault />
    </div>
  );
};

export default LayoutMain;
