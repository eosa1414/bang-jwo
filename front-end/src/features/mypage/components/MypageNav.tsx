import MaterialIcon from "../../../components/MaterialIcon";

interface NavItem {
  name: string;
  icon: string;
}

const navItems: NavItem[] = [
  { name: "계약", icon: "contract" },
  { name: "내놓은 집", icon: "house" },
  { name: "찜", icon: "favorite" },
  { name: "리뷰 관리", icon: "reviews" },
  { name: "내 정보", icon: "person" },
];

const MypageNav = () => {
  return (
    <div className="flex flex-wrap">
      {navItems.map((item, idx) => (
        <div
          key={idx}
          className={`flex items-center gap-1.5 justify-center border-y-1 border-l-1 border-neutral-gray text-neutral-gray w-[130px] p-[0.5rem_0_0.5rem_0.25rem]${
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
