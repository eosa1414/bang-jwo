import { ReactNode } from "react";
import InfoText from "../components/InfoText";
import Button from "../components/buttons/Button";

interface LayoutNoticeProps {
  //   icon?: string;
  infoText?: string;
  buttonText?: string;
  bgColor?: "gold" | "neutral-dark300";
  children: ReactNode;
  onClick: () => void;
}

const LayoutNotice = ({
  //   icon,
  infoText = "",
  buttonText,
  bgColor = "gold",
  children,
  onClick,
}: LayoutNoticeProps) => {
  return (
    <div
      className={`w-full min-h-[calc(100vh-55px-44px)] bg-${bgColor} flex items-center justify-center`}
    >
      <div className="items-center justify-center text-center bg-neutral-white rounded-md p-6 border-1 flex flex-col gap-6">
        <div className="self-start">
          {infoText && <InfoText text={infoText} />}
        </div>
        {children}
        {buttonText && (
          <Button size="large" variant="point" isLine onClick={onClick}>
            {buttonText}
          </Button>
        )}
      </div>
    </div>
  );
};

export default LayoutNotice;
