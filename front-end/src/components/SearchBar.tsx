import { useState } from "react";

const SearchBar = () => {
  const [searchQuery, setSearchQuery] = useState("");

  const search = () => {
    console.log("검색창 입력 내용:", searchQuery); //임시 console, 차후 기능 구현
  };

  const onChange = (e) => {
    setSearchQuery(e.target.value);
  };

  return (
    <div className="flex w-full max-w-sm border border-neutral-light100 rounded-full overflow-hidden p-[0.25rem_0.5rem_0.25rem_0.875rem]">
      <input
        type="text"
        value={searchQuery}
        onChange={onChange}
        placeholder="지역 명, 지하철 역을 입력해주세요"
        className="flex-grow border-none outline-none placeholder-neutral-gray placeholder:text-sm"
      />

      {/* 차후 IconButton으로의 변경 고려 */}
      <div
        className="flex rounded-full justify-center items-center text-neutral-black bg-transparent cursor-pointer"
        onClick={search}
      >
        <i class="material-symbols-rounded">search</i>
      </div>
    </div>
  );
};

export default SearchBar;
