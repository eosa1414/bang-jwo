interface TabMenuProps {
  tabTitles: string[];
  addClassName?: string;
  onTabClick: (idx: number) => void;
  activeTab: number;
}

const TabMenu = ({
  tabTitles = [],
  addClassName = "",
  onTabClick,
  activeTab,
}: TabMenuProps) => {
  return (
    <div
      className={`w-full flex overflow-x-auto bg-real-white border-b-1 border-neutral-light100 ${addClassName}`}
    >
      {tabTitles.map((tabName, idx) => (
        <div
          key={idx}
          onClick={() => onTabClick(idx)}
          className={`cursor-pointer ${
            activeTab === idx ? "text-red-500 font-bold" : "text-gray-500"
          }`}
        >
          {tabName}
        </div>
      ))}
    </div>
  );
};

export default TabMenu;
