import { RoomResponse } from "../types/roomTypes";
import axiosInstance from "../utils/axiosInstances";

export const fetchLikedRooms = async (page: number, size: number) => {
  console.log(page + "/" + size);
  const res = await axiosInstance.get<RoomResponse>("/api/v1/member/like", {
    params: { page, size },
  });
  return res.data;
};

export const toggleRoomLike = async (roomId: number): Promise<void> => {
  await axiosInstance.post(`/api/v1/room/${roomId}/like`);
};
