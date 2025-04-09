import { fetchMyProfile, patchMyProfile } from "../apis/account";
import { MemberResponseDto } from "../types/accountTypes";

export const getMyProfile = async (): Promise<MemberResponseDto> => {
  try {
    const userInfo = await fetchMyProfile();
    return userInfo;
  } catch (err) {
    console.error("Error fetching my profile:", err);
    throw err;
  }
};

export const updateMyProfile = async (formData: FormData) => {
  try {
    const result = await patchMyProfile(formData);
    return result;
  } catch (err) {
    console.error("Error updating my profile:", err);
    throw err;
  }
};
