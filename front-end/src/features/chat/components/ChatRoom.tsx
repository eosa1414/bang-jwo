import { useEffect, useRef, useState } from "react";
import ChatHeader from "./ChatHeader";
import ChatBubble from "./ChatBubble";
import MessageInputBox from "./MessageInputBox";
import DateBadge from "./DateBadge";
import ContractActionButton from "./ContractActionButton";

interface Message {
  id: number;
  type: "sent" | "received";
  text: string;
  time: string;
  senderName?: string;
  avatarUrl?: string;
  unreadCount?: number;
}

const ChatRoom = () => {
  const [messages, setMessages] = useState<Message[]>([
    {
      id: 1,
      type: "received",
      text: "계약서 작성하시겠어요?",
      time: "14:21",
      senderName: "망그러진쥐",
      avatarUrl: "/assets/images/chat_profile.png",
      unreadCount: 1,
    },
    {
      id: 2,
      type: "sent",
      text: "네 계약서 지금 바로 작성하시죠~",
      time: "14:22",
      unreadCount: 1,
    },
  ]);

  const [showContractButton, setShowContractButton] = useState(false);

  const scrollRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    if (scrollRef.current) {
      scrollRef.current.scrollIntoView({ behavior: "smooth" });
    }
  }, [messages]);

  const handleSendMessage = (text: string) => {
    const now = new Date();
    const time = now.toTimeString().slice(0, 5);

    const newMessage: Message = {
      id: messages.length + 1,
      type: "sent",
      text,
      time,
      unreadCount: 1,
    };

    setMessages((prev) => [...prev, newMessage]);
  };

  return (
    <div className="w-full h-full flex flex-col border border-neutral-light200 rounded-2xl shadow-xl overflow-hidden">
      <ChatHeader title="월세 50/500" onMenuClick={() => {}} />

      <div className="flex-1 overflow-y-auto bg-neutral-light300 p-4 custom-scroll">
        <div className="flex justify-center mb-4">
          <DateBadge date="2025년 3월 10일 월요일" />
        </div>

        {messages.map((msg) => (
          <ChatBubble key={msg.id} {...msg} />
        ))}

        <div ref={scrollRef} />
      </div>

      {showContractButton && (
        <div className="bg-neutral-light300 px-4 pt-1 pb-2">
          <ContractActionButton
            text="[임대인] 계약서 작성하기"
            onClick={() => setShowContractButton(false)}
          />
        </div>
      )}

      <MessageInputBox
        onSend={handleSendMessage}
        onAttachClick={() => setShowContractButton((prev) => !prev)}
      />
    </div>
  );
};

export default ChatRoom;
