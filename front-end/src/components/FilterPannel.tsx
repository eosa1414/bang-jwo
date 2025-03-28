import { ReactNode, useState } from "react";
import FilterBar from "./FilterBar";
import FilterChip from "./FilterChip";
import Slider from "./Slider";

interface FilterPannelProps {
  childrenTop?: ReactNode;
  childrenBottom?: ReactNode;
}

const FilterPannel = ({ childrenTop, childrenBottom }: FilterPannelProps) => {
  const [isFilterOpen, setIsFilterOpen] = useState(false);
  const [selectedFilters, setSelectedFilters] = useState<string[]>([]);

  const filterOptions = [
    "10평 미만",
    "10평대",
    "20평대",
    "30평대",
    "40평대",
    "50평대",
  ];

  const toggleFilter = () => {
    setIsFilterOpen((prev) => !prev);
  };

  const closeFilter = () => {
    setIsFilterOpen(false);
  };

  const handleSelectedFilters = (filter: string) => {
    setSelectedFilters((prev) => {
      if (filter === "전체") {
        return prev.length === filterOptions.length ? [] : filterOptions;
      }

      if (prev.includes(filter)) {
        return prev.filter((item) => item !== filter); //이미 있다면 제외
      } else {
        return [...prev, filter]; //추가
      }
    });
  };

  const isAllSelected = selectedFilters.length === filterOptions.length;

  return (
    <div className="filter-wrapper w-full relative overflow-hi">
      <div className="relative z-2 bg-real-white flex flex-col gap-2 w-full p-[0.625rem_0.75rem] border-b-1 border-neutral-light100">
        {childrenTop && <>{childrenTop}</>}
        <FilterBar
          selectedFilters={isAllSelected ? ["전체"] : selectedFilters}
          onClick={toggleFilter}
        />
        {childrenBottom && <>{childrenBottom}</>}
      </div>
      {/* 열리고 닫히는 패널 */}
      {isFilterOpen && (
        <div className="scroll-custom absolute top-full left-0 bg-real-white z-1 overflow-auto max-h-[min(calc(100vh-55px-7.9rem),24rem)] border-b-1 border-neutral-light100 shadow-custom">
          <div className="flex w-full justify-between border-b-1 border-neutral-light100 p-[0.75rem]">
            <span>필터</span>
            <i
              className="material-symbols-rounded cursor-pointer"
              onClick={closeFilter}
            >
              close
            </i>
          </div>
          <div className="w-full p-[0.5rem_0.75rem] flex flex-col gap-2">
            <p className="font-medium">면적</p>
            <div className="w-full flex flex-wrap gap-1">
              {["전체", ...filterOptions].map((filter) => (
                <FilterChip
                  key={filter}
                  text={
                    filter === "전체"
                      ? isAllSelected
                        ? "전체 해제"
                        : "전체"
                      : filter
                  }
                  type={
                    filter === "전체"
                      ? isAllSelected
                        ? "gray"
                        : "default"
                      : selectedFilters.includes(filter)
                      ? "selected"
                      : "default"
                  }
                  onClick={() => handleSelectedFilters(filter)}
                />
              ))}
            </div>
          </div>
          <div className="w-full p-[0.5rem_0.75rem] flex flex-col gap-2">
            <p className="font-medium">가격</p>
            <Slider title="보증금" minStep={1000000} max={300000000} />
            <Slider title="월세" max={2000000} />
          </div>
        </div>
      )}
    </div>
  );
};

export default FilterPannel;
