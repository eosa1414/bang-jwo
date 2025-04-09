// import Stomp from "stompjs";
// import { useEffect, useState, useRef } from "react";
// import { ChatMessage, ChatRoomSummary, RequestMessage } from "../types/chatTypes";
// import { fetchChatMessages } from "../apis/chat";
// import SockJS from "sockjs-client";

// const SOCKET_URL = `http://localhost:8080/chat`;

// // 커스텀 훅 형태로 변경
// export const connectSocket = (chatRoomId: number | null, scrollRef: any) => {
//   const [stompClient, setStompClient] = useState<Stomp.Client | null>(null);
//   const [messages, setMessages] = useState<ChatMessage[]>([]);
//   const [isConnected, setIsConnected] = useState(false);
  
//   // 연결 상태 추적을 위한 ref
//   const stompRef = useRef<Stomp.Client | null>(null);

//   useEffect(() => {
//     // 채팅방 ID가 유효할 때만 메시지 조회
//     const fetchMessages = async () => {
//       if (chatRoomId && chatRoomId > 0) {
//         try {
//           const fetchedMessages = await fetchChatMessages(chatRoomId);
//           setMessages(fetchedMessages);
//         } catch (error) {
//           console.error("Failed to fetch messages:", error);
//         }
//       }
//     };
    
//     fetchMessages();
//   }, [chatRoomId]);

//   useEffect(() => {
//     // chatRoomId가 없으면 연결하지 않음
//     if (!chatRoomId) return;

//     console.log(`Connecting to chat room ${chatRoomId}...`);
    
//     // 이미 연결된 경우 새 연결 생성 안함
//     if (stompRef.current && stompRef.current.connected) {
//       console.log("Already connected, skipping connection");
//       return;
//     }
    
//     // 소켓 연결 생성
//     const socket = new SockJS(SOCKET_URL);
//     const stomp = Stomp.over(socket);
//     stompRef.current = stomp;

//     const headers = {
//       userId: "1",
//       roomId: chatRoomId.toString(),
//     };

//     // 디버깅 모드 설정 (필요시 사용)
//     // stomp.debug = (msg) => console.log("📡", msg);
//     // 디버깅 비활성화
//     stomp.debug = () => {};

//     try {
//       stomp.connect(
//         headers,
//         () => {
//           console.log(`Connected to STOMP server for room ${chatRoomId}`);
//           setIsConnected(true);
//           setStompClient(stomp);
          
//           // 채팅방 구독
//           stomp.subscribe(
//             `/sub/chat/room/${chatRoomId}`,
//             (message) => {
//               console.log("Received message:", message);
//               try {
//                 const parsedMessage = JSON.parse(message.body);
//                 setMessages((prev) => [...prev, parsedMessage]);
//               } catch (e) {
//                 console.error("Error parsing message:", e);
//               }
//             },
//             { id: `sub-${chatRoomId}` }
//           );
//         },
//         (error) => {
//           // 에러 처리
//           console.error("STOMP connection error:", error);
//           setIsConnected(false);
//           stompRef.current = null;
//         }
//       );
//     } catch (e) {
//       console.error("Error establishing connection:", e);
//       setIsConnected(false);
//     }

//     // cleanup: 컴포넌트가 unmount될 때 또는 chatRoomId가 변경될 때
//     return () => {
//       console.log(`Cleaning up connection for room ${chatRoomId}`);
//       if (stomp && stomp.connected) {
//         try {
//           stomp.unsubscribe(`sub-${chatRoomId}`);
//           stomp.disconnect(() => {
//             console.log(`Disconnected from room ${chatRoomId}`);
//             setIsConnected(false);
//           });
//         } catch (e) {
//           console.error("Error during disconnect:", e);
//         }
//       }
      
//       if (socket.readyState !== SockJS.CLOSED) {
//         socket.close();
//       }
//       stompRef.current = null;
//     };
//   }, [chatRoomId]); // chatRoom 객체 전체가 아닌 chatRoomId만 의존성으로 설정

