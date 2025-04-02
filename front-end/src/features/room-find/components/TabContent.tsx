import { ReactNode, forwardRef } from "react";

interface TabContentProps {
  title?: string;
  scrollMarginTop?: number;
  children: ReactNode;
}

const TabContent = forwardRef<HTMLDivElement, TabContentProps>((props, ref) => {
  const { title = "", scrollMarginTop = 0, children } = props;

  return (
    <section
      ref={ref}
      className="flex flex-col gap-3"
      style={{
        scrollMarginTop: `calc(${scrollMarginTop}px + 0.875rem)`,
      }}
    >
      {title && <div className="font-bold text-base">{title}</div>}
      {children}
    </section>
  );
});

export default TabContent;
