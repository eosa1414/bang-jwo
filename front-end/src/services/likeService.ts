import { RoomResponse } from "../types/roomTypes";
import { fetchLikedRooms, toggleRoomLike } from "../apis/like";

export const getLikedRooms = async (
  page: number,
  size: number = 15
): Promise<RoomResponse> => {
  try {
    const data = await fetchLikedRooms(page, size);
    return data;
  } catch (error) {
    console.error("Error fetching liked rooms:", error);
    throw error;
  }
};

export const handleRoomLike = async (roomId: number): Promise<void> => {
  try {
    await toggleRoomLike(roomId);
  } catch (error) {
    console.error("Error toggling room like:", error);
    throw error;
  }
};