//   // 메시지가 변경될 때 스크롤 처리
//   useEffect(() => {
//     if (scrollRef?.current) {
//       scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
//     }
//   }, [messages, scrollRef]);

//   // 메시지 전송 함수
//   const sendMessage = (message: string) => {
//     if (!stompClient || !stompClient.connected || !chatRoomId) {
//       console.error("Cannot send message: Not connected or no chat room selected");
//       return;
//     }
    
//     try {
//       const messageData: RequestMessage = {
//         type: "sent",
//         chatRoomId: chatRoomId,
//         roomId: 1, // 실제 roomId로 대체
//         receiverId: 2, // 실제 receiverId로 대체
//         senderId: 1, // 실제 senderId로 대체
//         senderNickname: "asdf", // 실제 nickname으로 대체
//         message: message,
//         sendAt: new Date().toISOString(),
//         isReadByMe: false,
//       };
      
//       stompClient.send(
//         `/pub/message`,
//         {},
//         JSON.stringify(messageData)
//       );
//       console.log("Message sent:", messageData);
//     } catch (e) {
//       console.error("Error sending message:", e);
//     }
//   };

//   return { messages, sendMessage, isConnected };
// };



///////////////////



import Stomp from "stompjs";
import { useEffect, useState } from "react";
import { ChatMessage, ChatRoomSummary, RequestMessage } from "../types/chatTypes";
import { fetchChatMessages } from "../apis/chat";
import SockJS from "sockjs-client";

const SOCKET_URL = `http://localhost:8080/chat`;

export const connectSocket = (id: number | null, scrollRef: any) => {
  const [stompClient, setStompClient] = useState<Stomp.Client | null>(null);
  const [messages, setMessages] = useState<ChatMessage[]>([]);

  const sendMessage = (message: string) => {
    console.log("sendMessage", message);
    if (stompClient) {
      stompClient.send(
        `/pub/message`,
        {},
        JSON.stringify({
          type: "sent",
          chatRoomId: id,
          roomId: 1, // Replace with actual roomId if available
          receiverId: 2, // Replace with actual receiverId if available
          senderId: 1, // Replace with actual senderId
          senderNickname: "asdf", // Replace with actual nickname if available
          message: message,
          sendAt: new Date().toISOString(),
          isReadByMe: false,
        } as RequestMessage)
      );
    }
  };

  useEffect(() => {
    const fetchMessages = async () => {
      if (id ?? 0 !== null) {
        setMessages(await fetchChatMessages(id ?? 0));
      }
    };
    fetchMessages();
  }, [id]);

  useEffect(() => {
    const socket = new SockJS(SOCKET_URL);
    const stomp = Stomp.over(socket);
    console.log("stomp", stomp);

    const headers = {
      userId: "1",
      roomId: (id ?? 0).toString(),
    };

    console.log("headers", headers, "socket", socket);

    stomp.debug = (msg) => console.log("📡", msg);

    stomp.connect(headers, () => {
      console.log("Connected to STOMP server");

      const subscription = stomp.subscribe(`/sub/chat/room/${id}`, (message) => {
        console.log(message);
        setMessages((prev) => [...prev, JSON.parse(message.body)]);
      });

      setStompClient(stomp);

      return () => {
        subscription.unsubscribe();
        stomp.disconnect(() => {
          console.log("Disconnected from STOMP server");
        });
      };
    });
  return () => {
    socket.close();
  };
  }, [id]);

  useEffect(() => {
    if (scrollRef?.current) {
      scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
    }
  }, [messages, scrollRef]);

  return [ messages, sendMessage ];
};



/////////////////////////////////////////



// // src/utils/chatSocket.ts
// import Stomp from "stompjs";
// import { ChatMessage } from "../types/chatTypes";
// import { useEffect, useState } from 'react'

// // 환경변수를 통해 WebSocket URL을 지정 (적절히 변경하세요)
// const SOCKET_URL = `${
//   import.meta.env.VITE_BASE_URL
// }/chat`;


