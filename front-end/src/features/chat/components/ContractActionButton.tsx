interface Props {
  text: string;
  onClick: () => void;
}

const ContractActionButton = ({ text, onClick }: Props) => {
  return (
    <div className="mb-5 flex justify-start ml-4">
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
