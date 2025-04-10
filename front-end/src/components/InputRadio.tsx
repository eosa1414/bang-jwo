import { ChangeEvent } from "react";
import InputButton from "./InputButton";

interface InputRadioProps {
  checked: boolean;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  label: string;
  value: string;
  name: string;
}

const InputRadio = ({
  checked,
  onChange,
  label,
  value,
  name,
}: InputRadioProps) => {
  return (
    <label className="flex items-center space-x-2 cursor-pointer">
      <input
        type="radio"
        name={name}
        value={value}
        checked={checked}
        onChange={onChange}
        className="hidden"
      />
      <InputButton label={label} checked={checked} />
    </label>
  );
};

export default InputRadio;
