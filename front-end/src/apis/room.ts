import axiosInstance from "../utils/axiosInstances";
import { RoomResponse, RoomQueryParams } from "../types/roomTypes";

export const fetchRooms = async (params: RoomQueryParams) => {
  const res = await axiosInstance.get<RoomResponse>("/api/v1/room", { params });
  return res.data;
};
