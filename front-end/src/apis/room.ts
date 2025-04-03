import axiosInstance from "../utils/axiosInstances";
import { RoomResponse, RoomQueryParams, RoomDetailResponse } from "../types/roomTypes";

export const fetchRooms = async (params: RoomQueryParams) => {
  const res = await axiosInstance.get<RoomResponse>("/api/v1/room", { params });
  return res.data;
};

export const fetchDetailRoom = async (roomId: number) => {
  const res = await axiosInstance.get<RoomDetailResponse>(`/api/v1/room/${roomId}`);
  return res.data;
}