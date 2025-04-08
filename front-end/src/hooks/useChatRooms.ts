// hooks/useChatRooms.ts
import { useQuery } from "@tanstack/react-query";
import { getChatRooms } from "../services/chatService";
import { ChatRoomSummary } from "../types/chatTypes";

export const useChatRooms = () => {
  console.log("✅ useChatRooms 훅 호출됨");
  return useQuery<ChatRoomSummary[]>({
    queryKey: ["chatRooms"],
    queryFn: async () => {
      console.log("📦 getChatRooms 실행됨");
      const data = await getChatRooms();
      console.log("✅ getChatRooms 결과:", data);
      return data;
    },
    staleTime: 1000 * 60 * 3,
  });
};
