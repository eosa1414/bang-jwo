import { useEffect, useState } from "react";
import { Room } from "../../../../types/roomTypes";
import ListItemRoom from "../../../../components/ListItemRoom";
import Pagination from "../../../../components/buttons/Pagination";
import { getMyRooms } from "../../../../services/roomService";

const PageMySell = () => {
  const [myRooms, setMyRooms] = useState<Room[]>([]);
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const res = await getMyRooms(page);
        setMyRooms(res.items);
        setTotalPages(res.totalPages);
      } catch (error) {
        console.error("Failed to load my rooms:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [page]);

  return (
    <div>
      {loading ? (
        <p className="text-center text-sm px-2 py-8">Now Loading...</p>
      ) : myRooms.length === 0 ? (
        <p className="text-center text-sm px-2 py-8">내놓은 집이 없습니다.</p>
      ) : (
        <div>
          <ul>
            {myRooms.map((item) => (
              <li key={item.roomId}>
                <ListItemRoom
                  onSelectRoom={() => {
                    console.log(item.roomId);
                  }}
                  room={item}
                />
              </li>
            ))}
          </ul>

          <Pagination
            totalPages={totalPages}
            currentPage={page}
            onPageChange={(pageNumber) => setPage(pageNumber)}
          />
        </div>
      )}
    </div>
  );
};

export default PageMySell;
