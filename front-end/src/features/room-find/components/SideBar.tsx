import OneTwoRoomIcon from "../../../assets/icons/onetworoom.svg?react";
import OneTwoRoomDarkIcon from "../../../assets/icons/onetworoom_dark.svg?react";
import ApartIcon from "../../../assets/icons/apart.svg?react";
import ApartDarkIcon from "../../../assets/icons/apart_dark.svg?react";
import VillaHouseIcon from "../../../assets/icons/villahouse.svg?react";
import VillaHouseDarkIcon from "../../../assets/icons/villahouse_dark.svg?react";
import OfficetelIcon from "../../../assets/icons/officetel.svg?react";
import OfficetelDarkIcon from "../../../assets/icons/officetel_dark.svg?react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { useHoverAnimation } from "../../../hooks/useHoverAnimate";

const menuDatas = [
  {
    id: 1,
    icon: <OneTwoRoomIcon />,
    icon_dark: <OneTwoRoomDarkIcon />,
    label: ["원룸", "·", "투룸"],
    buildingType: "ONEROOM_TWOROOM",
  },
  {
    id: 2,
    icon: <ApartIcon />,
    icon_dark: <ApartDarkIcon />,
    label: ["아파트"],
    buildingType: "APARTMENT",
  },
  {
    id: 3,
    icon: <VillaHouseIcon />,
    icon_dark: <VillaHouseDarkIcon />,
    label: ["빌라", "·", "주택"],
    buildingType: "VILLA_HOUSE",
  },
  {
    id: 4,
    icon: <OfficetelIcon />,
    icon_dark: <OfficetelDarkIcon />,
    label: ["오피스텔"],
    buildingType: "OFFICETEL",
  },
];

const SideBar = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { category } = useParams();

  const handleClick = (buildingType: string) => {
    const searchParams = new URLSearchParams(location.search);

    navigate({
      pathname: `/room/find/${buildingType}`,
      search: searchParams.toString(),
    });
  };

  return (
    <aside className="flex border-r-1 border-neutral-light100 shrink-0 self-stretch">
      <nav>
        <ul>
          {menuDatas.map(({ id, icon, icon_dark, label, buildingType }) => {
            const isActive = category === buildingType;
            const { animationClass, eventHandlers } =
              useHoverAnimation("pulse");

            return (
              <li
                key={id}
                className={`p-[5px_10px] text-xs transition-all border-r-2 ${
                  isActive
                    ? "bg-gold-light/30 border-gold-dark"
                    : "border-transparent"
                }`}
              >
                <div
                  onClick={() => handleClick(buildingType)}
                  {...eventHandlers}
                  className={`${
                    isActive ? "hover:bg-gold/20" : "hover:bg-neutral-light200"
                  } ${animationClass} flex flex-col justify-center items-center gap-1.5 rounded-md p-[6px_4px_2px_4px] cursor-pointer transition-all`}
                >
                  {isActive ? icon_dark : icon}
                  <p className="flex gap-1">
                    {label?.map((text, index) => (
                      <span key={index}>{text}</span>
                    ))}
                  </p>
                </div>
              </li>
            );
          })}
        </ul>
      </nav>
    </aside>
  );
};

export default SideBar;
