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
import { useLocation, useNavigate } from "react-router-dom";

const PageRoomFind = () => {
  const [params, setParams] = useState<RoomQueryParams>(defaultParams);
  const [selectedRoomId, setSelectedRoomId] = useState<number | null>(null);
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    const urlParams = new URLSearchParams(location.search);
    const lat = urlParams.get("lat");
    const lng = urlParams.get("lng");
    const zoom = urlParams.get("zoom");
    const roomId = urlParams.get("roomId");

    setParams((prevParams) => ({
      ...prevParams,
      lat: lat ? parseFloat(lat) : prevParams.lat,
      lng: lng ? parseFloat(lng) : prevParams.lng,
      zoom: zoom ? parseInt(zoom, 10) : prevParams.zoom,
    }));

    if (roomId) {
      setSelectedRoomId(parseInt(roomId, 10));
    }
  }, [location.search]);

  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.search);

    if (selectedRoomId !== null) {
      urlParams.set("roomId", String(selectedRoomId));
    } else {
      urlParams.delete("roomId");
    }

    urlParams.set("lat", String(params.lat));
    urlParams.set("lng", String(params.lng));
    urlParams.set("zoom", String(params.zoom));

    navigate(`?${urlParams.toString()}`, { replace: true });
  }, [selectedRoomId, params.lat, params.lng, params.zoom, navigate]);

  const { data, isLoading, isError, error } = useQuery<RoomResponse, Error>({
    queryKey: ["rooms", params],
    queryFn: () => getRooms(params),
    enabled: !!params.lat && !!params.lng,
  });

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

          {isLoading ? (
            <div className="flex-1 flex items-center justify-center">
              {/* <span>Now Loading...</span> */}
            </div>
          ) : isError ? (
            <div className="flex-1 flex items-center justify-center">
              <span>Error: {error.message}</span>
            </div>
          ) : (
            <ListRoom
              listClassName="flex-1 overflow-y-auto"
              rooms={data?.items || []}
              onSelectRoom={(roomId) => setSelectedRoomId(roomId)}
            />
          )}
        </div>

        {/* modal */}
        <RoomDetail
          selectedRoomId={selectedRoomId}
          onClose={() => setSelectedRoomId(null)}
        />
      </div>

      {/* map */}
      <KakaoMap
        lat={params.lat ?? 37.5}
        lng={params.lng ?? 127.04}
        zoom={params.zoom}
        onCenterChanged={(newLat, newLng) => {
          if (params.lat !== newLat || params.lng !== newLng) {
            setParams((prev) => ({
              ...prev,
              lat: newLat,
              lng: newLng,
            }));
          }
        }}
        onZoomChanged={(newZoom) => {
          if (params.zoom !== newZoom) {
            setParams((prev) => ({
              ...prev,
              zoom: newZoom,
            }));
          }
        }}
        rooms={data?.items}
      />
    </div>
  );
};

export default PageRoomFind;
