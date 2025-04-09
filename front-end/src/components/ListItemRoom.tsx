import { MouseEvent, useState } from "react";
import { useAuth } from "../contexts/AuthContext";
import { handleRoomLike } from "../services/likeService";
import { Room, defaultRoom } from "../types/roomTypes";
import Button from "./buttons/Button";
import MaterialIcon from "./MaterialIcon";

interface ListItemRoomProps {
  room: Room;
  onSelectRoom: (roomId: number) => void;
}

const ListItemRoom = ({ room, onSelectRoom }: ListItemRoomProps) => {
  const roomData = room || defaultRoom;
  const [isLiked, setIsLiked] = useState(roomData.isLiked);
  const { accessToken } = useAuth();

  const handleClick = () => {
    onSelectRoom(roomData.roomId);
  };

  const handleLikeClick = async (e: MouseEvent<HTMLDivElement>) => {
    e.stopPropagation();

    if (!accessToken) {
      alert("로그인을 해야 이용할 수 있습니다.");
      return;
    }

    try {
      await handleRoomLike(roomData.roomId);
      setIsLiked((prev) => !prev);
    } catch (error) {
      console.error("좋아요 처리 중 에러", error);
      alert("좋아요 처리 중 문제가 발생했습니다.");
    }
  };

  return (
    <div
      className="flex p-[12px] gap-[12px] border-b-1 border-neutral-light100 cursor-pointer bg-real-white"
      onClick={handleClick}
    >
      {/* left side */}
      <div className="bg-neutral-light100 w-[8.2rem] h-[8.8rem] rounded-md overflow-hidden relative flex-shrink-0">
        {/* 이미지가 있다면 <img> 노출 */}
        {roomData.imageUrl ? (
          <img
            className="w-full h-full object-cover"
            src={roomData.imageUrl}
            alt={`room image of ${roomData.buildingType}`}
          />
        ) : (
          <div className="w-full h-full flex justify-center items-center text-sm text-neutral-gray">
            No image
          </div>
        )}

        {/* 그라데이션 오버레이 */}
        <div className="absolute top-0 left-0 bottom-0 right-0 w-full h-full bg-gradient-to-t from-black via-transparent to-transparent opacity-20"></div>

        {/* 하트 아이콘 */}
        <div
          onClick={handleLikeClick}
          className="cursor-pointer absolute bottom-2 right-2 flex justify-center items-center text-neutral-white"
        >
          <MaterialIcon icon="favorite" fill={isLiked} />
        </div>
      </div>
      {/* right side */}
      <div className="flex flex-col gap-1.5 pt-[6px] pb-[6px]">
        <p className="flex gap-1 font-bold">
          <span>월세</span>
          <span>
            {roomData.deposit / 10000}/{roomData.monthlyRent / 10000}
          </span>
        </p>
        <p className="text-sm text-neutral-dark100 font-light">
          {roomData.buildingType}
        </p>
        <p className="flex flex-wrap gap-0.5 text-sm">
          <span>
            {roomData.supplyArea.toFixed(2)}/{roomData.exclusiveArea.toFixed(2)}
            ㎡
          </span>
          <span>·</span>
          <span>{roomData.floor}</span>
          <span>·</span>
          <span>{roomData.maintenanceCost}원</span>
        </p>
        <p className="text-sm text-neutral-dark100">
          {roomData.simpleDescription}
        </p>
        <Button size="small" variant="gold" isAngular>
          집주인
        </Button>
      </div>
    </div>
  );
};

export default ListItemRoom;
