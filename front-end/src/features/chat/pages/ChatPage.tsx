import ChatList from "../components/ChatList";
import ChatRoom from "../components/ChatRoom";

const ChatPage: React.FC = () => {
  return (
    <div className="flex h-screen bg-neutral-light100 px-4 py-6 gap-4">
      <div className="w-[320px] bg-white rounded-xl shadow border border-neutral-light200 overflow-hidden">
        <ChatList />
      </div>

      <div className="flex-1">
        <ChatRoom />
      </div>
    </div>
  );
};

export default ChatPage;
