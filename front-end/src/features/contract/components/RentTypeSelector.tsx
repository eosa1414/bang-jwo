import { useState } from "react";

interface RentTypeSelectorProps {
  mode: "lessor" | "lessee";
}

const RentTypeSelector = ({ mode }: RentTypeSelectorProps) => {
  const [selected, setSelected] = useState<string | null>(null);
  const isDisabled = mode === "lessee";

  const rentOptions = [
    { label: "보증금 있는 월세", value: "deposit-monthly" },
    { label: "월세", value: "monthly" },
  ];

  return (
    <div
      className={`border-3 p-3 w-[150px] rounded-sm
        ${
          isDisabled
            ? "bg-neutral-light200 border-neutral-light100"
            : "bg-white"
        }
        ${
          selected === null && !isDisabled
            ? "border-green"
            : "border-neutral-gray"
        }`}
    >
      <fieldset className="flex flex-col gap-3">
        {rentOptions.map((option) => (
          <label
            key={option.value}
            className={`flex items-center gap-2 text-sm font-bold
              ${
                isDisabled
                  ? "text-neutral-black cursor-not-allowed"
                  : "cursor-pointer"
              }`}
          >
            <input
              type="radio"
              name="rentType"
              value={option.value}
              checked={selected === option.value}
              onChange={() => {
                if (!isDisabled) {
                  setSelected(option.value);
                }
              }}
              disabled={isDisabled}
              className={`w-[16px] h-[16px] border-2 rounded-none appearance-none
                transition-colors
                ${
                  isDisabled
                    ? "bg-white border-neutral-dark100"
                    : "bg-white border-neutral-dark200 cursor-pointer"
                }
                ${
                  selected === option.value && !isDisabled
                    ? "checked:bg-neutral-dark200"
                    : ""
                }
              `}
            />
            {option.label}
          </label>
        ))}
      </fieldset>
    </div>
  );
};

export default RentTypeSelector;
