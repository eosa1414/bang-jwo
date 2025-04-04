interface Props {
  title: string;
  message: string;
  time: string;
  unreadCount: number;
  price: string;
  avatarUrl: string;
}

const ChatListItem = ({
  title,
  message,
  time,
  unreadCount,
  price,
  avatarUrl,
}: Props) => {
  return (
    <div className="flex items-center gap-3 px-2 py-3 border-b border-neutral-light200 cursor-pointer hover:bg-neutral-light100">
      <img
        src="/assets/images/house.png"
        alt="house"
        className="w-12 h-12 rounded bg-neutral-light200 object-cover"
      />
      <div className="flex-1">
        <div className="text-xs text-neutral-dark100">
          월세 <strong>{price}</strong>
        </div>
        <div className="flex items-center gap-2 mt-1">
          <img src={avatarUrl} alt="avatar" className="w-6 h-6 rounded-full" />
          <span className="text-sm font-bold">{title}</span>
        </div>
        <div className="text-sm mt-0.5 text-black">{message}</div>
      </div>
      <div className="flex flex-col items-end justify-between h-full gap-1 text-xs text-neutral-dark100">
        <span>{time}</span>
        {unreadCount > 0 && (
          <span className="bg-gold text-white text-xs font-bold rounded-full w-4 h-4 flex items-center justify-center">
            {unreadCount}
          </span>
        )}
      </div>
    </div>
  );
};

export default ChatListItem;
