import { ChangeEvent } from "react";
import InputButton from "./InputButton";

interface InputCheckboxProps {
  checked: boolean;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  label: string;
}

const InputCheckbox = ({ checked, onChange, label }: InputCheckboxProps) => {
  return (
    <label className="flex items-center space-x-2 cursor-pointer">
      <input
        type="checkbox"
        checked={checked}
        onChange={onChange}
        className="hidden"
      />
      <InputButton label={label} checked={checked} />
    </label>
  );
};

export default InputCheckbox;
