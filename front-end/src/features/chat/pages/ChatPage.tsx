import { useEffect, useState } from "react";
import ChatList from "../components/ChatList";
import ChatRoom from "../components/ChatRoom";
import { ChatMessage } from "../../../types/chatTypes";
import { useChatStore } from "../../../store/chatStore";

const ChatPage: React.FC = () => {
  const { selectedChatId, setSelectedChatId } = useChatStore();
  const [messagesByChat, setMessagesByChat] = useState<
    Record<number, ChatMessage[]>
  >({});
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);

  // ✅ 창 너비 감지 (resize event)
  useEffect(() => {
    const handleResize = () => {
      setWindowWidth(window.innerWidth);
    };
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  // ✅ 가장 최근 대화 선택
  useEffect(() => {
    if (!selectedChatId || !(selectedChatId in messagesByChat)) {
      const chatsWithLastTime = Object.entries(messagesByChat)
        .map(([id, messages]) => {
          const last = messages[messages.length - 1];
          return {
            id: Number(id),
            timestamp: last
              ? new Date(last.sendAt).getTime()
              : 0,
          };
        })
        .filter((chat) => chat.timestamp > 0)
        .sort((a, b) => b.timestamp - a.timestamp);

      if (chatsWithLastTime.length > 0) {
        setSelectedChatId(chatsWithLastTime[0].id);
      }
    }
  }, [messagesByChat]);

  const isCompactView = windowWidth < 800;

  return (
    <div className="flex h-screen bg-white px-4 py-6 gap-4">
      {!isCompactView && (
        <div className="w-[320px] bg-white rounded-xl shadow border border-neutral-light200 overflow-hidden">
          <ChatList/>
        </div>
      )}

      <div className="flex-1">
        {selectedChatId !== null && (
          <ChatRoom
            chatId={selectedChatId}
          />
        )}
      </div>
    </div>
  );
};

export default ChatPage;
