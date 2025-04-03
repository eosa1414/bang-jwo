import { fetchRooms, fetchDetailRoom } from "../apis/room";
import {
  RoomResponse,
  RoomQueryParams,
  defaultParams,
  RoomDetailResponse,
} from "../types/roomTypes";

export const getRooms = async (params: RoomQueryParams = defaultParams) => {
  try {
    const roomsData: RoomResponse = await fetchRooms(params);
    return roomsData;
  } catch (err) {
    console.error("Error fetching rooms:", err);
    throw err;
  }
};

export const getRoomDetail = async (
  roomId: number
): Promise<RoomDetailResponse> => {
  try {
    const room = await fetchDetailRoom(roomId);
    return room;
  } catch (err) {
    console.error("Error  fetching room detail:", err);
    throw err;
  }
};