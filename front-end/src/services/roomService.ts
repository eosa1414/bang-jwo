import { fetchRooms } from "../apis/room";
import {
  RoomResponse,
  RoomQueryParams,
  defaultParams,
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
