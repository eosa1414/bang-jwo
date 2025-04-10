import { HTMLAttributes, MouseEvent, forwardRef } from "react";
import MaterialIcon from "../MaterialIcon";

interface ButtonIconProps extends HTMLAttributes<HTMLDivElement> {
  icon: string;
  onClick?: ((e: MouseEvent<HTMLDivElement>) => void) | (() => void);
  addClassName?: string;
}

// forwardRef로 ref를 전달받을 수 있게 수정
const ButtonIcon = forwardRef<HTMLDivElement, ButtonIconProps>(
  ({ icon, onClick, addClassName }, ref) => {
    return (
      <div
        ref={ref} // ref 전달
        className={`flex items-center justify-center cursor-pointer rounded-full hover:bg-neutral-black/10 p-2 transition-all ${addClassName}`}
        onClick={onClick}
      >
        <MaterialIcon icon={icon} />
      </div>
    );
  }
);

// ButtonIcon 컴포넌트에 displayName을 설정
ButtonIcon.displayName = "ButtonIcon";

export default ButtonIcon;
