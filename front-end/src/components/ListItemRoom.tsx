import { Room, defaultRoom } from "../types/roomTypes";

interface ListItemRoomProps {
  room: Room;
}

const ListItemRoom = ({ room }: ListItemRoomProps) => {
  const roomData = room || defaultRoom;

  return (
    <div className="flex p-[12px] gap-[12px] border-b-1 border-neutral-light100">
      {/* left side */}
      <div className="bg-neutral-light100 w-[8.2rem] h-[8.8rem] rounded-md overflow-hidden relative">
        {/* 이미지가 있다면 <img> 노출 */}
        {roomData.image ? (
          <img
            className="w-full h-full object-cover"
            src={roomData.image}
            alt={`room image of ${roomData.roomType}`}
          />
        ) : (
          <div className="w-full h-full flex justify-center items-center text-sm text-neutral-gray">
            No image
          </div>
        )}

        {/* 그라데이션 오버레이 */}
        <div className="absolute top-0 left-0 bottom-0 right-0 w-full h-full bg-gradient-to-t from-black via-transparent to-transparent opacity-20"></div>

        {/* 하트 아이콘 */}
        <div className="absolute bottom-2 right-2 flex justify-center items-center text-neutral-white">
          <i className="material-symbols-rounded">favorite</i>
        </div>
      </div>

      <div className="flex flex-col gap-1.5 pt-[6px] pb-[6px]">
        <p className="flex gap-1 font-bold">
          <span>월세</span>
          <span>
            {roomData.deposit / 10000}/{roomData.monthlyRent / 10000}
          </span>
        </p>
        <p className="text-sm text-neutral-dark100 font-light">
          {roomData.roomType}
        </p>
        <p className="flex gap-0.5 text-sm">
          <span>
            {roomData.supplyArea}/{roomData.exclusiveArea}
          </span>
          <span>·</span>
          <span>{roomData.floor}</span>
          <span>·</span>
          <span>{roomData.maintenanceCost}원</span>
        </p>
        <p className="text-sm text-neutral-dark100">
          {roomData.simpleDescription}
        </p>
        <p className="text-sm">집주인</p>
      </div>
    </div>
  );
};

export default ListItemRoom;
