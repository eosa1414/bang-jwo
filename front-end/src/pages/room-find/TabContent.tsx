import { ReactNode, forwardRef } from "react";

interface TabContentProps {
  title?: string;
  children: ReactNode;
}

const TabContent = forwardRef<HTMLDivElement, TabContentProps>((props, ref) => {
  const { title = "", children } = props;

  return (
    <div
      ref={ref}
      className="flex flex-col gap-3 scroll-mt-[calc(48px+1.25rem+1.75rem+0.875rem)]"
    >
      {title && <div className="font-bold text-base">{title}</div>}
      {children}
    </div>
  );
});

export default TabContent;
