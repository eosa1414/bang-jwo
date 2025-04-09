import { useNavigate } from "react-router-dom";
import Button from "../../../components/buttons/Button";
import InfoText from "../../../components/InfoText";

const RoomSellNotice = () => {
  const navigate = useNavigate();
  const goNext = () => {
    navigate("write");
  };

  return (
    <div className="w-full min-h-[calc(100vh-55px-44px)] bg-gold flex items-center justify-center">
      <div className="items-center justify-center text-center bg-neutral-white rounded-md p-6 border-1 flex flex-col gap-6">
        <div className="self-start">
          <InfoText text="안내드립니다" />
        </div>
        <div className="text-sm">
          <p>집을 내놓으려면 수수료 700원과</p>
          <p>부동산 고유 번호가 필요합니다.</p>
        </div>
        <Button size="large" variant="point" isLine onClick={goNext}>
          계속 내놓으러 가기
        </Button>
      </div>
    </div>
  );
};

export default RoomSellNotice;
