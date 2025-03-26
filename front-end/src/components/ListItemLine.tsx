interface ListItemLineProps {
  title?: string;
  content?: string;
}

const ListItemLine = ({ title = "", content = "" }: ListItemLineProps) => {
  return (
    <div className="flex justify-between w-full font-medium text-sm">
      <span>{title}</span>
      <span>{content}</span>
    </div>
  );
};

export default ListItemLine;
