import { useState } from "react";
import ChatbotIcon from "../components/ChatbotIcon";
import ChatbotNotice from "../components/ChatbotNotice";

const ChatbotNoticePage = () => {
  const [showNotice, setShowNotice] = useState(true); // 초기엔 보이게

  const toggleNotice = () => {
    setShowNotice((prev) => !prev);
  };

  return (
    <div className="relative pb-10">
      {/* 안내사항이 있을 때만 표시 */}
      {showNotice && <ChatbotNotice />}

      {/* 아이콘 버튼 */}
      <div className="absolute -bottom-6 left-4">
        <button onClick={toggleNotice}>
          <ChatbotIcon />
        </button>
      </div>
    </div>
  );
};

export default ChatbotNoticePage;
