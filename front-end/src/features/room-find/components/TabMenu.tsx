import { useEffect, useRef, useState } from "react";

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
  const scrollRef = useRef<HTMLDivElement>(null);
  const [canScrollLeft, setCanScrollLeft] = useState(false);
  const [canScrollRight, setCanScrollRight] = useState(false);

  const updateScrollButtons = () => {
    if (!scrollRef.current) return;
    const { scrollLeft, scrollWidth, clientWidth } = scrollRef.current;
    setCanScrollLeft(scrollLeft > 0);
    setCanScrollRight(
      Math.ceil(scrollLeft + clientWidth) < Math.ceil(scrollWidth)
    );
  };

  const doScroll = (direction: "left" | "right") => {
    if (!scrollRef.current) return;
    const scrollAmount = 180;
    scrollRef.current.scrollBy({
      left: direction === "left" ? -scrollAmount : scrollAmount,
      behavior: "smooth",
    });
  };

  const scrollToActiveTab = () => {
    if (!scrollRef.current) return;
    const tabWidth = scrollRef.current.children[activeTab]?.clientWidth || 0;
    const scrollLeft = scrollRef.current.scrollLeft;
    const tabOffset = Array.from(scrollRef.current.children)
      .slice(0, activeTab)
      .reduce((acc, tab) => acc + tab.clientWidth, 0);

    const offset = tabOffset - scrollLeft;
    if (offset < 0 || offset + tabWidth > scrollRef.current.clientWidth) {
      scrollRef.current.scrollTo({
        left: tabOffset,
        behavior: "smooth",
      });
    }
  };

  useEffect(() => {
    updateScrollButtons();
    const scrollEl = scrollRef.current;
    if (scrollEl) {
      scrollEl.addEventListener("scroll", updateScrollButtons);
    }
    return () => {
      if (scrollEl) {
        scrollEl.removeEventListener("scroll", updateScrollButtons);
      }
    };
  }, []);

  useEffect(() => {
    scrollToActiveTab();
  }, [activeTab]);

  const maskStyle = {
    maskImage: `${
      canScrollLeft
        ? "linear-gradient(to right, transparent 0, transparent 4px, #000 36px, rgb(0, 0, 0) 100%)"
        : ""
    }${canScrollLeft && canScrollRight ? "," : ""}${
      canScrollRight
        ? "linear-gradient(to left, transparent 0, transparent 4px, #000 36px, rgb(0, 0, 0) 100%)"
        : ""
    }`,
    WebkitMaskImage: `${
      canScrollLeft
        ? "linear-gradient(to right, transparent 0, transparent 4px, #000 36px, rgb(0, 0, 0) 100%)"
        : ""
    }${canScrollLeft && canScrollRight ? "," : ""}${
      canScrollRight
        ? "linear-gradient(to left, transparent 0, transparent 4px, #000 36px, rgb(0, 0, 0) 100%)"
        : ""
    }`,
  };

  return (
    <div
      className={`px-10 relative w-full flex items-center bg-real-white border-b-1 border-neutral-light100 ${addClassName}`}
    >
      <div className="absolute z-1 left-0 top-0 bottom-0 px-2.5 y-full flex items-center">
        <button
          className={`flex items-center justify-center transition ${
            canScrollLeft ? "opacity-100 cursor-pointer" : "opacity-50"
          }`}
          onClick={() => doScroll("left")}
          disabled={!canScrollLeft}
        >
          <i className="material-symbols-rounded text-xl">arrow_circle_left</i>
        </button>
      </div>
      {/* tab menu */}
      <div
        ref={scrollRef}
        className={`scroll-hidden w-full flex gap-6 overflow-x-auto whitespace-nowrap font-medium`}
        style={maskStyle}
      >
        {tabTitles.map((tabName, idx) => (
          <div
            key={idx}
            onClick={() => onTabClick(idx)}
            className={`cursor-pointer py-[0.625rem] w-fit ${
              activeTab === idx
                ? "text-neutral-black border-b-1 border-neutral-black"
                : "text-neutral-gray"
            }`}
          >
            {tabName}
          </div>
        ))}
      </div>
      <div className="absolute z-1 right-0 top-0 bottom-0 px-2.5 y-full flex items-center">
        <button
          className={`flex items-center justify-center transition ${
            canScrollRight ? "opacity-100 cursor-pointer" : "opacity-50"
          }`}
          onClick={() => doScroll("right")}
          disabled={!canScrollRight}
        >
          <i className="material-symbols-rounded text-xl">arrow_circle_right</i>
        </button>
      </div>
    </div>
  );
};

export default TabMenu;
