interface TabMenuProps {
  tabTitles: string[];
  addClassName?: string;
  onTabClick: (idx: number) => void;
}

const TabMenu = ({
  tabTitles = [],
  addClassName = "",
  onTabClick,
}: TabMenuProps) => {
  return (
    <div
      className={`w-full flex overflow-x-auto bg-real-white border-b-1 border-neutral-light100 ${addClassName}`}
    >
      {tabTitles.map((tabName, idx) => (
        <div key={idx} onClick={() => onTabClick(idx)}>
          {tabName}
        </div>
      ))}
    </div>
  );
};

export default TabMenu;
