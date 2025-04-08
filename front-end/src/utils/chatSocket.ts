import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import { ChatMessage } from "../types/chatTypes";

let stompClient: Client | null = null;

export const connectChatSocket = (
  chatRoomId: number,
  myId: number,
  onMessageReceived: (msg: ChatMessage) => void
) => {
  stompClient = new Client({
    webSocketFactory: () =>
      new SockJS(import.meta.env.VITE_SOCKET_URL + "/ws"),
    reconnectDelay: 5000, // 자동 재연결
    debug: (str) => console.log("📡", str),
  });

  // 연결 성공 시
  stompClient.onConnect = () => {
    console.log("✅ WebSocket 연결됨");

    // 채팅 메시지 구독
    stompClient?.subscribe(`/sub/chat/room/${chatRoomId}`, (message) => {
      const body: ChatMessage = JSON.parse(message.body);
      onMessageReceived(body);
    });

    // 알림 구독
    stompClient?.subscribe(`/sub/alert/${myId}`, (alert) => {
      console.log("🔔 알림 수신:", JSON.parse(alert.body));
    });
  };

  // 오류 발생 시
  stompClient.onStompError = (frame) => {
    console.error("❌ STOMP 오류:", frame.headers["message"]);
  };

  // 연결 시작
  stompClient.activate();
};

export const sendChatMessage = (dto: ChatMessage) => {
  if (!stompClient || !stompClient.connected) {
    console.warn("⚠️ WebSocket 연결되지 않음");
    return;
  }

  stompClient.publish({
    destination: "/pub/message",
    body: JSON.stringify(dto),
  });
};

export const disconnectChatSocket = () => {
  if (stompClient) {
    stompClient.deactivate();
    console.log("⛔ WebSocket 연결 해제");
  }
};
