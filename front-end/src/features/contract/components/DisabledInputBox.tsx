interface DisabledInputBoxProps {
  value?: string;
  placeholder?: string;
  customWidth?: string;
}

const DisabledInputBox = ({
  value = "",
  placeholder,
  customWidth = "w-[100px]",
}: DisabledInputBoxProps) => {
  return (
    <input
      type="text"
      value={value}
      disabled
      placeholder={placeholder}
      maxLength={100}
      className={`px-2 py-1 text-sm font-medium border-2 rounded-sm 
        bg-neutral-light200 text-neutral-dark100 border-neutral-light100 
        cursor-default ${customWidth}`}
    />
  );
};

export default DisabledInputBox;
