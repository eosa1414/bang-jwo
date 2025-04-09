import { fetchMyContracts } from "../apis/myContract";

export const getMyContracts = async (page: number = 0, size: number = 15) => {
  try {
    const data = await fetchMyContracts(page, size);
    return data;
  } catch (err) {
    console.error("Error fetching my contracts:", err);
    throw err;
  }
};
