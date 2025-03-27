interface FilterChipProps {
  text: string;
  type: "default" | "selected" | "gray";
  onClick: () => void;
}

const FilterChip = ({ text, type, onClick }: FilterChipProps) => {
  return (
    <div
      onClick={onClick}
      className={`cursor-pointer p-[0.375rem_0.625rem] border-1 border-neutral-light100 rounded-sm min-w-[86px] text-center text-sm text-neutral-dark200 ${
        type === "selected" && "bg-gold-light/20"
      } ${type === "gray" && "bg-neutral-light100"}`}
    >
      {text}
    </div>
  );
};

export default FilterChip;
