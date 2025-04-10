import { create } from "zustand";
import { ChatRoomSummary, ChatMessage } from "../types/chatTypes";

interface ChatState {
  selectedChatId: number | null;
  chatRooms: ChatRoomSummary[];
  chatRoom: ChatRoomSummary | null
  messagesByChat: Record<number, ChatMessage[]>;
  setSelectedChatId: (id: number) => void;
  setChatRooms: (rooms: ChatRoomSummary[]) => void;
  setMessagesForRoom: (chatRoomId: number, messages: ChatMessage[]) => void;
  addMessageToRoom: (chatRoomId: number, message: ChatMessage) => void;
  setChatRoom: (room: ChatRoomSummary) => void;
}

export const useChatStore = create<ChatState>((set) => ({
  selectedChatId: null,
  chatRooms: [],
  messagesByChat: {},
  chatRoom: null,

  setSelectedChatId: (id) => set({ selectedChatId: id }),

  setChatRooms: (rooms) => set({ chatRooms: rooms }),

  setMessagesForRoom: (chatRoomId, messages) =>
    set((state) => ({
      messagesByChat: {
        ...state.messagesByChat,
        [chatRoomId]: messages,
      },
    })),

  addMessageToRoom: (chatRoomId, message) =>
    set((state) => ({
      messagesByChat: {
        ...state.messagesByChat,
        [chatRoomId]: [...(state.messagesByChat[chatRoomId] || []), message],
      },
    })),

  setChatRoom: (room) => set({ chatRoom: room })
}));