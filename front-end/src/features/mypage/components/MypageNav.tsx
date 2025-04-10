import { useLocation, useNavigate } from "react-router-dom";
import MaterialIcon from "../../../components/MaterialIcon";

interface NavItem {
  name: string;
  icon: string;
  path: string;
}

const navItems: NavItem[] = [
  { name: "계약", icon: "contract", path: "contract" },
  { name: "내놓은 집", icon: "house", path: "sell" },
  { name: "찜", icon: "favorite", path: "like" },
  // { name: "리뷰 관리", icon: "reviews", path: "review" },
  { name: "내 정보", icon: "person", path: "" },
];

const MypageNav = () => {
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <div className="flex max-w-full">
      {navItems.map((item, idx) => {
        const isActive =
          item.path == ""
            ? location.pathname == "/mypage"
            : location.pathname.includes(`${item.path}`);
        return (
          <div
            key={idx}
            onClick={() => {
              navigate(item.path);
            }}
            className={`cursor-pointer flex items-center gap-1.5 justify-center border-y-1 border-l-1 border-neutral-gray w-[130px] p-[0.5rem_0rem_0.5rem_0.25rem] ${
              idx === navItems.length - 1 ? " border-r-1" : ""
            } ${
              isActive ? "bg-neutral-dark300 text-gold" : "text-neutral-gray"
            }`}
          >
            <MaterialIcon icon={item.icon} fill={true} />
            <span className="font-bold">{item.name}</span>
            <span
              className={`w-[6.85px] h-[6.85px] rounded-full bg-gold ${
                isActive ? "" : "invisible"
              }`}
            />
          </div>
        );
      })}
    </div>
  );
};

export default MypageNav;
