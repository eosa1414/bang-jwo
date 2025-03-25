export interface Pagable {
  totalItems: number;
  totalPages: number;
  currentPage: number;
  size: number;
}

export interface Room {
  roomId: number;
  roomType: string;
  monthlyRent: number;
  deposit: number;
  supplyArea: number;
  exclusiveArea: number;
  maintenanceCost: number;
  simpleDescription: string;
  addressDetail: string;
  floor: string;
  direction: string;
  image: string;
  like: boolean;
}

export const defaultRoom: Room = {
  roomId: 0,
  roomType: "",
  monthlyRent: 0,
  deposit: 0,
  supplyArea: 0,
  exclusiveArea: 0,
  maintenanceCost: 0,
  simpleDescription: "",
  addressDetail: "",
  floor: "",
  direction: "",
  image: "",
  like: false,
};

export interface RoomResponse {
  pagable: Pagable;
  data: Room[];
}

// 쿼리
export interface RoomQueryParams {
  buildingType?: string;
  price?: string;
  area?: string;
  lat?: string;
  lng?: string;
  zoom?: string;
  page?: number;
}

export const defaultParams: RoomQueryParams = {
  buildingType: "",
  price: "",
  area: "",
  lat: "",
  lng: "",
  zoom: "",
  page: 1,
};
