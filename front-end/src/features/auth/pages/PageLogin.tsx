import Buttonkakao from "../components/ButtonKakao";

const PageLogin = () => {
  const REDIRECT_URI = import.meta.env.VITE_KAKAO_REDIRECT_URI;

  const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${
    import.meta.env.VITE_KAKAO_CLIENT_ID
  }&redirect_uri=${REDIRECT_URI}&response_type=code`;

  const handleLoginClick = () => {
    window.location.href = KAKAO_AUTH_URL;
  };

  return (
    <div>
      <Buttonkakao onClick={handleLoginClick} />
    </div>
  );
};

export default PageLogin;
