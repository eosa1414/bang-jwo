import Stomp from "stompjs";
import { useEffect, useState } from "react";
import { ChatMessage, RequestMessage } from "../types/chatTypes";
import { fetchChatMessages } from "../apis/chat";
import { useChatStore } from "../store/chatStore";
import { useQueryClient } from "@tanstack/react-query";


const SOCKET_URL = `${import.meta.env.VITE_API_BASE_URL}/chat`;

export const connectSocket = (id: number | null, scrollRef: any) => {
  const [stompClient, setStompClient] = useState<Stomp.Client | null>(null);
  const [messages, setMessages] = useState<ChatMessage[]>([]);
  const { chatRoom } = useChatStore();
  const queryClient = useQueryClient();
  
  const sendMessage = (message: string) => {
    const now = new Date();
    const timezoneOffset = now.getTimezoneOffset() * 60000; // 분 -> 밀리초 변환
    const localISOTime = new Date(now.getTime() - timezoneOffset).toISOString();
    console.log("sendMessage", message);
    if (stompClient) {
      stompClient.send(
        `/pub/message`,
        {},
        JSON.stringify({
          type: "sent",
          chatRoomId: id,
          roomId: chatRoom?.roomId, // Replace with actual roomId if available
          receiverId: chatRoom?.otherId, // Replace with actual receiverId if available
          senderId: 1, // Replace with actual senderId
          senderNickname: "asdfasdf", // Replace with actual nickname if available
          message: message,
          sendAt: localISOTime,
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
    const socket = new WebSocket(SOCKET_URL);
    const stomp = Stomp.over(socket);
    console.log("stomp", stomp);

    const headers = {
      userId: "1",
      roomId: (id ?? 0).toString(),
    };

    console.log("headers", headers, "socket", socket);

    socket.onerror = (error) => {
      console.error('Socket error:', error);
    };
    
    socket.onclose = (event) => {
      console.log('Socket closed:', event.code, event.reason);
    };

    stomp.debug = (msg) => console.log("📡", msg);

    stomp.connect(headers, () => {
      console.log("Connected to STOMP server");

      const subscription = stomp.subscribe(`/sub/chat/room/${id}`, (message) => {
        console.log(message);
        setMessages((prev) => [...prev, JSON.parse(message.body)]);
        queryClient.invalidateQueries({ queryKey: ["chatRooms"] });
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
