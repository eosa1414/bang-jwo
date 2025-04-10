interface ChatHeaderProps {
  title: string;
  onMenuClick: () => void;
}

const ChatHeader = ({ title, onMenuClick }: ChatHeaderProps) => {
  return (
    <div className="flex items-center justify-between px-4 py-3 bg-white border-b border-neutral-light100 cursor-pointer">
      <div className="text-base font-bold text-neutral-black">{title}</div>
      <button onClick={onMenuClick}></button>
    </div>
  );
};

export default ChatHeader;
