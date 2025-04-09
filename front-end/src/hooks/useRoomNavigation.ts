import { useNavigate } from "react-router-dom";

export const useRoomNavigation = () => {
  const navigate = useNavigate();
  const goToRoomDetail = (lat: number, lng: number, roomId: number) => {
    navigate(`/room/find?lat=${lat}&lng=${lng}&roomId=${roomId}`);
  };
  return { goToRoomDetail };
};
