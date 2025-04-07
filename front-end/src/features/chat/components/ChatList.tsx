import ChatListHeader from "./ChatListHeader";
import ChatListItem from "./ChatListItem";
import { useChatStore } from "../../../store/chatStore.ts";
import { useChatRooms } from "../../../hooks/useChatRooms";
import { ChatRoomSummary, Message } from "../../../types/chatTypes";


const ChatList = () => {
  const { selectedChatId, setSelectedChatId, messagesByChat } = useChatStore();
  const { data: chatRooms, isLoading, isError } = useChatRooms();

  if (isLoading) return <div>로딩 중...</div>;
  if (isError || !chatRooms) return <div>채팅방 목록 로딩 실패</div>;

  return (
    <div className="flex flex-col h-full">
      <ChatListHeader />
      <div className="flex-1 overflow-y-auto px-4 custom-scroll">
        {chatRooms.map((room) => {
          const messages: Message[] = messagesByChat[room.chatRoomId] || [];

          const filteredMessages = messages.filter((m) => m.type !== "system");
          const lastMessage = filteredMessages.at(-1);

          const unreadCount = filteredMessages.filter(
            (m) => m.type === "received" && !m.isReadByMe
          ).length;

          return (
            <ChatListItem
              key={room.chatRoomId+Date.now()}
              id={room.chatRoomId}
              title={room.nickname}
              price={`월세 ${room.deposit}/${room.monthly}`}
              avatarUrl={room.profileImage}
              time={lastMessage?.time ?? ""}
              message={lastMessage?.text ?? room.lastMessage}
              unreadCount={unreadCount || room.unreadCount}
              isSelected={room.chatRoomId === selectedChatId}
              roomImage={room.roomImage}
              onClick={() => setSelectedChatId(room.chatRoomId)}
            />
          );
        })}
      </div>
    </div>
  );
};

export default ChatList;

// interface ChatListProps {
//   selectedChatId: number | null;
//   onSelectChat: (id: number) => void;
//   messagesByChat: Record<number, Message[]>;
// }

// const meta = {
//   1: {
//     title: "망그러진쥐",
//     price: "500/50",
//     avatarUrl: "/assets/images/chat_profile.png",
//   },
//   2: {
//     title: "감자바보",
//     price: "1000/80",
//     avatarUrl: "/assets/images/chat_profile.png",
//   },
//   3: {
//     title: "부동산초보",
//     price: "300/30",
//     avatarUrl: "/assets/images/chat_profile.png",
//   },
//   4: {
//     title: "곰돌이푸",
//     price: "200/25",
//     avatarUrl: "/assets/images/chat_profile.png",
//   },
//   5: {
//     title: "피카츄",
//     price: "800/70",
//     avatarUrl: "/assets/images/chat_profile.png",
//   },
//   6: {
//     title: "라이언",
//     price: "600/60",
//     avatarUrl: "/assets/images/chat_profile.png",
//   },
//   7: {
//     title: "냥이짱",
//     price: "700/65",
//     avatarUrl: "/assets/images/chat_profile.png",
//   },
//   8: {
//     title: "뽀로로",
//     price: "450/45",
//     avatarUrl: "/assets/images/chat_profile.png",
//   },
//   9: {
//     title: "코난",
//     price: "550/55",
//     avatarUrl: "/assets/images/chat_profile.png",
//   },
//   10: {
//     title: "짱구",
//     price: "350/35",
//     avatarUrl: "/assets/images/chat_profile.png",
//   },
// };

// const ChatList = ({
//   selectedChatId,
//   onSelectChat,
//   messagesByChat,
// }: ChatListProps) => {
//   return (
//     <div className="flex flex-col h-full">
//       <ChatListHeader />
//       <div className="flex-1 overflow-y-auto px-4 custom-scroll">
//         {Object.entries(meta).map(([idStr, info]) => {
//           const id = Number(idStr);
//           const messages = messagesByChat[id] || [];

//           // ✅ system 메시지를 제외한 메시지 리스트
//           const filteredMessages = messages.filter((m) => m.type !== "system");

//           const lastMessage =
//             filteredMessages.length > 0
//               ? filteredMessages[filteredMessages.length - 1]
//               : null;

//           const unreadCount = filteredMessages.filter(
//             (m) => m.type === "received" && !m.isReadByMe
//           ).length;

//           return (
//             <ChatListItem
//               key={id}
//               id={id}
//               title={info.title}
//               price={info.price}
//               avatarUrl={info.avatarUrl}
//               time={lastMessage ? lastMessage.time : ""}
//               message={lastMessage ? lastMessage.text : ""}
//               unreadCount={unreadCount}
//               isSelected={id === selectedChatId}
//               onClick={() => onSelectChat(id)}
//             />
//           );
//         })}
//       </div>
//     </div>
//   );
// };

// export default ChatList;
