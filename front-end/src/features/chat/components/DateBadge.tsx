interface DateBadgeProps {
  date: string; // 예: "2025-03-26T00:52:49"
}

const DateBadge = ({ date }: DateBadgeProps) => {
  const formatDate = (dateStr: string): string => {
    const parsedDate = new Date(dateStr);

    if (isNaN(parsedDate.getTime())) {
      return "유효하지 않은 날짜";
    }

    const year = parsedDate.getFullYear();
    const month = parsedDate.getMonth() + 1;
    const day = parsedDate.getDate();

    const weekdays = [
      "일요일",
      "월요일",
      "화요일",
      "수요일",
      "목요일",
      "금요일",
      "토요일",
    ];
    const weekday = weekdays[parsedDate.getDay()];

    return `${year}년 ${month}월 ${day}일 ${weekday}`;
  };

  return (
    <div className="inline-block px-4 py-2 bg-neutral-dark200 opacity-50 rounded-full text-white font-medium text-xs">
      {formatDate(date)}
    </div>
  );
};

export default DateBadge;
