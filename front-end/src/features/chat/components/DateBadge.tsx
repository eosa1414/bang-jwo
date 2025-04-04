interface DateBadgeProps {
  date: string; // 예: "2025-03-10"
}

const DateBadge = ({ date }: DateBadgeProps) => {
  const formatDate = (dateStr: string): string => {
    const dateObj = new Date(dateStr);
    const year = dateObj.getFullYear();
    const month = dateObj.getMonth() + 1;
    const day = dateObj.getDate();

    const weekdays = [
      "일요일",
      "월요일",
      "화요일",
      "수요일",
      "목요일",
      "금요일",
      "토요일",
    ];
    const weekday = weekdays[dateObj.getDay()];

    return `${year}년 ${month}월 ${day}일 ${weekday}`;
  };

  return (
    <div className="inline-block px-4 py-2 bg-neutral-dark200 opacity-50 rounded-full text-white font-medium text-xs">
      {formatDate(date)}
    </div>
  );
};

export default DateBadge;
