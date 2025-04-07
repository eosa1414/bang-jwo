import { useEffect, useRef, useState } from "react";
import ChatHeader from "./ChatHeader";
import ChatBubble from "./ChatBubble";
import MessageInputBox from "./MessageInputBox";
import DateBadge from "./DateBadge";
import SystemMessage from "./SystemMessage";
import ContractActionButton from "./ContractActionButton";
import { getChatMessages } from "../../../services/chatService";
import {
  connectChatSocket,
  disconnectChatSocket,
  sendChatMessage,
} from "../../../utils/chatSocket"; // 경로에 맞게 조정
import { ChatMessage } from "../../../types/chatTypes";


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

  useEffect(() => {
    const fetchMessages = async () => {
      if (chatId === null) return;
  
      try {
        const res = await getChatMessages(chatId);
  
        setMessagesByChat((prev) => ({
          ...prev,
          [chatId]: (res ?? []).map((msg) => ({
            id: msg.chatId,
            type: msg.senderId === 2 ? "sent" : "received",
            // type: msg.senderId === myId ? "sent" : "received",
            text: msg.message,
            time: msg.sendAt.slice(11, 16),
            date: msg.sendAt.slice(0, 10),
            isReadByMe: true,
          })),
        }));
      } catch (err) {
        console.error("❌ 메시지 불러오기 실패:", err);
      }
    };
  
    fetchMessages();
  }, [chatId]);
  

  // ✅ 스크롤 아래로 이동
  useEffect(() => {
    if (scrollRef.current) {
      scrollRef.current.scrollIntoView({ behavior: "smooth" });
    }
  }, [messages]);

  const handleSendMessage = (text: string) => {
    if (!chatId) return;
  
    const now = new Date();
    const sendAt = now.toISOString();
  
    const message: ChatMessage = {
      chatRoomId: chatId,
      roomId: 0, // roomId 알 수 있으면 넣기
      chatId: 0, // 채팅 고유 ID는 백에서 처리
      receiverId: 999, // 상대방 ID (프론트에서 알고 있다면)
      senderId: 2, // 내 ID
      senderNickname: "나", // 내 닉네임
      message: text,
      sendAt: sendAt,
      read: false,
    };
  
    sendChatMessage(message);
  };
  
  
  // const handleSendMessage = (text: string) => {
  //   if (chatId === null) return;

  //   const now = new Date();
  //   const time = now.toTimeString().slice(0, 5);
  //   const date = now.toISOString().slice(0, 10);

  //   const newMessage: Message = {
  //     id: messages.length + 1,
  //     type: "sent",
  //     text,
  //     time,
  //     date,
  //     isReadByPartner: false,
  //   };

  //   setMessagesByChat((prev) => ({
  //     ...prev,
  //     [chatId]: [...(prev[chatId] || []), newMessage],
  //   }));
  // };

  useEffect(() => {
    if (!chatId) return;
  
    // 내 userId, 예: 로그인된 유저 ID (임시로 2 사용 중이라면 아래 수정)
    const myId = 2;
  
    // 연결
    connectChatSocket(chatId, myId, (msg) => {
      const time = msg.sendAt.slice(11, 16);
      const date = msg.sendAt.slice(0, 10);
  
      const newMessage: Message = {
        id: Date.now(), // 고유한 ID로 중복 방지
        type: msg.senderId === myId ? "sent" : "received",
        text: msg.message,
        time,
        date,
        isReadByMe: msg.senderId !== myId,
      };
  
      setMessagesByChat((prev) => ({
        ...prev,
        [chatId]: [...(prev[chatId] || []), newMessage],
      }));
    });
  
    return () => {
      disconnectChatSocket();
    };
  }, [chatId]);
  

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

    messages.forEach((msg, index) => {
      if (msg.date !== lastDate) {
        result.push(
          <div key={`date-${index}`} className="flex justify-center my-2">
            <DateBadge date={msg.date} />
          </div>
        );
        lastDate = msg.date;
      }

      if (msg.type === "system" && msg.role) {
        result.push(<SystemMessage key={`system-${index}`} role={msg.role} />);
      } else {
        result.push(<ChatBubble key={index} {...msg} />);
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
