import KakaoSymbol from "../../../assets/images/kakaologin_symbol.png";

interface ButtonkakaoProps {
  onClick: () => void;
}

const Buttonkakao = ({ onClick }: ButtonkakaoProps) => {
  return (
    <button
      onClick={onClick}
      className="flex items-center bg-kakao-container rounded-[0.25rem] cursor-pointer min-h-[45px] w-[300px] min-w-fit max-w-full px-[0.3752rem]"
    >
      <img
        src={KakaoSymbol}
        alt="Kakao symbol"
        className="w-[1.125rem] mx-[0.5628rem]"
      />
      <div className="flex justify-center items-center flex-grow">
        카카오로 3초만에 시작하기
      </div>
    </button>
  );
};

export default Buttonkakao;
