import { motion } from "framer-motion";

interface CircularTextProps {
  text: string;
  size?: number;
  addClass?: string;
  rotateSpeed?: number; //작성 시 회전
}

const CircularText = ({
  text,
  size = 70,
  addClass,
  rotateSpeed,
}: CircularTextProps) => {
  const computedSize = `calc(1rem + ${size * 2}px)`;
  return (
    <motion.div
      className={`relative flex items-center justify-center ${addClass}`}
      style={{
        width: computedSize,
        height: computedSize,
      }}
      animate={rotateSpeed ? { rotate: 360 } : {}}
      transition={
        rotateSpeed
          ? { repeat: Infinity, duration: rotateSpeed, ease: "linear" }
          : {}
      }
    >
      {text.split("").map((char, i) => {
        const angle = (i / text.length) * 360 + 90;
        return (
          <span
            key={i}
            className="absolute left-1/2 top-1/2 text-lg leading-none"
            style={{
              transform: `translate(-50%, -50%) rotate(${angle}deg) translate(${size}px) rotate(90deg)`,
              transformOrigin: "center",
            }}
          >
            {char}
          </span>
        );
      })}
    </motion.div>
  );
};

export default CircularText;
