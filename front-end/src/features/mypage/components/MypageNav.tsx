import { useNavigate } from "react-router-dom";
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
  { name: "리뷰 관리", icon: "reviews", path: "review" },
  { name: "내 정보", icon: "person", path: "" },
];

const MypageNav = () => {
  const navigate = useNavigate();

  return (
    <div className="flex flex-wrap">
      {navItems.map((item, idx) => (
        <div
          key={idx}
          onClick={() => {
            navigate(item.path);
          }}
          className={`cursor-pointer flex items-center gap-1.5 justify-center border-y-1 border-l-1 border-neutral-gray text-neutral-gray w-[130px] p-[0.5rem_0_0.5rem_0.25rem]${
            idx === navItems.length - 1 ? " border-r-1" : ""
          } `}
        >
          <MaterialIcon icon={item.icon} fill={true} />
          <span className="font-bold">{item.name}</span>
          <span
            className={`w-[6.85px] h-[6.85px] rounded-full bg-gold invisible`}
          />
        </div>
      ))}
    </div>
  );
};

export default MypageNav;
