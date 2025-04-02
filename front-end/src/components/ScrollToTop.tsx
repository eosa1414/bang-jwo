import CircularText from "./CircularText";
import MaterialIcon from "./MaterialIcon";

const ScrollToTop = () => {
  const handleScroll = () => {
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  return (
    <div
      onClick={handleScroll}
      className="cursor-pointer fixed flex items-center justify-center text-gold-light right-10 bottom-10 z-10000"
    >
      <CircularText
        text=" Scroll To Top •"
        addClass="text-gold-light"
        rotateSpeed={20}
      />
      <div className="absolute text-center flex flex-col w-fit left-0 top-0 translate-x-4/5 translate-y-4/5">
        <MaterialIcon icon="arrow_upward" size={48} />
        <span className="text-4xl font-bold">Top</span>
      </div>
    </div>
  );
};

export default ScrollToTop;
