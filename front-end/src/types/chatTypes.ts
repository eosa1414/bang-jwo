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
  