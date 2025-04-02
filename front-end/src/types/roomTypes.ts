export interface Pagable {
  totalItems: number;
  totalPages: number;
  currentPage: number;
  size: number;
}

export interface Room {
  roomId: number;
  memberId: number;
  isLiked: boolean;
  buildingType: string;
  status: string;
  deposit: number;
  monthlyRent: number;
  exclusiveArea: number;
  supplyArea: number;
  maintenanceCost: number;
  simpleDescription: string;
  floor: string;
  imageUrl: string;
}

export const defaultRoom: Room = {
  roomId: 0,
  memberId: 0,
  isLiked: false,
  buildingType: "",
  status: "",
  deposit: 0,
  monthlyRent: 0,
  exclusiveArea: 0,
  supplyArea: 0,
  maintenanceCost: 0,
  simpleDescription: "",
  floor: "",
  imageUrl: "",
};

export interface RoomResponse {
  totalItems: number;
  totalPages: number;
  currentPage: number;
  size: number;
  currentPageItemCount: number;
  offset: number;
  items: Room[];
}

// 쿼리
export interface RoomQueryParams {
  buildingType?: string;
  price?: string;
  area?: string;
  lat?: number;
  lng?: number;
  zoom?: number;
  page?: number;
}

export const defaultParams: RoomQueryParams = {
  buildingType: "",
  price: "",
  area: "",
  lat: 33.450701,
  lng: 126.570667,
  zoom: 4,
  page: 1,
};
