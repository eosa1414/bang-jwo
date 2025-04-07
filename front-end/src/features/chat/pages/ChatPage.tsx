import { useEffect, useState } from "react";
import ChatList from "../components/ChatList";
import ChatRoom from "../components/ChatRoom";
import { Message } from "../../../types/chatTypes";

const chatPartners: Record<number, string> = {
  1: "망그러진쥐",
  2: "감자바보",
  3: "부동산초보",
  4: "곰돌이푸",
  5: "피카츄",
  6: "라이언",
  7: "냥이짱",
  8: "뽀로로",
  9: "코난",
  10: "짱구",
};

const ChatPage: React.FC = () => {
  const [selectedChatId, setSelectedChatId] = useState<number | null>(null);
  const [messagesByChat, setMessagesByChat] = useState<
    Record<number, Message[]>
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

  // ✅ 초기 메시지 세팅
  useEffect(() => {
    const now = new Date();
    const date = now.toISOString().slice(0, 10);
    const time = now.toTimeString().slice(0, 5);

    const initialMessages: Record<number, Message[]> = {};
    for (let id = 1; id <= 10; id++) {
      initialMessages[id] = [
        {
          id: 1,
          type: "received",
          text: "계약서 작성하시겠어요?",
          time,
          date,
          senderName: chatPartners[id],
          avatarUrl: "/assets/images/chat_profile.png",
          isReadByMe: false,
        },
        {
          id: 2,
          type: "sent",
          text: "네 기다리겠습니다!",
          time,
          date,
          isReadByPartner: false,
        },
      ];
    }

    setMessagesByChat(initialMessages);
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
              ? new Date(`${last.date}T${last.time}`).getTime()
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
            messagesByChat={messagesByChat}
            setMessagesByChat={setMessagesByChat}
          />
        )}
      </div>
    </div>
  );
};

export default ChatPage;
