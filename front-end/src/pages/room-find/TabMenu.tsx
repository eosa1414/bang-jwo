import { RefObject, useEffect, useState } from "react";

interface TabMenuProps {
  tabList: string[];
  addClassName?: string;
  scrollRef?: RefObject<(HTMLDivElement | null)[]>;
}

const TabMenu = ({
  tabList = [],
  addClassName = "",
  scrollRef,
}: TabMenuProps) => {
  const [activeTabIdx, setActiveTabIdx] = useState<number | null>(null);

  const handleTabActive = (idx: number) => {
    setActiveTabIdx(idx);
    const y = scrollRef?.current[idx]?.offsetTop! - 52;
    window.scrollTo(0, y);

    console.log("눌렸다" + idx);
  };

  useEffect(() => {
    const activeTab = () => {
      scrollRef.current.forEach((ref: HTMLDivElement | null, idx: number) => {
        if (ref && ref.getBoundingClientRect().y < 53) {
          setActiveTabIdx(idx);
        }
      });
      window.addEventListener("scroll", activeTab);
      return () => {
        window.removeEventListener("scroll", activeTab);
      };
    };
  }, [scrollRef]);

  return (
    <div
      className={`w-full flex overflow-x-auto bg-real-white border-b-1 border-neutral-light100 ${addClassName}`}
    >
      {tabList.map((tabName, idx) => (
        <div key={idx} onClick={() => handleTabActive(idx)}>
          {tabName}
        </div>
      ))}
    </div>
  );
};

export default TabMenu;
