import Button from "../components/buttons/Button";
import MaterialIcon from "../components/MaterialIcon";
import { useNavigate } from "react-router-dom";

const PageNotFound = () => {
  const navigate = useNavigate();

  const goHome = () => {
    navigate("/");
  };

  return (
    <div className="w-full flex-grow flex flex-col text-center items-center text-neutral-dark300">
      {/* 404 message (PC) */}
      <div className="w-full overflow-hidden">
        <p className="flex invisible sm:visible font-['TmonMonsori'] text-[calc(100vw/14)] whitespace-nowrap min-h-[20vh] justify-center items-center">
          {Array(7).fill("404").join(" ")}
        </p>
        <div className="divider dark300 w-full w2" />
      </div>

      {/* content */}
      <div className="flex flex-col flex-grow gap-10 items-center justify-center">
        <div className="flex flex-col gap-2  items-center justify-center">
          <p className="sm:hidden font-['TmonMonsori'] text-9xl whitespace-nowrap">
            404
          </p>
          <p className="text-sm">Page Not Found</p>
        </div>
        <div className="flex flex-col gap-2 items-center justify-center">
          <p className="font-bold text-xl flex items-center justify-center gap-1 mr-1">
            <MaterialIcon icon="house" />
            <span>앗! 찾고 계신 방이 없어요!</span>
          </p>
          <p>페이지가 사라졌거나 주소를 잘못 입력했을 수도 있어요.</p>
          <Button variant="dark" onClick={goHome}>
            홈으로 돌아가기
          </Button>
        </div>
      </div>

      {/* bottom line */}
      <div className="w-full overflow-hidden">
        <div className="divider dark300 w-full w2" />
        <p className="min-h-[20vh]"></p>
      </div>
    </div>
  );
};

export default PageNotFound;
