interface Props {
  text: string;
  onClick: () => void;
}

const ContractActionButton = ({ text, onClick }: Props) => {
  return (
    <div className="animate-slide-up mb-2 flex justify-start">
      <button
        onClick={onClick}
        className="bg-white px-4 py-2 rounded-2xl text-black text-sm font-medium border border-neutral-light100 cursor-pointer"
      >
        {text}
      </button>
    </div>
  );
};

export default ContractActionButton;
