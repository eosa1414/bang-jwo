import Button from "../../../components/buttons/Button";

interface RoomOptionsProps {
  title?: string;
  roomOptions: string[];
}

const RoomOptions = ({ title = "", roomOptions = [] }: RoomOptionsProps) => {
  return (
    <div className="flex w-full flex-col gap-2.5 pt-[0.25rem] pb-[0.625rem]">
      {title && <p className="font-bold">{title}</p>}
      <div className="flex w-full gap-1 flex-wrap">
        {roomOptions.map((option, index) => (
          <Button key={index} size="small" as="div" variant="neutral">
            {option}
          </Button>
        ))}
      </div>
    </div>
  );
};

export default RoomOptions;
