import { ChangeEvent } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

type InputDateProps = {
  name?: string;
  value: string | number | undefined | null;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  placeholder?: string;
  disabled?: boolean;
};

const InputDate = ({
  name,
  value,
  onChange,
  placeholder = "날짜 선택",
  disabled = false,
}: InputDateProps) => {
  return (
    <DatePicker
      selected={
        value && !isNaN(new Date(value).getTime()) ? new Date(value) : null
      }
      onChange={(date: Date | null) =>
        onChange({
          target: { name, value: date?.toISOString().split("T")[0] || "" },
        } as React.ChangeEvent<HTMLInputElement>)
      }
      dateFormat="yyyy-MM-dd"
      placeholderText={placeholder}
      disabled={disabled}
      customInput={
        <input
          name={name}
          value={value ?? ""}
          onChange={onChange}
          placeholder={placeholder}
          className="border border-neutral-light100 px-3 py-2 rounded-md focus:outline-none focus:ring-2 focus:ring-inset focus:ring-gold-light"
        />
      }
    />
  );
};

export default InputDate;
