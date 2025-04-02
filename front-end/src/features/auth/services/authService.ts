import { postKakaoAuth } from "../apis/auth";

export const loginWithKakao = async (
  code: string,
  login: (username: string) => void
) => {
  try {
    const res = await postKakaoAuth(code);
    console.log("로그인 성공:", res.data);

    login(res.data.user);

    return res.data;
  } catch (err) {
    console.error("로그인 실패:", err);
    throw err;
  }
};
