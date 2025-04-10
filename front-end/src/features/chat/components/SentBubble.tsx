interface SentBubbleProps {
  text: string;
  time: string;
  isReadByPartner?: boolean;
}

const SentBubble = ({ text, time, isReadByPartner }: SentBubbleProps) => {
  return (
    <div className="flex justify-end items-end gap-2 mb-3">
      <div className="flex items-end gap-1 flex-row-reverse">
        <div className="bg-forsythia px-4 py-2 rounded-2xl text-black text-base whitespace-pre-line">
          {text}
        </div>

        <div className="flex flex-col text-xs text-neutral-dark100 leading-tight min-w-[24px] translate-y-1.5">
          {isReadByPartner === false && (
            <span className="text-gold font-bold self-end">1</span>
          )}
          <span className="self-end">{time}</span>
        </div>
      </div>
    </div>
  );
};

export default SentBubble;
