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

// ChatRoom은 실시간 메시지와 API로 받아온 메시지를 모두 관리합니다.
interface ChatRoomProps {
  chatId: number | null;
}

const ChatRoom = ({
  chatId
}: ChatRoomProps) => {
  const scrollRef = useRef<HTMLDivElement | null>(null);
  const [showContractButton, setShowContractButton] = useState(false);
  const { chatRoom, selectedChatId } = useChatStore();

    // ④ 소켓 연결: ChatRoom 컴포넌트에서 채팅방 ID가 있을 때 소켓 연결하고 실시간 메시지를 반영
  // connectSocket은 [소켓메시지배열, sendMessage 함수]를 반환합니다.
  const [ messages, sendSocketMessage ] = connectSocket(chatId, scrollRef);

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

    Array.isArray(messages) && messages.forEach((msg, index) => {
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
          result.push(<SystemMessage key={`system-${index}`} role={msg.role} />);
        }
      } else {
        result.push(
          <ChatBubble
            text={msg.message}
            time={msg.sendAt.slice(11, 16)}
            key={index}
            {...msg}
            type={["sent", "system", "received"].includes(msg.type) ? (msg.type as "sent" | "system" | "received") : "received"}
          />
        );
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
      <MessageInputBox
        onSend={typeof sendSocketMessage === "function" ? sendSocketMessage : () => {}}
        onAttachClick={() => setShowContractButton((prev) => !prev)}
      />
      {showContractButton && (
        <ContractActionButton
          text="[임대인] 계약서 작성하기"
          onClick={() => {
            if (chatId === null) return;
            setShowContractButton(false);
          }}
        />
      )}
    </div>
  );
};

export default ChatRoom;




/////////////////////////////////


// import React, { useEffect, useRef, useState } from "react";
// import ChatHeader from "./ChatHeader";
// import ChatBubble from "./ChatBubble";
// import MessageInputBox from "./MessageInputBox";
// import DateBadge from "./DateBadge";
// import SystemMessage from "./SystemMessage";
// import ContractActionButton from "./ContractActionButton";
// import { getChatMessages } from "../../../services/chatService";
// import {
//   connectChatSocket,
//   disconnectChatSocket,
//   sendChatMessage,
// } from "../../../utils/chatSocket";
// import { ChatMessage } from "../../../types/chatTypes";
// import { useChatStore } from "../../../store/chatStore";

// export type MessageType = "sent" | "received" | "system";

// export interface Message {
//   id: number;
//   type: MessageType;
//   text: string;
//   time: string;
//   date: string;
//   senderName?: string;
//   avatarUrl?: string;
//   isReadByMe?: boolean;
//   isReadByPartner?: boolean;
//   role?: "임대인" | "임차인"; // system 메시지 전용
// }

// interface ChatRoomProps {
//   chatId: number | null;
//   messagesByChat: Record<number, Message[]>;
//   setMessagesByChat: React.Dispatch<
//     React.SetStateAction<Record<number, Message[]>>
//   >;
// }

// const ChatRoom = ({
//   chatId,
//   messagesByChat,
//   setMessagesByChat,
// }: ChatRoomProps) => {
//   const scrollRef = useRef<HTMLDivElement | null>(null);
//   const [showContractButton, setShowContractButton] = useState(false);
//   const { chatRoom } = useChatStore();

//   const messages = chatId !== null ? messagesByChat[chatId] || [] : [];

//   // ① 채팅방 입장 시 읽지 않은 받은 메시지를 읽음 처리
//   useEffect(() => {
//     if (chatId !== null) {
//       setMessagesByChat((prev) => ({
//         ...prev,
//         [chatId]:
//           prev[chatId]?.map((msg) =>
//             msg.type === "received" && !msg.isReadByMe
//               ? { ...msg, isReadByMe: true }
//               : msg
//           ) || [],
//       }));
//     }
//   }, [chatId, setMessagesByChat]);

//   // ② chatId가 변경될 때마다 해당 채팅방의 메시지 API 호출
//   useEffect(() => {
//     const fetchMessages = async () => {
//       console.log("채팅방 ID:", chatId);
//       if (chatId === null) return;

//       try {
//         // API 호출해서 해당 채팅방의 메시지를 받아옴
//         const res = await getChatMessages(chatId);
//         console.log(chatId + "번 방의 메시지:", res);
//         setMessagesByChat((prev) => ({
//           ...prev,
//           [chatId]: (res ?? []).map((msg: any) => ({
//             id: msg.chatId,
//             type: msg.senderId === 2 ? "sent" : "received", // 예시로 로그인된 사용자 id가 2인 경우
//             text: msg.message,
//             time: msg.sendAt.slice(11, 16),
//             date: msg.sendAt.slice(0, 10),
//             isReadByMe: true,
//           })),
//         }));
//       } catch (err) {
//         console.error("❌ 메시지 불러오기 실패:", err);
//       }
//     };

