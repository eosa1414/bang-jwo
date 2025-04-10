import { useEffect, useRef, useState } from "react";
import ChatbotHeader from "./ChatbotHeader";
import ChatbotBubble from "./ChatbotBubble";
import ChatbotInput from "./ChatbotInput";

interface Message {
  id: number;
  sender: "user" | "bot";
  text: string;
  time: string;
}

const Chatbot = () => {
  const [messages, setMessages] = useState<Message[]>([
    {
      id: 1,
      sender: "bot",
      text: "무엇을 물어보시겠어요?",
      time: "14:21",
    },
  ]);

  // 👇 스크롤 대상 ref
  const scrollRef = useRef<HTMLDivElement>(null);

  const handleSendMessage = (text: string) => {
    const now = new Date();
    const time = now.toTimeString().slice(0, 5); // HH:mm 포맷

    const newUserMessage: Message = {
      id: messages.length + 1,
      sender: "user",
      text,
      time,
    };

    const newBotMessage: Message = {
      id: messages.length + 2,
      sender: "bot",
      text: "이 특약 조건은...",
      time,
    };

    setMessages([...messages, newUserMessage, newBotMessage]);
  };

  // 👇 메시지 변경될 때마다 스크롤 맨 아래로 이동
  useEffect(() => {
    if (scrollRef.current) {
      scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
    }
  }, [messages]);

  return (
    <div className="max-w-[360px] h-[520px] flex flex-col border border-neutral-light200 rounded-lg overflow-hidden bg-neutral-light300">
      <ChatbotHeader />

      {/* 채팅 내용 영역: 스크롤 가능 + ref 연결 */}
      <div
        ref={scrollRef}
        className="flex-1 px-4 py-3 overflow-y-auto space-y-2"
      >
        {messages.map((msg) => (
          <ChatbotBubble
            key={msg.id}
            isUser={msg.sender === "user"}
            message={msg.text}
            time={msg.time}
          />
        ))}
      </div>

      <ChatbotInput onSend={handleSendMessage} />
    </div>
  );
};

export default Chatbot;
