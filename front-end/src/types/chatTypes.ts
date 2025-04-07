// types/chatTypes.ts

// 채팅방 생성 요청
export interface CreateChatRoomRequest {
    auctionId: number;
    participantIds: number[];
  }
  
  // 채팅방 생성 응답
  export interface CreateChatRoomResponse {
    chatRoomId: number;
  }
  
  // 채팅방 목록 응답
  export interface ChatRoomListResponse {
    chatRooms: {
      chatRoomId: number;
      auctionTitle: string;
      lastMessage: string;
      updatedAt: string;
    }[];
  }
  
  // 채팅 메시지 응답
  export interface ChatMessagesResponse {
    chatRoomId: number;
    messages: {
      messageId: number;
      senderId: number;
      content: string;
      createdAt: string;
    }[];
  }
  
  export type MessageType = "sent" | "received" | "system";

export interface Message {
  id: number;
  type: MessageType;
  text: string;
  time: string;
  date: string;
  senderName?: string;
  avatarUrl?: string;
  isReadByMe?: boolean;
  isReadByPartner?: boolean;
  role?: "임대인" | "임차인"; // 시스템 메시지에만 사용
}