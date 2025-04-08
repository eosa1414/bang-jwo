import LayoutTitle from "../../../layouts/LayoutTitle";
import RoomForm from "../components/RoomForm";

const PageRoomSellUpdate = () => {
  return (
    <LayoutTitle title="내놓은 집 수정하기">
      <RoomForm type="update" onSubmit={() => console.log("ests")} />
    </LayoutTitle>
  );
};

export default PageRoomSellUpdate;
