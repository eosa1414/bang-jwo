import ListItemRoom from "./ListItemRoom";
import { Room } from "../types/roomTypes";

interface ListRoomProps {
  rooms: Room[];
  listClassName?: string;
  onSelectRoom: (roomId: number) => void;
}

const ListRoom = ({
  rooms,
  listClassName = "",
  onSelectRoom,
}: ListRoomProps) => {
  return (
    <div className={`scroll-custom ${listClassName}`}>
      {rooms.map((room) => (
        <ListItemRoom
          key={room.roomId}
          room={room}
          onSelectRoom={onSelectRoom}
        />
      ))}
    </div>
  );
};

export default ListRoom;
