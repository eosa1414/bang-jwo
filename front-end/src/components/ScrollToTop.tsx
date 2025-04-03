import { useState, useEffect } from "react";
import CircularText from "./CircularText";
import MaterialIcon from "./MaterialIcon";

const ScrollToTop = () => {
  const [size, setSize] = useState(window.innerWidth < 640 ? 50 : 70);

  useEffect(() => {
    const handleResize = () => {
      setSize(window.innerWidth < 640 ? 50 : 70);
    };

    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  const handleScroll = () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  return (
    <div
      onClick={handleScroll}
      className="cursor-pointer fixed flex items-center justify-center text-gold-light right-5 bottom-5 sm:right-10 sm:bottom-10 z-10000"
    >
      <CircularText
        text=" Scroll To Top •"
        addClass="text-gold-light"
        size={size} // 화면 크기에 따라 동적으로 변경
        rotateSpeed={20}
      />
      <div className="absolute text-center flex flex-col w-fit left-[50%] top-[50%] translate-x-[-50%] translate-y-[-50%] pb-3">
        <MaterialIcon icon="arrow_upward" size={size === 70 ? 48 : 32} />
        <span className={`font-bold ${size === 70 ? "text-4xl" : "text-2xl"}`}>
          Top
        </span>
      </div>
    </div>
  );
};

export default ScrollToTop;
