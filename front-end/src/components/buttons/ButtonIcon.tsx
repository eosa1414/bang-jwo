import { ButtonHTMLAttributes } from "react";
import MaterialIcon from "../MaterialIcon";

interface ButtonIconProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  icon: string;
  onClick?: () => void;
  addClassName?: string;
}

const ButtonIcon = ({ icon, onClick, addClassName }: ButtonIconProps) => {
  return (
    <div
      className={`flex items-center justify-center cursor-pointer rounded-full hover:bg-neutral-black/10 p-2 transition-all ${addClassName}`}
      onClick={onClick}
    >
      <MaterialIcon icon={icon} />
    </div>
  );
};

export default ButtonIcon;
