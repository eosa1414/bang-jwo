import SideBar from "./SideBar";
import ImageItem from "../../components/ImageItem";
import KakaoMap from "../../components/KakaoMap";

const RoomFind = () => {
  return (
    <div className="flex w-full min-h-0">
      <SideBar />

      {/* pannel */}
      <div className="bg-real-white relative w-[340px] border-r-1 border-neutral-light100">
        <ImageItem />
        <ImageItem />

        {/* modal */}
        <div className="absolute z-2 w-[320px] h-full left-[340px] top-0 bottom-0 p-[10px]">
          <div className="bg-real-white w-full h-full rounded-xl shadow-custom"></div>
        </div>
      </div>

      {/* map */}
      <KakaoMap lat={33.450701} lng={126.570667} />
    </div>
  );
};

export default RoomFind;
