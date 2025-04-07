import { useNavigate } from "react-router-dom";
import Button from "../../../components/buttons/Button";
import MaterialIcon from "../../../components/MaterialIcon";

const CreateSuccess = () => {
  const navigate = useNavigate();
  const goHome = () => {
    navigate("/");
  };
  const seeRegistRoom = () => {
    navigate("/");
  };

  return (
    <div className="flex flex-col gap-2 justify-center items-center">
      <div className="flex items-center text-2xl font-bold p-4">
        <p className="text-gold flex items-center justify-center">
          <MaterialIcon icon="check" />
        </p>
        등록 완료되었습니다
      </div>
      <Button onClick={goHome} size="large">
        홈으로
      </Button>
      <Button onClick={seeRegistRoom} size="large" variant="point">
        등록한 집 확인하러 가기
      </Button>
    </div>
  );
};

export default CreateSuccess;
