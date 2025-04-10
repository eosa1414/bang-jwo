import { useParams } from "react-router-dom";
import LayoutTitle from "../../../layouts/LayoutTitle";
import RoomForm from "../components/RoomForm";
import { useRoomNavigation } from "../../../hooks/useRoomNavigation";

const PageRoomSellUpdate = () => {
  const params = useParams();
  const roomId = params.roomId ? parseInt(params.roomId) : null;
  const { goToRoomDetail } = useRoomNavigation();

  return (
    <LayoutTitle title="내놓은 집 수정하기">
      <RoomForm
        type="update"
        onSubmit={(_, __, ___, roomLatLng) => {
          if (roomLatLng && roomId !== null) {
            const { lat, lng } = roomLatLng;
            goToRoomDetail(lat, lng, roomId);
          }
        }}
      />
    </LayoutTitle>
  );
};

export default PageRoomSellUpdate;
