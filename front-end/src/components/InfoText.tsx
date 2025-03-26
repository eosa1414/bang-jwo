interface InfoTextProps {
  text: string;
}

const InfoText = ({ text = "" }: InfoTextProps) => {
  return (
    <div className="flex items-center gap-0.5 text-neutral-dark100 text-sm font-bold pl-[.125rem]">
      <i className="material-symbols-rounded text-base font-bold">info</i>
      <span>{text}</span>
    </div>
  );
};

export default InfoText;
