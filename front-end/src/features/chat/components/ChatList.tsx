import ChatListHeader from "./ChatListHeader";
import ChatListItem from "./ChatListItem";

const ChatList = () => {
  const mockChats = [
    {
      id: 1,
      title: "망그러진쥐",
      message: "계약서 작성하시겠어요?",
      time: "14:21",
      unreadCount: 1,
      price: "5000/130",
      avatarUrl: "/assets/images/chat_profile.png",
    },
  ];

  return (
    <div className="flex flex-col h-full">
      <ChatListHeader />
      <div className="flex-1 overflow-y-auto px-4">
        {mockChats.map((chat) => (
          <ChatListItem key={chat.id} {...chat} />
        ))}
      </div>
    </div>
  );
};

export default ChatList;
