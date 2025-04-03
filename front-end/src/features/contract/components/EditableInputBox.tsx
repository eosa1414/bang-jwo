interface EditableInputBoxProps {
  value: string;
  onChange: (value: string) => void;
  placeholder?: string;
  minLength?: number;
  maxLength?: number;
  customWidth?: string;
}

const EditableInputBox = ({
  value,
  onChange,
  placeholder,
  maxLength = 10,
  customWidth = "w-[100px]",
}: EditableInputBoxProps) => {
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const inputValue = e.target.value;
    if (!maxLength || inputValue.length <= maxLength) {
      onChange(inputValue);
    }
  };

  return (
    <input
      type="text"
      value={value}
      onChange={handleChange}
      placeholder={placeholder}
      maxLength={maxLength}
      className={`px-2 py-1 text-sm font-medium border-3 rounded-sm outline-none transition-colors duration-150
        ${value ? "border-neutral-gray" : "border-green"} 
        ${customWidth} cursor-pointer bg-white`}
    />
  );
};

export default EditableInputBox;
