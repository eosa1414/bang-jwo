interface DisabledInputBoxProps {
  value?: string;
  placeholder?: string;
}

const DisabledInputBox = ({
  value = "",
  placeholder,
}: DisabledInputBoxProps) => {
  return (
    <input
      type="text"
      value={value}
      disabled
      placeholder={placeholder}
      maxLength={10}
      className="w-[100px] px-2 py-1 text-sm font-medium border-2 rounded-sm bg-neutral-light200 text-neutral-dark100
                   border-neutral-light100 cursor-default"
    />
  );
};

export default DisabledInputBox;
