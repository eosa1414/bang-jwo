// services/chatService.ts
import {
    fetchCreateChatRoom,
    fetchLeaveChatRoom,
    fetchChatRooms,
    fetchChatMessages,
  } from "../apis/chat";
  
  import {
    CreateChatRoomRequest,
    CreateChatRoomResponse,
    ChatRoomListResponse,
    ChatMessagesResponse,
  } from "../types/chatTypes";
  
  // 채팅방 생성
  export const createChatRoom = async (data: CreateChatRoomRequest): Promise<CreateChatRoomResponse> => {
    try {
      return await fetchCreateChatRoom(data);
    } catch (err) {
      console.error("Error creating chat room:", err);
      throw err;
    }
  };
  
  // 채팅방 나가기
  export const leaveChatRoom = async (chatRoomId: number) => {
    try {
      return await fetchLeaveChatRoom(chatRoomId);
    } catch (err) {
      console.error("Error leaving chat room:", err);
      throw err;
    }
  };
  
  // 채팅방 목록
  export const getChatRooms = async (): Promise<ChatRoomListResponse> => {
    try {
      return await fetchChatRooms();
    } catch (err) {
      console.error("Error fetching chat rooms:", err);
      throw err;
    }
  };
  
  // 채팅 메시지 조회
  export const getChatMessages = async (chatRoomId: number): Promise<ChatMessagesResponse> => {
    try {
      return await fetchChatMessages(chatRoomId);
    } catch (err) {
      console.error("Error fetching chat messages:", err);
      throw err;
    }
  };
  