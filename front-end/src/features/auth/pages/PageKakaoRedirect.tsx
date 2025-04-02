import { useEffect } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import { loginWithKakao } from "../services/authService";
import { useAuth } from "../../../contexts/AuthContext";

const PageKakaoRedirect = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const { login } = useAuth();
  const code = searchParams.get("code");

  useEffect(() => {
    if (code) {
      loginWithKakao(code, login)
        .then(() => navigate("/"))
        .catch(() => console.error("로그인 처리 중 오류 발생"));
    }
  }, [code, navigate]);

  return (
    <div className="w-screen h-screen flex justify-center items-center bg-kakao-yellow">
      <div className="text-kakao-label">로그인 처리 중...</div>
    </div>
  );
};

export default PageKakaoRedirect;
