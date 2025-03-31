import OneTwoRoomIcon from "../../../assets/icons/one-two-room.svg?react";
import ApartIcon from "../../../assets/icons/apart.svg?react";
import VillaHouseIcon from "../../../assets/icons/villa-house.svg?react";
import OfficetelIcon from "../../../assets/icons/officetel.svg?react";

const menuDatas = [
  {
    id: 1,
    icon: <OneTwoRoomIcon />,
    label: ["원룸", "·", "투룸"],
  },
  {
    id: 2,
    icon: <ApartIcon />,
    label: ["아파트"],
  },
  {
    id: 3,
    icon: <VillaHouseIcon />,
    label: ["빌라", "·", "주택"],
  },
  {
    id: 4,
    icon: <OfficetelIcon />,
    label: ["오피스텔"],
  },
];

const SideBar = () => {
  return (
    <aside className="flex border-r-1 border-neutral-light100 shrink-0 self-stretch">
      <nav>
        <ul>
          {menuDatas.map(({ id, icon, label }) => (
            <li key={id} className="p-[5px_10px] text-xs">
              <div className="hover:bg-neutral-light200 flex flex-col justify-center items-center gap-1.5 rounded-md p-[6px_4px_2px_4px]">
                {icon}
                <p className="flex gap-1">
                  {label?.map((text, index) => (
                    <span key={index}>{text}</span>
                  ))}
                </p>
              </div>
            </li>
          ))}
        </ul>
      </nav>
    </aside>
  );
};

export default SideBar;
