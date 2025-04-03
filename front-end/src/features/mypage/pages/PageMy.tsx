import { Outlet } from "react-router-dom";
import LayoutTitle from "../../../layouts/LayoutTitle";
import MypageNav from "../components/MypageNav";

const PageMy = () => {
  return (
    <LayoutTitle title="Mypage">
      <MypageNav />
      <div className="w-full py-[2.125rem]">
        <Outlet />
      </div>
    </LayoutTitle>
  );
};

export default PageMy;
