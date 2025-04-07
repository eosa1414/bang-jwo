// apis/chat.ts
import axiosInstance from "../utils/axiosInstances";
import {
  CreateChatRoomRequest,
  CreateChatRoomResponse,
  ChatRoomSummary,
  ChatMessagesResponse,
  ChatMessage,
} from "../types/chatTypes";

// 채팅방 생성
export const fetchCreateChatRoom = async (data: CreateChatRoomRequest) => {
  const res = await axiosInstance.post<CreateChatRoomResponse>("/api/v1/chat/new", data);
  return res.data;
};

// 채팅방 나가기
export const fetchLeaveChatRoom = async (chatRoomId: number) => {
  const res = await axiosInstance.post(`/api/v1/chat/leave/${chatRoomId}`);
  return res.data;
};

// 채팅방 목록 조회
export const fetchChatRooms = async () => {
  const res = await axiosInstance.get<ChatRoomSummary[]>("/api/v1/chat/list");
  console.log("🔥 실제 응답 데이터:", res.data);
  return res.data;
};

// 채팅방 입장 (채팅 메시지 조회)
export const fetchChatMessages = async (chatRoomId: number) => {
  const res = await axiosInstance.get<ChatMessage[]>(`/api/v1/chat/enter/${chatRoomId}`);
  return res.data;
};
