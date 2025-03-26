import { useState } from "react";

interface SliderProps {
  title?: string;
  min?: number;
  max: number;
  minStep?: number;
}

const getStep = (value: number, minStep: number = 10000) => {
  let step: number;

  if (value <= 100000) {
    step = 10000;
  } else if (value <= 3000000) {
    step = 100000;
  } else if (value <= 10000000) {
    step = 1000000;
  } else if (value <= 100000000) {
    step = 5000000;
  } else {
    step = 10000000;
  }
  return Math.max(step, minStep);
};

const formatValue = (value: number) => {
  if (value === 0) return "0원";
  if (value < 10000) return `${value}원`;

  const billions = Math.floor(value / 100000000);
  const millions = Math.floor((value % 100000000) / 10000);

  if (billions > 0 && millions > 0) {
    return `${billions}억 ${millions}만원`;
  }
  if (billions > 0) {
    return `${billions}억`;
  }
  return `${millions}만원`;
};

const Slider = ({ min = 0, max, title = "", minStep }: SliderProps) => {
  const [value, setValue] = useState((min + max) / 2);
  const step = getStep(value, minStep);

  const handleTextChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    let newValue = parseInt(e.target.value.replace(/[^0-9]/g, ""), 10);
    if (isNaN(newValue)) newValue = min;
    newValue = Math.min(Math.max(newValue * 10000, min), max); //범위 제한
    setValue(newValue);
  };

  const handleSliderChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newValue = Number(e.target.value);
    setValue(newValue);
  };

  return (
    <div className="w-full">
      <div className="w-full flex items-center gap-2 text-sm">
        {title}
        <input
          type="text"
          value={Math.floor(value / 10000)} // 만원 단위로 표시
          onChange={handleTextChange}
          className="border-1 border-neutral-light100 w-20 rounded-sm p-[0.25rem_0.625rem]"
        />
        만원
        <span className="text-neutral-dark100"> = {formatValue(value)}</span>
      </div>
      <input
        type="range"
        min={min}
        max={max}
        step={step}
        value={value}
        onChange={handleSliderChange}
        className="w-full accent-gold"
      />
    </div>
  );
};

export default Slider;
