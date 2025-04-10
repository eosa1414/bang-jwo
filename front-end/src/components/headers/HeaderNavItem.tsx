import { ReactNode } from "react";

interface HeaderNavItemProps {
  children: ReactNode;
  onClick?: () => void;
}

const HeaderNavItem = ({ children, onClick }: HeaderNavItemProps) => {
  return (
    <li
      onClick={onClick}
      className="text-neutral-black px-2 py-1 hover:bg-neutral-black/10 rounded-md transition-all cursor-pointer"
    >
      {children}
    </li>
  );
};

export default HeaderNavItem;
