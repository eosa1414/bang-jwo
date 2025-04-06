interface ReceivedBubbleProps {
  text: string;
  time: string;
  unreadCount?: number;
  senderName?: string;
  avatarUrl?: string;
}

const ReceivedBubble = ({
  text,
  time,
  unreadCount,
  senderName,
  avatarUrl,
}: ReceivedBubbleProps) => {
  return (
    <div className="flex items-start gap-2 mb-3">
      {/* 프로필 이미지 */}
      {avatarUrl && (
        <img
          src={avatarUrl}
          alt="avatar"
          className="w-12 h-12 rounded-full object-cover"
        />
      )}

      <div className="flex flex-col max-w-[70%] items-start">
        {senderName && (
          <span className="text-sm text-black font-semibold mb-1 ml-1">
            {senderName}
          </span>
        )}

        <div className="flex items-end gap-1">
          {/* 말풍선 */}
          <div className="bg-white border border-neutral-light200 px-4 py-2 rounded-2xl text-neutral-black text-base whitespace-pre-line">
            {text}
          </div>

          {/* 시간 & 읽음 수 */}
          <div className="flex flex-col text-xs text-neutral-dark100 leading-tight min-w-[24px]">
            {unreadCount !== undefined && unreadCount > 0 && (
              <span className="text-gold font-bold self-start">
                {unreadCount}
              </span>
            )}
            <span className="self-end">{time}</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ReceivedBubble;
