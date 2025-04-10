import { useState } from "react";
import Chatbot from "../components/Chatbot";
import ChatbotIcon from "../components/ChatbotIcon";

interface Message {
  id: number;
  sender: "user" | "chatbot";
  text: string;
  timestamp: string;
}

const ChatbotPage = () => {
  const [showChatbot, setShowChatbot] = useState(true);
  const [messages, setMessages] = useState<Message[]>([
    {
      id: 1,
      sender: "chatbot",
      text: "무엇을 물어보시겠어요?",
      timestamp: new Date().toTimeString().slice(0, 5),
    },
  ]);

  const toggleChatbot = () => {
    setShowChatbot((prev) => !prev);
  };

  return (
    <div className="relative pb-10">
      {/* 챗봇이 열려 있을 때만 표시 */}
      {showChatbot && <Chatbot messages={messages} setMessages={setMessages} />}

      {/* 아이콘 버튼 */}
      <div className="absolute -bottom-6 left-4">
        <button onClick={toggleChatbot}>
          <ChatbotIcon />
        </button>
      </div>
    </div>
  );
};

export default ChatbotPage;
