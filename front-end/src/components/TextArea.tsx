import { ChangeEvent } from "react";

type TextareaProps = {
  value: string;
  name?: string;
  onChange: (e: ChangeEvent<HTMLTextAreaElement>) => void;
  placeholder?: string;
  rows?: number;
  className?: string;
  disabled?: boolean;
};

export default function Textarea({
  value,
  name,
  onChange,
  placeholder,
  rows = 5,
  className = "",
  disabled = false,
}: TextareaProps) {
  return (
    <textarea
      name={name}
      value={value}
      onChange={onChange}
      placeholder={placeholder}
      rows={rows}
      disabled={disabled}
      className={`
          w-full border border-neutral-light100 px-3 py-2 rounded-md resize-none
          focus:outline-none focus:ring-2 focus:ring-inset focus:ring-gold-light ${
            disabled
              ? "bg-neutral-light100 text-neutral-gray cursor-not-allowed"
              : "bg-real-white"
          }
          ${className}
        `}
    />
  );
}
