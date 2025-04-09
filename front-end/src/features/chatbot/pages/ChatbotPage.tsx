import { useState } from "react";
import Chatbot from "../components/Chatbot";
import ChatbotIcon from "../components/ChatbotIcon";

const ChatbotPage = () => {
  const [showChatbot, setShowChatbot] = useState(true);

  const toggleChatbot = () => {
    setShowChatbot((prev) => !prev);
  };

  return (
    <div className="relative pb-10">
      {/* 챗봇이 열려 있을 때만 표시 */}
      {showChatbot && <Chatbot />}

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
