import { useEffect, useRef, useState } from "react";
import ChatHeader from "./ChatHeader";
import ChatBubble from "./ChatBubble";
import MessageInputBox from "./MessageInputBox";
import DateBadge from "./DateBadge";
import SystemMessage from "./SystemMessage";
import ContractActionButton from "./ContractActionButton";

type MessageType = "sent" | "received" | "system";

interface Message {
  id: number;
  type: MessageType;
  text: string;
  time: string;
  date: string;
  senderName?: string;
  avatarUrl?: string;
  isReadByMe?: boolean;
  isReadByPartner?: boolean;
  role?: "임대인" | "임차인"; // system 메시지 전용
}

interface ChatRoomProps {
  chatId: number | null;
  messagesByChat: Record<number, Message[]>;
  setMessagesByChat: React.Dispatch<
    React.SetStateAction<Record<number, Message[]>>
  >;
}

const chatMeta: Record<number, { deposit: string; monthly: string }> = {
  1: { deposit: "500", monthly: "50" },
  2: { deposit: "1000", monthly: "80" },
  3: { deposit: "300", monthly: "30" },
  4: { deposit: "200", monthly: "25" },
  5: { deposit: "800", monthly: "70" },
  6: { deposit: "600", monthly: "60" },
  7: { deposit: "700", monthly: "65" },
  8: { deposit: "450", monthly: "45" },
  9: { deposit: "550", monthly: "55" },
  10: { deposit: "350", monthly: "35" },
};

const ChatRoom = ({
  chatId,
  messagesByChat,
  setMessagesByChat,
}: ChatRoomProps) => {
  const scrollRef = useRef<HTMLDivElement | null>(null);
  const [showContractButton, setShowContractButton] = useState(false); // ✅ 버튼 표시 여부

  const messages = chatId !== null ? messagesByChat[chatId] || [] : [];

  // ✅ 받은 메시지 읽음 처리
  useEffect(() => {
    if (chatId !== null) {
      setMessagesByChat((prev) => ({
        ...prev,
        [chatId]:
          prev[chatId]?.map((msg) =>
            msg.type === "received" && !msg.isReadByMe
              ? { ...msg, isReadByMe: true }
              : msg
          ) || [],
      }));
    }
  }, [chatId, setMessagesByChat]);

  // ✅ 스크롤 아래로 이동
  useEffect(() => {
    if (scrollRef.current) {
      scrollRef.current.scrollIntoView({ behavior: "smooth" });
    }
  }, [messages]);

  const handleSendMessage = (text: string) => {
    if (chatId === null) return;

    const now = new Date();
    const time = now.toTimeString().slice(0, 5);
    const date = now.toISOString().slice(0, 10);

    const newMessage: Message = {
      id: messages.length + 1,
      type: "sent",
      text,
      time,
      date,
      isReadByPartner: false,
    };

    setMessagesByChat((prev) => ({
      ...prev,
      [chatId]: [...(prev[chatId] || []), newMessage],
    }));
  };

  if (chatId === null) {
    return (
      <div className="w-full h-full flex items-center justify-center text-neutral-gray text-lg">
        문의하기를 선택해주세요
      </div>
    );
  }

  const meta = chatMeta[chatId];
  const chatTitle = meta ? `월세 ${meta.deposit}/${meta.monthly}` : "월세";

  const renderMessagesWithDateBadge = () => {
    const result: React.ReactNode[] = [];
    let lastDate: string | null = null;

    messages.forEach((msg) => {
      if (msg.date !== lastDate) {
        result.push(
          <div key={`date-${msg.date}`} className="flex justify-center my-2">
            <DateBadge date={msg.date} />
          </div>
        );
        lastDate = msg.date;
      }

      if (msg.type === "system" && msg.role) {
        result.push(<SystemMessage key={`system-${msg.id}`} role={msg.role} />);
      } else {
        result.push(<ChatBubble key={msg.id} {...msg} />);
      }
    });

    return result;
  };

  return (
    <div className="w-full h-full flex flex-col border border-neutral-light200 rounded-2xl shadow-xl overflow-hidden">
      <ChatHeader title={chatTitle} onMenuClick={() => {}} />

      <div className="flex-1 overflow-y-auto bg-white p-4 custom-scroll">
        {renderMessagesWithDateBadge()}
        <div ref={scrollRef} />
      </div>

      {/* ✅ 계약서 작성 버튼 표시 영역 */}
      {showContractButton && (
        <ContractActionButton
          text="[임대인] 계약서 작성하기"
          onClick={() => {
            if (chatId === null) return;

            const now = new Date();
            const time = now.toTimeString().slice(0, 5);
            const date = now.toISOString().slice(0, 10);
            const currentMessages = messagesByChat[chatId] || [];

            const newSystemMessage: Message = {
              id: currentMessages.length + 1,
              type: "system",
              text: "",
              time,
              date,
              role: "임대인", // ✅ 여기에서 임차인으로 바꾸면 임차인용 메시지도 가능
            };

            setMessagesByChat((prev) => ({
              ...prev,
              [chatId]: [...currentMessages, newSystemMessage],
            }));

            setShowContractButton(false); // 버튼 숨기기
          }}
        />
      )}

      <MessageInputBox
        onSend={handleSendMessage}
        onAttachClick={() => setShowContractButton((prev) => !prev)}
      />
    </div>
  );
};

export default ChatRoom;
