import { Link } from "react-router-dom";
import Logo from "/logo.svg";

interface HeaderProps {
  title?: string;
}

const Header = ({ title }: HeaderProps) => {
  return (
    <header className="flex w-full h-[55px] px-[14px] justify-between items-center border-b border-neutral-light100">
      <Link to="/" className="flex items-center gap-2">
        <img src={Logo} className="w-[35.28px]" alt="BangJwo logo" />
        <span className="text-lg font-['TmonMonsori'] text-gold">방줘</span>
      </Link>

      <div className="text-base font-bold">
        {title || "텍스트"}
      </div>

      <div className="flex items-center mr-4">
        <i className="material-symbols-rounded text-xl">chat</i>
      </div>
    </header>
  );
};

export default Header;
