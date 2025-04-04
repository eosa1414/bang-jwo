interface SentBubbleProps {
  text: string;
  time: string;
  unreadCount?: number;
}

const SentBubble = ({ text, time, unreadCount }: SentBubbleProps) => {
  return (
    <div className="flex justify-end items-end gap-2 mb-3">
      <div className="flex items-end gap-1 flex-row-reverse">
        {/* 말풍선 */}
        <div className="bg-forsythia px-4 py-2 rounded-2xl text-black text-base whitespace-pre-line">
          {text}
        </div>

        {/* 시간 & 읽음 수 */}
        <div className="flex flex-col text-xs text-neutral-gray leading-tight min-w-[24px] translate-y-1.5">
          {unreadCount !== undefined && unreadCount > 0 && (
            <span className="text-gold font-bold self-end">{unreadCount}</span>
          )}
          <span className="self-end">{time}</span>
        </div>
      </div>
    </div>
  );
};

export default SentBubble;
