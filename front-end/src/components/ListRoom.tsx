import ListItemRoom from "./ListItemRoom";
import { Room } from "../types/roomTypes";

interface ListRoomProps {
  rooms: Room[];
  listClassName?: string;
}

const ListRoom = ({ rooms, listClassName = "" }: ListRoomProps) => {
  return (
    <div className={`scroll-custom ${listClassName}`}>
      {rooms.map((room) => (
        <ListItemRoom key={room.roomId} room={room} />
      ))}
    </div>
  );
};

export default ListRoom;
