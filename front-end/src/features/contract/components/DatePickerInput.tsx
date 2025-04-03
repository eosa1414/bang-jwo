import DatePicker from "react-datepicker";
import { ko } from "date-fns/locale";

interface DatePickerInputProps {
  selectedDate: Date | null;
  onChange: (date: Date | null) => void;
  placeholder?: string;
}

const DatePickerInput = ({
  selectedDate,
  onChange,
  placeholder = "날짜 선택",
}: DatePickerInputProps) => {
  return (
    <DatePicker
      selected={selectedDate}
      onChange={onChange}
      dateFormat="yyyy.MM.dd"
      locale={ko}
      placeholderText={placeholder}
      className="px-2 py-1 text-sm font-medium border-3 rounded-sm outline-none transition-colors duration-150 border-green w-[140px] bg-white cursor-pointer"
    />
  );
};

export default DatePickerInput;
