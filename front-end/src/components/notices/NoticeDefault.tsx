interface NoticeDefaultProps {
  children: React.ReactNode;
}

const NoticeDefault = ({ children }: NoticeDefaultProps) => {
  return (
    <div className="flex gap-1 items-start p-3 bg-white rounded-md text-base leading-[22px] text-black font-medium">
      <span className="material-symbols-rounded text-lg text-neutral-dark200">
        info
      </span>
      <div>{children}</div>
    </div>
  );
};

export default NoticeDefault;
