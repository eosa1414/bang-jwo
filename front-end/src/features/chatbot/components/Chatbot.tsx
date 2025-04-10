import { useEffect, useRef } from "react";
import { useMutation } from "@tanstack/react-query";
import ChatbotHeader from "./ChatbotHeader";
import ChatbotBubble from "./ChatbotBubble";
import ChatbotInput from "./ChatbotInput";
import { fetchChatbot } from "../../../apis/ai";

// API 반환 메시지 인터페이스
interface APIMessage {
  sender: "user" | "chatbot";
  message: string;
  timestamp: string;
}

// API 전체 반환형
interface ChatbotResponse {
  data: APIMessage[];
}

// 내부적으로 관리할 메시지 상태 인터페이스
interface Message {
  id: number;
  sender: "user" | "chatbot";
  text: string;
  timestamp: string;
}

interface ChatbotProps {
  messages: Message[];
  setMessages: React.Dispatch<React.SetStateAction<Message[]>>;
}

const Chatbot = ({ messages, setMessages }: ChatbotProps) => {
  const scrollRef = useRef<HTMLDivElement>(null);

  // useMutation에 옵션 객체로 mutationFn을 설정합니다.
  const { mutate } = useMutation<ChatbotResponse, Error, { question: string }>({
    mutationFn: fetchChatbot,
    onSuccess: (response) => {
      // API 반환 배열에서 chatbot 메시지만 필터링합니다.
      const chatbotMsg = response.data.find((msg) => msg.sender === "chatbot");
      if (chatbotMsg) {
        const botMessage: Message = {
          id: messages.length + 1,
          sender: "chatbot",
          text: chatbotMsg.message,
          timestamp: new Date(chatbotMsg.timestamp).toTimeString().slice(0, 5),
        };
        setMessages((prevMessages) => [...prevMessages, botMessage]);
      }
    },
    onError: () => {
      const now = new Date();
      const errorMessage: Message = {
        id: Date.now(),
        sender: "chatbot",
        text: "오류가 발생했습니다. 다시 시도해주세요.",
        timestamp: now.toTimeString().slice(0, 5),
      };
      setMessages((prevMessages) => [...prevMessages, errorMessage]);
    },
  });

  const handleSendMessage = (text: string) => {
    const now = new Date();
    const userMessage: Message = {
      id: Date.now(),
      sender: "user",
      text,
      timestamp: now.toTimeString().slice(0, 5),
    };

    // 사용자 메시지를 상태에 추가
    setMessages((prevMessages) => [...prevMessages, userMessage]);

    console.log(text);

    // mutate로 API 호출 (API 응답에서 chatbot 메시지만 따로 처리)
    mutate({ question: text });
  };

  // 메시지 추가 시 스크롤을 맨 아래로 이동
  useEffect(() => {
    if (scrollRef.current) {
      scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
    }
  }, [messages]);

  return (
    <div className="max-w-[360px] h-[520px] flex flex-col border border-neutral-light200 rounded-lg overflow-hidden bg-neutral-light300">
      <ChatbotHeader />
      <div
        ref={scrollRef}
        className="flex-1 px-4 py-3 overflow-y-auto space-y-2"
      >
        {messages.map((msg) => (
          <ChatbotBubble
            key={msg.id}
            isUser={msg.sender === "user"}
            message={msg.text}
            time={msg.timestamp}
          />
        ))}
      </div>
      <ChatbotInput onSend={handleSendMessage} />
    </div>
  );
};

export default Chatbot;

///////////////

// import { useEffect, useRef, useState } from "react";
// import { useMutation } from "@tanstack/react-query";
// import ChatbotHeader from "./ChatbotHeader";
// import ChatbotBubble from "./ChatbotBubble";
// import ChatbotInput from "./ChatbotInput";
// import { fetchChatbot } from "../../../apis/ai";

// // API에서 반환되는 메시지 인터페이스
// interface APIMessage {
//   sender: "user" | "chatbot";
//   message: string;
//   timestamp: string;
// }

// // API 전체 반환 형식
// interface ChatbotResponse {
//   data: APIMessage[];
// }

// // 내부적으로 관리할 메시지 상태 인터페이스
// interface Message {
//   id: number;
//   sender: "user" | "chatbot";
//   text: string;
//   timestamp: string;
// }

// interface request {
//   question: string;
// }

// const Chatbot = () => {
//   const [messages, setMessages] = useState<Message[]>([
//     {
//       id: 1,
//       sender: "chatbot",
//       text: "무엇을 물어보시겠어요?",
//       timestamp: new Date().toTimeString().slice(0, 5),
//     },
//   ]);

//   // 스크롤 이동을 위한 ref
//   const scrollRef = useRef<HTMLDivElement>(null);

