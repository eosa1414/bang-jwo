// ChatRoom.tsx
import React, { useEffect, useRef, useState } from "react";
import ChatHeader from "./ChatHeader";
import ChatBubble from "./ChatBubble";
import MessageInputBox from "./MessageInputBox";
import DateBadge from "./DateBadge";
import SystemMessage from "./SystemMessage";
import ContractActionButton from "./ContractActionButton";
import { connectSocket } from "../../../utils/chatSocket";
import { useChatStore } from "../../../store/chatStore";
import { useAuth } from "../../../contexts/AuthContext";

// ChatRoom은 실시간 메시지와 API로 받아온 메시지를 모두 관리합니다.
interface ChatRoomProps {
  chatId: number | null;
}

const ChatRoom = ({ chatId }: ChatRoomProps) => {
  const scrollRef = useRef<HTMLDivElement | null>(null);
  const [showContractButton, setShowContractButton] = useState(false);
  const { chatRoom } = useChatStore();
  const { user } = useAuth();

  const myId = Number(user?.sub);

  // ④ 소켓 연결: ChatRoom 컴포넌트에서 채팅방 ID가 있을 때 소켓 연결하고 실시간 메시지를 반영
  // connectSocket은 [소켓메시지배열, sendMessage 함수]를 반환합니다.
  const [messages, sendSocketMessage] = connectSocket(chatId, scrollRef);

  // ③ 새로운 메시지가 추가되면 스크롤을 최신 메시지 위치로 이동
  useEffect(() => {
    if (scrollRef.current) {
      scrollRef.current.scrollIntoView({ behavior: "smooth" });
    }
  }, [messages]);

  if (chatId === null || chatId === 0) {
    return (
      <div className="w-full h-full flex items-center justify-center text-neutral-gray text-lg">
        문의하기를 선택해주세요
      </div>
    );
  }

  const chatTitle = chatRoom
    ? `월세 ${chatRoom.deposit}/${chatRoom.monthly}`
    : "월세";

  // 메시지와 날짜 배지를 함께 렌더링하는 함수
  const renderMessagesWithDateBadge = () => {
    const result: React.ReactNode[] = [];
    let lastDate: string | null = null;

    Array.isArray(messages) &&
      messages.forEach((msg, index) => {
        console.log(myId, msg.senderId);
        if (msg.sendAt.slice(0, 10) !== lastDate) {
          result.push(
            <div key={`date-${index}`} className="flex justify-center my-2">
              <DateBadge date={msg.sendAt.slice(0, 10)} />
            </div>
          );
          lastDate = msg.sendAt.slice(0, 10);
        }
        if (msg.type === "system" && "role" in msg) {
          if (msg.role === "임대인" || msg.role === "임차인") {
            result.push(
              <SystemMessage key={`system-${index}`} role={msg.role} />
            );
          }
        } else {
          result.push(
            <ChatBubble
              text={msg.message}
              time={msg.sendAt.slice(11, 16)}
              key={index}
              {...msg}
              // type={
              //   ["sent", "system", "received"].includes(msg.type)
              //     ? (msg.type as "sent" | "system" | "received")
              //     : "received"
              // }
              type={myId == msg.senderId ? "sent" : "received"}
            />
          );
        }
      });

    return result;
  };

  return (
    <div className="w-full h-full flex flex-col border border-neutral-light200 rounded-2xl shadow-xl overflow-hidden">
      <ChatHeader
        title={chatTitle}
        onMenuClick={() => {
          // TODO: 메뉴 클릭 시 처리 로직 추가
          console.log("redirect to room find page");
          // redirect()
        }}
      />
      <div className="flex-1 overflow-y-auto bg-white p-4 custom-scroll">
        {renderMessagesWithDateBadge()}
        <div ref={scrollRef} />
      </div>
      <div className="flex">
        {showContractButton && (
          <ContractActionButton
            text="[임대인] 계약서 작성하기"
            onClick={() => {
              if (chatId === null) return;
              window.open(`/seller-contract`, "_blank", "noopener,noreferrer");
              setShowContractButton(false);
            }}
          />
        )}
        {showContractButton && (
          <ContractActionButton
            text="[임차인] 계약서 작성하기"
            onClick={() => {
              if (chatId === null) return;
              window.open(`/buyer-contract`, "_blank", "noopener,noreferrer");
              setShowContractButton(false);
            }}
          />
        )}
      </div>
      <MessageInputBox
        onSend={
          typeof sendSocketMessage === "function" ? sendSocketMessage : () => {}
        }
        onAttachClick={() => setShowContractButton((prev) => !prev)}
      />
    </div>
  );
};

export default ChatRoom;
