import ReceivedBubble from "./ReceivedBubble";
import SentBubble from "./SentBubble";

interface ChatBubbleProps {
  type: "sent" | "received" | "system"; // ✅ system 추가
  text: string;
  time: string;
  senderName?: string;
  avatarUrl?: string;
  isReadByPartner?: boolean; // ✅ 내가 보낸 메시지 읽음 여부
}

const ChatBubble = ({
  type,
  text,
  time,
  senderName,
  avatarUrl,
  isReadByPartner,
}: ChatBubbleProps) => {
  // ✅ system 타입이면 아무것도 렌더링하지 않음
  if (type === "system") {
    return null;
  }

  if (type === "received") {
    return (
      <ReceivedBubble
        text={text}
        time={time}
        senderName={senderName}
        avatarUrl={avatarUrl}
      />
    );
  }

  return (
    <SentBubble text={text} time={time} isReadByPartner={isReadByPartner} />
  );
};

export default ChatBubble;
