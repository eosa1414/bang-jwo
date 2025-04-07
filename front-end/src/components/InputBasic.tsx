import { ChangeEvent } from "react";

type InputBasicProps = {
  name?: string;
  value: string | number | undefined | null; //재사용성을 높이기 위하여 undefined와 null 허용
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  placeholder?: string;
  type?: string;
};

const InputBasic = ({
  name,
  value,
  onChange,
  placeholder,
  type = "text",
}: InputBasicProps) => {
  return (
    <input
      name={name}
      type={type}
      value={value ?? ""} //undefined와 null 처리
      onChange={onChange}
      placeholder={placeholder}
      className="border border-neutral-light100 px-3 py-2 rounded-md focus:outline-none focus:ring-2 focus:ring-inset focus:ring-gold-light"
    />
  );
};

export default InputBasic;
