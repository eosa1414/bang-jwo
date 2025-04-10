import { useNavigate } from "react-router-dom";
import Button from "../../../components/buttons/Button";
import LayoutTitle from "../../../layouts/LayoutTitle";
import MaterialIcon from "../../../components/MaterialIcon";

const PageWelcome = () => {
  const navigate = useNavigate();

  return (
    <LayoutTitle title="회원가입 완료!">
      <div className="flex flex-col gap-6 items-center m-4 text-xl">
        지금 바로 안전하게 방을 구해보세요.
        <MaterialIcon icon="arrow_downward" />
        <Button
          onClick={() => {
            navigate("/room/find");
          }}
          size="large"
        >
          집 보러 가기
        </Button>
        <Button
          onClick={() => {
            navigate("/mypage");
          }}
          variant="point"
          size="large"
        >
          본인 인증하러 가기
        </Button>
      </div>
    </LayoutTitle>
  );
};

export default PageWelcome;
