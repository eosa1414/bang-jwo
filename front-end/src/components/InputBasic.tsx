import { ChangeEvent } from "react";

type InputBasicProps = {
  name?: string;
  value: string | number | undefined | null; //재사용성을 높이기 위하여 undefined와 null 허용
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  placeholder?: string;
  type?: string;
  text?: string;
  size?: "small" | "medium" | "large";
  required?: boolean;
};

const InputBasic = ({
  name,
  value,
  onChange,
  placeholder,
  type = "text",
  text,
  size = "medium",
  required,
}: InputBasicProps) => {
  // tailwind에 맞춰서 계단식으로 계산
  const getPaddingRightClass = (text?: string) => {
    if (!text) return "";
    const length = text.length;
    if (length <= 1) return "pr-7";
    if (length <= 2) return "pr-11";
    if (length <= 4) return "pr-15";
    return "pr-19";
  };

  return (
    <div
      className={`relative ${
        size === "large"
          ? "flex-grow w-full"
          : size === "small"
          ? "w-[8.75rem]"
          : ""
      }`}
    >
      <input
        name={name}
        type={type}
        value={value ?? ""} //undefined와 null 처리
        onChange={onChange}
        required={required}
        placeholder={placeholder}
        className={`w-full border border-neutral-light100 px-3 py-2 rounded-md focus:outline-none focus:ring-2 focus:ring-inset focus:ring-gold-light ${getPaddingRightClass(
          text
        )}`}
      />
      {text && (
        <span className="absolute right-3 top-1/2 -translate-y-1/2 pointer-events-none">
          {text}
        </span>
      )}
    </div>
  );
};

export default InputBasic;