//   // useMutation에 제네릭 타입을 명시하여 fetchChatbot이 request 매개변수({question: string})와 ChatbotResponse를 주고 받도록 설정
//   const { mutate } = useMutation<ChatbotResponse, Error, { question: string }>(
//     fetchChatbot: (data: request)
//     {
//       onSuccess: (response: ChatbotResponse) => {
//         // API의 반환된 배열에서 chatbot의 메시지만 골라냅니다.
//         const chatbotMsg = response.data.find(
//           (msg) => msg.sender === "chatbot"
//         );
//         if (chatbotMsg) {
//           // 타임스탬프 포맷은 원래 API의 데이터를 사용할 수도 있고, 혹은 현재 시간으로 변경할 수도 있습니다.
//           const botMessage: Message = {
//             id: Date.now(), // 간단히 Date.now()로 식별자 설정
//             sender: "chatbot",
//             text: chatbotMsg.message,
//             // YYYY-MM-DDTHH:mm:ss.sssZ 형태의 timestamp를 HH:mm 포맷으로 변환
//             timestamp: new Date(chatbotMsg.timestamp).toTimeString().slice(0, 5),
//           };
//           setMessages((prevMessages) => [...prevMessages, botMessage]);
//         }
//       },
//       onError: () => {
//         const now = new Date();
//         const errorMessage: Message = {
//           id: Date.now(),
//           sender: "chatbot",
//           text: "오류가 발생했습니다. 다시 시도해주세요.",
//           timestamp: now.toTimeString().slice(0, 5),
//         };
//         setMessages((prevMessages) => [...prevMessages, errorMessage]);
//       },
//     }
//   );

//   // 사용자가 메시지를 보낼 때 호출됨
//   const handleSendMessage = (text: string) => {
//     const now = new Date();
//     const userMessage: Message = {
//       id: Date.now(),
//       sender: "user",
//       text,
//       timestamp: now.toTimeString().slice(0, 5),
//     };

//     // 사용자의 메시지를 추가합니다.
//     setMessages((prevMessages) => [...prevMessages, userMessage]);

//     // API 호출: 질문 전송. API의 반환값에는 user 메시지도 포함되어 있지만
//     // 여기서는 chatbot 메시지만 따로 추가할 것이므로 바로 mutate를 호출합니다.
//     mutate({ question: text });
//   };

//   // messages 상태 변경 시 스크롤 영역을 맨 아래로 이동
//   useEffect(() => {
//     if (scrollRef.current) {
//       scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
//     }
//   }, [messages]);

//   return (
//     <div className="max-w-[360px] h-[520px] flex flex-col border border-neutral-light200 rounded-lg overflow-hidden bg-neutral-light300">
//       <ChatbotHeader />
//       {/* 채팅 내용 영역: 스크롤 가능 */}
//       <div ref={scrollRef} className="flex-1 px-4 py-3 overflow-y-auto space-y-2">
//         {messages.map((msg) => (
//           <ChatbotBubble
//             key={msg.id}
//             isUser={msg.sender === "user"}
//             message={msg.text}
//             time={msg.timestamp}
//           />
//         ))}
//       </div>
//       <ChatbotInput onSend={handleSendMessage} />
//     </div>
//   );
// };

// export default Chatbot;

//////////////////

// import { useEffect, useRef, useState } from "react";
// import ChatbotHeader from "./ChatbotHeader";
// import ChatbotBubble from "./ChatbotBubble";
// import ChatbotInput from "./ChatbotInput";
// import { fetchChatbot } from "../../../apis/ai";

// interface Message {
//   id: number;
//   sender: "user" | "chatbot";
//   text: string;
//   timestamp: string;
// }

// const Chatbot = () => {
//   const [messages, setMessages] = useState<Message[]>([
//     {
//       id: 1,
//       sender: "chatbot",
//       text: "무엇을 물어보시겠어요?",
//       timestamp: new Date().toTimeString().slice(0, 5), // HH:mm 포맷
//     },
//   ]);

//   // 👇 스크롤 대상 ref
//   const scrollRef = useRef<HTMLDivElement>(null);

//   const handleSendMessage = (text: string) => {
//     const now = new Date();
//     const time = now.toTimeString().slice(0, 5); // HH:mm 포맷

//     const newUserMessage: Message = {
//       id: messages.length + 1,
//       sender: "user",
//       text,
//       timestamp: time,
//     };

//     const newBotMessage: Message = {
//       id: messages.length + 2,
//       sender: "chatbot",
//       text: "이 특약 조건은...",
//       timestamp: time,
//     };

//     setMessages([...messages, newUserMessage, newBotMessage]);
//   };

//   // 👇 메시지 변경될 때마다 스크롤 맨 아래로 이동
//   useEffect(() => {
//     if (scrollRef.current) {
//       scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
//     }
//   }, [messages]);

//   return (
//     <div className="max-w-[360px] h-[520px] flex flex-col border border-neutral-light200 rounded-lg overflow-hidden bg-neutral-light300">
//       <ChatbotHeader />

//       {/* 채팅 내용 영역: 스크롤 가능 + ref 연결 */}
//       <div
//         ref={scrollRef}
//         className="flex-1 px-4 py-3 overflow-y-auto space-y-2"
//       >
//         {messages.map((msg) => (
//           <ChatbotBubble
//             key={msg.id}
//             isUser={msg.sender === "user"}
//             message={msg.text}
//             time={msg.timestamp}
//           />
//         ))}
//       </div>

//       <ChatbotInput onSend={
//         handleSendMessage
//         } />
//     </div>
//   );
// };

// export default Chatbot;
