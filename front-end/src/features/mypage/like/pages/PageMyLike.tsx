import { useEffect, useState } from "react";
import { getLikedRooms } from "../../../../services/likeService";
import { Room } from "../../../../types/roomTypes";
import ListItemRoom from "../../../../components/ListItemRoom";
import Pagination from "../../../../components/buttons/Pagination";
import { useRoomNavigation } from "../../../../hooks/useRoomNavigation";

const PageMyLike = () => {
  const { goToRoomDetail } = useRoomNavigation();
  const [likedRooms, setLikedRooms] = useState<Room[]>([]);
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const res = await getLikedRooms(page); // 1-based page
        setLikedRooms(res.items);
        setTotalPages(res.totalPages);
      } catch (error) {
        console.error("Failed to load likes:", error);
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
      ) : likedRooms.length === 0 ? (
        <p className="text-center text-sm px-2 py-8">찜한 집이 없습니다.</p>
      ) : (
        <div>
          <ul>
            {likedRooms.map((item) => (
              <li key={item.roomId}>
                <ListItemRoom
                  onSelectRoom={() => {
                    goToRoomDetail(item.lat, item.lng, item.roomId);
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

export default PageMyLike;
