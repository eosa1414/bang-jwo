import { useState } from "react";

const RentTypeSelector = () => {
  const [selected, setSelected] = useState<string | null>(null);

  const rentOptions = [
    { label: "보증금 있는 월세", value: "deposit-monthly" },
    { label: "월세", value: "monthly" },
  ];

  return (
    <div
      className={`border-3 p-3 w-[150px] bg-white rounded-sm
        ${selected === null ? "border-green" : "border-neutral-gray"}`}
    >
      <fieldset className="flex flex-col gap-3">
        {rentOptions.map((option) => (
          <label
            key={option.value}
            className="flex items-center gap-2 text-sm font-bold cursor-pointer"
          >
            <input
              type="radio"
              name="rentType"
              value={option.value}
              checked={selected === option.value}
              onChange={() => setSelected(option.value)}
              className="w-[16px] h-[16px] border-2 border-neutral-dark200 bg-white
                         appearance-none cursor-pointer rounded-none
                         checked:bg-neutral-dark200 transition-colors"
            />
            {option.label}
          </label>
        ))}
      </fieldset>
    </div>
  );
};

export default RentTypeSelector;
