interface NoticeGrayProps {
  children: React.ReactNode;
  arrowOffsetLeft?: string;
}

const NoticeGray = ({
  children,
  arrowOffsetLeft = "36px",
}: NoticeGrayProps) => {
  return (
    <div className="relative w-fit mt-[-4px] opacity-95">
      <div
        className="absolute top-[-6px] w-0 h-0 border-x-8 border-x-transparent border-b-[8px] border-b-neutral-dark200"
        style={{ left: arrowOffsetLeft }}
      />

      <div className="flex items-center gap-2 px-4 py-3 bg-neutral-dark200 text-white rounded-xl text-sm shadow-lg">
        <i className="material-symbols-rounded text-white text-base">info</i>
        <span className="font-medium">{children}</span>
      </div>
    </div>
  );
};

export default NoticeGray;
