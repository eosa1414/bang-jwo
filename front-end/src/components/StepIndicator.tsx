import MaterialIcon from "./MaterialIcon";

type Step = {
  icon?: string;
  label: string;
};

interface StepIndicatorProps {
  steps: Step[];
  current: number;
}

const StepIndicator = ({ steps, current }: StepIndicatorProps) => {
  return (
    <div className="flex mx-auto border-l-1 border-t-1 border-b-1 border-neutral-gray max-w-full mb-[3.25rem]">
      {steps.map((step, index) => {
        const isActive = index === current;

        return (
          <div
            className={`flex flex-col gap-0.5 items-center justify-center font-bold max-w-full w-[7.5rem] h-[5.65rem] pt-[1.25rem] pb-[0.75rem] border-r-1 border-neutral-gray ${
              isActive ? "bg-neutral-dark300 text-gold" : "text-neutral-gray"
            }`}
          >
            {step.icon && <MaterialIcon icon={step.icon} />}
            <div>{step.label}</div>
            <span
              className={`shrink-0 w-[6.85px] h-[6.85px] rounded-full bg-gold ${
                isActive ? "" : "invisible"
              }`}
            />
          </div>
        );
      })}
    </div>
  );
};

export default StepIndicator;
