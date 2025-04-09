import { RoomResponse } from "../../../../types/roomTypes";
import axiosInstance from "../../../../utils/axiosInstances";

export const fetchMyContracts = async (
  page: number = 0,
  size: number
): Promise<RoomResponse> => {
  const res = await axiosInstance.get<RoomResponse>("/api/v1/member/contract", {
    params: { page, size },
  });
  return res.data;
};
