import { ReactNode } from "react";
import InfoText from "./InfoText";

interface TitleBoxProps {
  title?: string;
  detail?: string;
  required?: boolean;
  infoText?: string;
  children: ReactNode;
}

const TitleBox = ({
  title,
  detail,
  required,
  infoText,
  children,
}: TitleBoxProps) => {
  return (
    <div className="w-full flex flex-col gap-2">
      {(title || required || detail) && (
        <p className="font-bold text-lg">
          {title && <span className="whitespace-pre-line">{title}</span>}
          {required && <span className="text-coral-red">*</span>}
          {detail && <span className="text-gold-dark">{detail}</span>}
        </p>
      )}
      {infoText && (
        <p>
          <InfoText text={infoText} />
        </p>
      )}
      {children}
    </div>
  );
};

export default TitleBox;
