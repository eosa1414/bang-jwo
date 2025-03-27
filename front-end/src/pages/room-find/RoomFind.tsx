import { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { getRooms } from "../../services/roomService";
import {
  RoomQueryParams,
  RoomResponse,
  defaultParams,
  Room,
} from "../../types/roomTypes";
import SideBar from "./SideBar";
import ListRoom from "../../components/ListRoom";
import KakaoMap from "../../components/KakaoMap";
import SearchBar from "../../components/SearchBar";
import InfoText from "../../components/InfoText";
import FilterPannel from "../../components/FilterPannel";

const RoomFind = () => {
  const [params, setParams] = useState<RoomQueryParams>(defaultParams);

  // 더미 데이터
  const dummyRooms: Room[] = [
    {
      roomId: 1,
      roomType: "원룸",
      monthlyRent: 300000,
      deposit: 5000000,
      supplyArea: 15,
      exclusiveArea: 10,
      maintenanceCost: 30000,
      simpleDescription: "깔끔한 원룸입니다.",
      addressDetail: "서울시 강남구 역삼동",
      floor: "3층",
      direction: "남향",
      image:
        "https://images.pexels.com/photos/1457842/pexels-photo-1457842.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      like: false,
    },
    {
      roomId: 2,
      roomType: "투룸",
      monthlyRent: 500000,
      deposit: 10000000,
      supplyArea: 30,
      exclusiveArea: 20,
      maintenanceCost: 50000,
      simpleDescription: "넓고 따뜻한 투룸입니다.",
      addressDetail: "서울시 서초구 반포동",
      floor: "5층",
      direction: "동향",
      image:
        "https://images.pexels.com/photos/1643383/pexels-photo-1643383.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      like: true,
    },
    {
      roomId: 3,
      roomType: "원룸",
      monthlyRent: 350000,
      deposit: 6000000,
      supplyArea: 18,
      exclusiveArea: 12,
      maintenanceCost: 35000,
      simpleDescription: "조용하고 아늑한 원룸입니다.",
      addressDetail: "서울시 마포구 상수동",
      floor: "2층",
      direction: "서향",
      image:
        "https://images.pexels.com/photos/2826787/pexels-photo-2826787.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      like: false,
    },
    {
      roomId: 4,
      roomType: "투룸",
      monthlyRent: 600000,
      deposit: 15000000,
      supplyArea: 35,
      exclusiveArea: 25,
      maintenanceCost: 60000,
      simpleDescription: "넓고 깨끗한 투룸입니다.",
      addressDetail: "서울시 송파구 방이동",
      floor: "6층",
      direction: "북향",
      image:
        "https://images.pexels.com/photos/259962/pexels-photo-259962.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      like: true,
    },
    {
      roomId: 5,
      roomType: "오피스텔",
      monthlyRent: 800000,
      deposit: 20000000,
      supplyArea: 45,
      exclusiveArea: 35,
      maintenanceCost: 70000,
      simpleDescription: "모던하고 넓은 오피스텔입니다.",
      addressDetail: "서울시 강동구 고덕동",
      floor: "7층",
      direction: "동향",
      image:
        "https://images.pexels.com/photos/911738/pexels-photo-911738.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
      like: false,
    },
    {
      roomId: 6,
      roomType: "투룸",
      monthlyRent: 550000,
      deposit: 12000000,
      supplyArea: 28,
      exclusiveArea: 22,
      maintenanceCost: 40000,
      simpleDescription: "편리한 위치의 투룸입니다.",
      addressDetail: "서울시 종로구 삼청동",
      floor: "4층",
      direction: "서향",
      image: "",
      like: true,
    },
  ];

  const data = { data: dummyRooms } as RoomResponse;
  // const { data, isLoading, isError, error } = useQuery<RoomResponse, Error>(
  //   ["rooms", params],
  //   () => getRooms(params)
  // );

  // if (isLoading) return <div>Now Loading...</div>;
  // if (isError) return <div>Error: {(error as Error).message}</div>;

  return (
    <div className="flex w-full min-h-0">
      <SideBar />

      {/* pannel */}
      <div className="flex flex-col items-center bg-real-white relative w-[340px] border-r-1 border-neutral-light100">
        <div className="flex flex-col w-full h-full overflow-hidden">
          <FilterPannel
            childrenTop={<SearchBar />}
            childrenBottom={<InfoText text="'방줘'에는 월세만 있습니다." />}
          />

          <ListRoom
            listClassName="flex-1 overflow-y-auto"
            rooms={data?.data || []}
          />
        </div>

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
