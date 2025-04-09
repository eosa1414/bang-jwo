import { useState, HTMLAttributes } from "react";

export const useHoverAnimation = (animation: string = "pulse") => {
  const [isHover, setIsHover] = useState(false);

  const onMouseEnter: HTMLAttributes<HTMLElement>["onMouseEnter"] = () => {
    setIsHover(true);
  };

  const onAnimationEnd: HTMLAttributes<HTMLElement>["onAnimationEnd"] = () => {
    setIsHover(false);
  };

  const animationClass = isHover
    ? `animate__animated animate__${animation}`
    : "";

  return {
    animationClass,
    eventHandlers: {
      onMouseEnter,
      onAnimationEnd,
    },
  };
};
