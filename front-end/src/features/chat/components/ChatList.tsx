// src/components/chat/ChatList.tsx
import ChatListHeader from "./ChatListHeader";
import ChatListItem from "./ChatListItem";
import { useChatStore } from "../../../store/chatStore";
import { useChatRooms } from "../../../hooks/useChatRooms";
import { useQueryClient } from "@tanstack/react-query";

const ChatList = () => {
  const { selectedChatId, setSelectedChatId, setChatRoom } = useChatStore();
  const { data: chatRooms, isLoading, isError } = useChatRooms();
  const queryClient = useQueryClient();

  if (isLoading) return <div>로딩 중...</div>;
  if (isError || !chatRooms) return <div>채팅방 목록 로딩 실패</div>;

  return (
    <div className="flex flex-col h-full">
      <ChatListHeader />
      <div className="flex-1 overflow-y-auto px-4 custom-scroll">
        {chatRooms.map((room) => {
          return (
            <ChatListItem
              key={room.chatRoomId} // 고유한 키 사용
              id={room.chatRoomId}
              title={room.nickname}
              price={`월세 ${room.deposit}/${room.monthly}`}
              avatarUrl={room.profileImage}
              time={(room?.sendAt).slice(11, 16)}
              message={room.lastMessage}
              unreadCount={room.unreadCount}
              isSelected={room.chatRoomId === selectedChatId}
              roomImage={room.roomImage}
              onClick={() => {
                setSelectedChatId(room.chatRoomId);
                setChatRoom(room);
                queryClient.invalidateQueries({ queryKey: ["chatRooms"] });
              }}
            />
          );
        })}
      </div>
    </div>
  );
};

export default ChatList;

// // src/components/chat/ChatList.tsx
// import ChatListHeader from "./ChatListHeader";
// import ChatListItem from "./ChatListItem";
// import { useChatStore } from "../../../store/chatStore";
// import { useChatRooms } from "../../../hooks/useChatRooms";
// import { Message } from "../../../types/chatTypes";

// const ChatList = () => {
//   const { selectedChatId, setSelectedChatId, messagesByChat, setChatRoom } = useChatStore();
//   const { data: chatRooms, isLoading, isError } = useChatRooms();

//   if (isLoading) return <div>로딩 중...</div>;
//   if (isError || !chatRooms) return <div>채팅방 목록 로딩 실패</div>;

//   return (
//     <div className="flex flex-col h-full">
//       <ChatListHeader />
//       <div className="flex-1 overflow-y-auto px-4 custom-scroll">
//         {chatRooms.map((room) => {
//           const messages: Message[] = messagesByChat[room.chatRoomId] || [];
//           const filteredMessages = messages.filter((m) => m.type !== "system");
//           const lastMessage = filteredMessages.at(-1);

//           const unreadCount = filteredMessages.filter(
//             (m) => m.type === "received" && !m.isReadByMe
//           ).length;

//           return (
//             <ChatListItem
//               key={room.chatRoomId} // 고유한 키 사용
//               id={room.chatRoomId}
//               title={room.nickname}
//               price={`월세 ${room.deposit}/${room.monthly}`}
//               avatarUrl={room.profileImage}
//               time={lastMessage?.time ?? ""}
//               message={lastMessage?.text ?? room.lastMessage}
//               unreadCount={unreadCount || room.unreadCount}
//               isSelected={room.chatRoomId === selectedChatId}
//               roomImage={room.roomImage}
//               onClick={() => {
//                 setSelectedChatId(room.chatRoomId)
//                 setChatRoom(room);
//               }
//               }
//             />
//           );
//         })}
//       </div>
//     </div>
//   );
// };

// export default ChatList;
