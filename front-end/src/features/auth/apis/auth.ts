import axiosInstance from "../../../utils/axiosInstances";

export const postKakaoAuth = (code: string) => {
  return axiosInstance.post("/api/v1/login", { code });
};

export const deleteAccount = () => {
  return axiosInstance.delete("/api/v1/member/me");
};