// export const connectSocket = (id: number, onMessageReceived: (msg: ChatMessage) => void) => {
//   const [stompClient, setStompClient] = useState<Stomp.Client | null>(null)
//   const [messages, setMessages] = useState<ChatMessage[]>([])
//   const sendMessage = (message: ChatMessage) => {
//     if (stompClient) {
//       stompClient.send(
//         `/pub/chat/message/${id}`,
//         {},
//         JSON.stringify(message),
//       )
//     }
//   }

//   useEffect(() => {
//     const socket = new WebSocket(SOCKET_URL) // Use native WebSocket instead of SockJS
//     const stomp = Stomp.over(socket)

//     stomp.connect({}, () => {
//       console.log('Connected to STOMP server')

//       // Subscribe to chat room messages
//       const subscription = stomp.subscribe(`/sub/chat/room/${id}`, message => {
//         console.log(message)
//         setMessages(prev => [...prev, JSON.parse(message.body)])
//         const body: ChatMessage = JSON.parse(message.body);
//         onMessageReceived(body);
//       })

//       setStompClient(stomp)

//       return () => {
//         subscription.unsubscribe() // Unsubscribe on cleanup
//         stomp.disconnect(() => {
//           console.log('Disconnected from STOMP server');
//         })
//       }
//     })

//     return () => {
//       // stomp.disconnect()
//       socket.close()
//     }
//   }, [id])

//   return [messages, sendMessage]
// }



///////////////////////////////////////////////



// // src/utils/chatSocket.ts
// import Stomp from "stompjs";
// import { ChatMessage } from "../types/chatTypes";

// // 환경변수를 통해 WebSocket URL을 지정 (적절히 변경하세요)
// const SOCKET_URL = `${
//   import.meta.env.VITE_BASE_URL
// }/chat`;

// let stompClient: Stomp.Client | null = null;

// export const connectChatSocket = (
//   chatRoomId: number,
//   onMessageReceived: (msg: ChatMessage) => void
// ) => {
//   console.log("소켓 연결 시도:", SOCKET_URL);
//   // SockJS 인스턴스를 생성합니다.
//   const socket = new WebSocket(SOCKET_URL);
//   console.log("소켓 연결:", socket);
//   // stompjs를 통해 STOMP 클라이언트를 생성합니다.
//   const stomp = Stomp.over(socket);
//   console.log("stompjs 생성:", stomp);
  
//   // 디버그 로그 활성화 (필요시 비활성화 가능)
//   stomp.debug = (msg) => console.log("📡", msg);

//   // 헤더 객체 그대로 생성 (문자열로 변환)
//   const headers = {
//     userId: "1",            // 문자열 형태로 전송
//     roomId: chatRoomId.toString()
//   };
  
//   console.log("헤더:", headers, "소켓 연결 시도:", SOCKET_URL);
  
//   // 연결 성공 시 처리 – 헤더 객체를 바로 전달합니다.
//   stomp.connect(
//     // headers,
//     () => {
//       console.log("✅ STOMP 연결 성공");
//       // 채팅방 구독 (채팅방 ID를 경로에 포함)
//       stomp.subscribe(`/sub/chat/room/${chatRoomId}`, (message) => {
//         const body: ChatMessage = JSON.parse(message.body);
//         onMessageReceived(body);
//       });
//       stompClient = stomp;
//     },
//     (error) => {
//       console.error("❌ STOMP 연결 오류:", error);
//     }
//   );
// };

// export const sendChatMessage = (dto: ChatMessage) => {
//   if (!stompClient || !stompClient.connected) {
//     console.warn("⚠️ WebSocket 연결되지 않음");
//     return;
//   }
//   // 지정한 발행 경로로 메시지 전송
//   stompClient.send(
//     "/pub/message",
//     {},
//     JSON.stringify(dto)
//   );
// };

// export const disconnectChatSocket = () => {
//   if (stompClient) {
//     stompClient.disconnect(() => {
//       console.log("⛔ STOMP 연결 해제됨");
//     });
//     stompClient = null;
//   }
// };
