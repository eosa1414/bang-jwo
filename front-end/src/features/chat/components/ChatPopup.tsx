import { useEffect } from "react";
import ChatPage from "../pages/ChatPage";

const ChatPopup = ({ onClose }: { onClose: () => void }) => {
  useEffect(() => {
    document.body.style.overflow = "hidden";
    return () => {
      document.body.style.overflow = "auto";
    };
  }, []);

  return (
    <div className="fixed inset-0 bg-black/30 z-50 flex items-center justify-center">
      <div className="absolute inset-0" onClick={onClose} />

      <div className="relative w-[1000px] h-[700px] bg-white rounded-2xl shadow-xl z-10 overflow-hidden">
        <ChatPage />
        <button
          onClick={onClose}
          className="absolute top-4 right-4 text-neutral-dark200 hover:text-black"
        >
          ✕
        </button>
      </div>
    </div>
  );
};

export default ChatPopup;
