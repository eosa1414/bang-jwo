interface InputButtonProps {
  checked?: boolean;
  label: string;
  onClick?: () => void;
}

const InputButton = ({ checked, label, onClick }: InputButtonProps) => {
  return (
    <span
      onClick={onClick}
      className={`cursor-pointer text-sm font-bold px-4 py-2 border-1 border-neutral-light100 rounded-md transition-all ${
        checked ? "bg-gold-light/30" : ""
      }`}
    >
      {label}
    </span>
  );
};

export default InputButton;
