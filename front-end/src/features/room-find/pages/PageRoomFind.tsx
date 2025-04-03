import { useEffect, useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { getRooms } from "../../../services/roomService";
import {
  RoomQueryParams,
  RoomResponse,
  defaultParams,
} from "../../../types/roomTypes";
import SideBar from "../components/SideBar";
import ListRoom from "../../../components/ListRoom";
import KakaoMap from "../../../components/KakaoMap";
import SearchBar from "../../../components/SearchBar";
import InfoText from "../../../components/InfoText";
import FilterPannel from "../../../components/FilterPannel";
import RoomDetail from "../components/RoomDetail";

const PageRoomFind = () => {
  const [params, setParams] = useState<RoomQueryParams>(defaultParams);
  const [selectedRoomId, setSelectedRoomId] = useState<number | null>(null);

  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const lat = urlParams.get("lat");
    const lng = urlParams.get("lng");
    const zoom = urlParams.get("zoom");

    setParams((prevParams) => ({
      ...prevParams,
      lat: lat ? parseFloat(lat) : prevParams.lat,
      lng: lng ? parseFloat(lng) : prevParams.lng,
      zoom: zoom ? parseInt(zoom, 10) : prevParams.zoom,
    }));
  }, []);

  const { data, isLoading, isError, error } = useQuery<RoomResponse, Error>({
    queryKey: ["rooms", params],
    queryFn: () => getRooms(params),
    enabled: !!params.lat && !!params.lng,
  });

  if (isLoading) return <div className="m-auto">Now Loading...</div>;
  if (isError)
    return <div className="m-auto">Error: {(error as Error).message}</div>;

  return (
    <div className="flex w-full min-h-0">
      <SideBar />

      {/* pannel */}
      <div className="flex flex-col items-center bg-real-white relative w-[340px] border-r-1 border-neutral-light100 z-2">
        <div className="flex flex-col w-full h-full overflow-hidden">
          <FilterPannel
            childrenTop={<SearchBar />}
            childrenBottom={<InfoText text="'방줘'에는 월세만 있습니다." />}
          />
          <ListRoom
            listClassName="flex-1 overflow-y-auto"
            rooms={data?.items || []}
            onSelectRoom={(roomId) => setSelectedRoomId(roomId)}
          />
        </div>

        {/* modal */}
        <RoomDetail
          selectedRoomId={selectedRoomId}
          onClose={() => setSelectedRoomId(null)}
        />
      </div>

      {/* map */}
      <KakaoMap lat={33.450701} lng={126.570667} />
    </div>
  );
};

export default PageRoomFind;
