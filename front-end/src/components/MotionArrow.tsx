import { motion } from "framer-motion";

interface MotionArrowProps {
  addClass?: string;
}

export default function MotionArrow({ addClass = "" }: MotionArrowProps) {
  return (
    <div className={`flex flex-col items-center ${addClass}`}>
      <motion.div
        className="w-[2px] bg-neutral-black"
        animate={{
          height: [0, "calc(100vh - 55px - 28px)"],
        }}
        transition={{
          duration: 2,
          repeat: Infinity,
          ease: "easeInOut",
        }}
      />
      {/* arrow head */}
      <div className="flex flex-col items-center mt-[-24px]">
        <div className="w-[20px] h-[20px] border-b-2 border-r-2 border-neutral-black rotate-[45deg]" />
      </div>
    </div>
  );
}
