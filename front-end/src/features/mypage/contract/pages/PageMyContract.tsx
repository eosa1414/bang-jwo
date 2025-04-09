import { useEffect, useState } from "react";
import ListItemContract from "../components/ListItemContract";
import { Room } from "../../../../types/roomTypes";
import { getMyContracts } from "../services/myContractService";
import Pagination from "../../../../components/buttons/Pagination";

const PageMyContract = () => {
  const [myContracts, setMyContracts] = useState<Room[]>([]);
  const [page, setPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(1);
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const res = await getMyContracts(page);
        setMyContracts(res.items);
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
    <>
      {loading ? (
        <p className="text-center text-sm px-2 py-8">Now Loading...</p>
      ) : myContracts.length === 0 ? (
        <p className="text-center text-sm px-2 py-8">
          계약하고 있는 집이 없습니다.
        </p>
      ) : (
        <>
          <ul className="flex flex-wrap">
            {myContracts.map((contract) => (
              <ListItemContract key={contract.roomId} contract={contract} />
            ))}
          </ul>
          <Pagination
            totalPages={totalPages}
            currentPage={page}
            onPageChange={(pageNumber) => setPage(pageNumber)}
          />
        </>
      )}
    </>
  );
};

export default PageMyContract;
