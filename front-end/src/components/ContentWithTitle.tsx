import { ReactNode } from "react";

interface ContentWithTitleProps {
  title?: string;
  children: ReactNode;
}

const ContentWithTitle = ({ title, children }: ContentWithTitleProps) => {
  return (
    <div className="flex gap-2 items-center">
      {title && (
        <p
          className={`shrink-0 w-[4.875rem] text-neutral-dark100 font-medium whitespace-pre-line`}
        >
          {title}
        </p>
      )}
      <div className="flex flex-wrap gap-2 items-center flex-grow">
        {children}
      </div>
    </div>
  );
};

export default ContentWithTitle;
