import ReceivedBubble from "./ReceivedBubble";
import SentBubble from "./SentBubble";

interface ChatBubbleProps {
  type: "sent" | "received";
  text: string;
  time: string;
  unreadCount?: number;
  senderName?: string;
  avatarUrl?: string;
}

const ChatBubble = ({
  type,
  text,
  time,
  unreadCount,
  senderName,
  avatarUrl,
}: ChatBubbleProps) => {
  if (type === "received") {
    return (
      <ReceivedBubble
        text={text}
        time={time}
        unreadCount={unreadCount}
        senderName={senderName}
        avatarUrl={avatarUrl}
      />
    );
  }

  return <SentBubble text={text} time={time} unreadCount={unreadCount} />;
};

export default ChatBubble;
