import { create } from "zustand";
import { ChatRoomSummary, Message } from "../types/chatTypes";

interface ChatState {
  selectedChatId: number | null;
  chatRooms: ChatRoomSummary[];
  messagesByChat: Record<number, Message[]>;
  setSelectedChatId: (id: number) => void;
  setChatRooms: (rooms: ChatRoomSummary[]) => void;
  setMessagesForRoom: (chatRoomId: number, messages: Message[]) => void;
  addMessageToRoom: (chatRoomId: number, message: Message) => void;
}

export const useChatStore = create<ChatState>((set) => ({
  selectedChatId: null,
  chatRooms: [],
  messagesByChat: {},

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
}));