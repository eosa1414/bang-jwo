import {
  MaintenanceIncludeName,
  RoomOption,
  RoomBuildingType,
  RoomDirection,
} from "../types/roomTypes";

export const maintenanceIncludeLabel: Record<MaintenanceIncludeName, string> = {
  WATER: "수도료",
  ELECTRICITY: "전기료",
  INTERNET: "인터넷비",
  GAS: "가스비",
  CLEANING: "청소비",
  CABLE_TV: "유선 TV",
  PARKING: "주차비",
  HEATING: "난방비",
  ELEVATOR_MAINTENANCE: "승강기 유지비",
};

export const roomOptionLabel: Record<RoomOption, String> = {
  ELEVATOR: "엘리베이터",
  ROOFTOP: "옥탑",
  AIR_CONDITIONER: "에어컨",
  WASHING_MACHINE: "세탁기",
  REFRIGERATOR: "냉장고",
  MICROWAVE: "전자렌지",
  GAS_RANGE: "가스렌지",
  INDUCTION: "인덕션",
  BED: "침대",
};

export const roomBuildingTypeLabel: Record<RoomBuildingType, string> = {
  ONEROOM_TWOROOM: "원룸 · 투룸",
  APARTMENT: "아파트",
  VILLA_HOUSE: "빌라 · 주택",
  OFFICETEL: "오피스텔",
};

export const roomDirectionTypeLabel: Record<RoomDirection, string> = {
  NORTH: "북향",
  NORTHEAST: "북동향",
  EAST: "동향",
  SOUTHEAST: "남동향",
  SOUTH: "남향",
  SOUTHWEST: "남서향",
  WEST: "서향",
  NORTHWEST: "북서향",
};