//     fetchMessages();
//   }, [chatId, setMessagesByChat]);

//   // ③ 새로운 메시지가 추가되면 스크롤을 최신 메시지 위치로 이동
//   useEffect(() => {
//     if (scrollRef.current) {
//       scrollRef.current.scrollIntoView({ behavior: "smooth" });
//     }
//   }, [messages]);

//   // ④ 메시지 전송 핸들러
//   const handleSendMessage = (text: string) => {
//     if (!chatId) return;

//     const now = new Date();
//     const sendAt = now.toISOString();

//     const message: ChatMessage = {
//       chatRoomId: chatId,
//       roomId: chatRoom?.chatRoomId ?? 0, // 실제 roomId를 사용하는 경우 수정
//       chatId: Date.now(), // 임시 채팅 고유 ID, 실제로는 백엔드에서 부여
//       receiverId: chatRoom?.otherId ?? 0, // 상대방 ID (프론트에서 알고 있다면)
//       senderId: 1, // 로그인 사용자 ID (예시: 2)
//       senderNickname: "나",
//       message: text,
//       sendAt: sendAt,
//       isReadByMe: true,
//       type: ""
//     };

//     sendChatMessage(message);
//   };

//   // ⑤ 채팅방 변경 시 웹소켓 연결 재설정 (실시간 메시지 수신)
//   useEffect(() => {
//     if (chatId === null) return;
//     const myId = 1; // 로그인 사용자 id (예시)

//     connectChatSocket(chatId, (msg) => {
//       console.log(`${chatId}번 방에서 메시지 수신:`, msg);
//       const time = msg.sendAt.slice(11, 16);
//       const date = msg.sendAt.slice(0, 10);

//       const newMessage: Message = {
//         id: Date.now(),
//         type: msg.senderId === myId ? "sent" : "received",
//         text: msg.message,
//         time,
//         date,
//         isReadByMe: msg.senderId !== myId,
//       };

//       setMessagesByChat((prev) => ({
//         ...prev,
//         [chatId]: [...(prev[chatId] || []), newMessage],
//       }));
//     });

//     return () => {
//       disconnectChatSocket();
//     };
//   }, [chatId, setMessagesByChat]);

//   if (chatId === null) {
//     return (
//       <div className="w-full h-full flex items-center justify-center text-neutral-gray text-lg">
//         문의하기를 선택해주세요
//       </div>
//     );
//   }

//   const chatTitle = chatRoom ? `월세 ${chatRoom.deposit}/${chatRoom.monthly}` : "월세";

//   // 메시지와 날짜 배지를 함께 렌더링하는 함수
//   const renderMessagesWithDateBadge = () => {
//     const result: React.ReactNode[] = [];
//     let lastDate: string | null = null;

//     messages.forEach((msg, index) => {
//       if (msg.date !== lastDate) {
//         result.push(
//           <div key={`date-${index}`} className="flex justify-center my-2">
//             <DateBadge date={msg.date} />
//           </div>
//         );
//         lastDate = msg.date;
//       }

//       if (msg.type === "system" && msg.role) {
//         result.push(<SystemMessage key={`system-${index}`} role={msg.role} />);
//       } else {
//         result.push(<ChatBubble key={index} {...msg} />);
//       }
//     });

//     return result;
//   };

//   return (
//     <div className="w-full h-full flex flex-col border border-neutral-light200 rounded-2xl shadow-xl overflow-hidden">
//       <ChatHeader title={chatTitle} onMenuClick={() => {}} />

//       <div className="flex-1 overflow-y-auto bg-white p-4 custom-scroll">
//         {renderMessagesWithDateBadge()}
//         <div ref={scrollRef} />
//       </div>

//       {/* 계약서 작성 버튼 영역 */}
//       {showContractButton && (
//         <ContractActionButton
//           text="[임대인] 계약서 작성하기"
//           onClick={() => {
//             if (chatId === null) return;

//             const now = new Date();
//             const time = now.toTimeString().slice(0, 5);
//             const date = now.toISOString().slice(0, 10);
//             const currentMessages = messagesByChat[chatId] || [];

//             const newSystemMessage: Message = {
//               id: currentMessages.length + 1,
//               type: "system",
//               text: "",
//               time,
//               date,
//               role: "임대인",
//             };

//             setMessagesByChat((prev) => ({
//               ...prev,
//               [chatId]: [...currentMessages, newSystemMessage],
//             }));

//             setShowContractButton(false);
//           }}
//         />
//       )}

//       <MessageInputBox
//         onSend={handleSendMessage}
//         onAttachClick={() => setShowContractButton((prev) => !prev)}
//       />
//     </div>
//   );
// };

// export default ChatRoom;
