// src/libs/socket.ts
import { Client, IMessage } from '@stomp/stompjs';

const socket = new Client({
  brokerURL: import.meta.env.VITE_SOCKET_URL, // 예: ws://localhost:8080/ws
  reconnectDelay: 5000,
  heartbeatIncoming: 4000,
  heartbeatOutgoing: 4000,
});

export default socket;
