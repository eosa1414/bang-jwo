interface ChatbotBubbleProps {
  isUser: boolean;
  message: string;
  time: string;
}

const ChatbotBubble = ({ isUser, message, time }: ChatbotBubbleProps) => {
  return (
    <div className={`flex ${isUser ? "justify-end" : "justify-start"} mb-2`}>
      <div
        className={`
            max-w-[70%] 
            px-3 py-2 
            rounded-lg 
            text-sm 
            whitespace-pre-wrap 
            ${
              isUser
                ? "bg-[#e4f0e1] text-neutral-black"
                : "bg-white text-neutral-black"
            }
          `}
      >
        {message}
        <div className="text-xs text-neutral-dark100 text-right mt-1">
          {time}
        </div>
      </div>
    </div>
  );
};

export default ChatbotBubble;
