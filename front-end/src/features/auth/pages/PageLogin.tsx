import LineBox from "../../../components/LineBox";
import Logo from "/logo.svg";
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
    <div className="flex flex-col items-center py-[4rem] px-[1rem]">
      <LineBox addClassName="flex flex-col gap-8 py-[4.25rem] px-[3.25rem] max-w-full">
        {/* logo */}
        <div className="flex flex-col gap-4 items-center">
          <img src={Logo} className="w-[200px]" alt="BangJwo logo" />
          <p className="text-6xl font-['TmonMonsori'] text-gold">방줘</p>
        </div>
        {/* bottom */}
        <div className="flex flex-col gap-6">
          <div className="text-center text-lg">
            <p>간편하게 로그인하고</p>
            <p className="font-bold">수수료 없이 방을 거래해 보세요!</p>
          </div>
          <Buttonkakao onClick={handleLoginClick} />
        </div>
      </LineBox>
    </div>
  );
};

export default PageLogin;
