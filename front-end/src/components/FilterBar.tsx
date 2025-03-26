interface FilterBarProps {
  selectedFilters?: string[];
  onClick: () => void;
}

const FilterBar = ({ selectedFilters, onClick }: FilterBarProps) => {
  return (
    <div className="flex w-full text-sm cursor-pointer" onClick={onClick}>
      <div className="flex items-center flex-grow border-l-1 border-t-1 border-b-1 border-neutral-light100 rounded-l-full p-[0.3125rem_0.625rem] text-neutral-dark200">
        {selectedFilters?.length > 0
          ? selectedFilters?.join(", ")
          : "원하는 방의 조건을 선택해보세요"}
      </div>
      <div className="flex justify-center items-center rounded-r-full bg-neutral-dark200 text-neutral-white p-[0_0.625rem_0_0.5rem]">
        <i className="material-symbols-rounded text-base">tune</i>
        필터
      </div>
    </div>
  );
};

export default FilterBar;
