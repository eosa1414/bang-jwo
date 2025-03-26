import React from "react";
import classNames from "classnames";

type ButtonSize = "basic" | "small" | "medium";
type ButtonColor =
  | "neutral-light100"
  | "neutral-dark200"
  | "coral-red"
  | "gold-light";
type TextColor = "neutral-black" | "neutral-white" | "neutral-dark100";

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  size?: ButtonSize;
  bgColor?: ButtonColor;
  textColor?: TextColor;
  children: React.ReactNode;
}

const sizeClasses = {
  basic: "inline-flex items-center justify-center h-9 w-fit min-w-[64px] px-3 text-base rounded-lg flex-shrink-0",
  small: "inline-flex items-center justify-center h-7 w-fit min-w-[48px] px-2 text-sm rounded-lg flex-shrink-0",
  medium: "inline-flex items-center justify-center h-12 w-fit min-w-[160px] px-4 text-lg rounded-2xl flex-shrink-0"
};

const bgColorClasses = {
  "neutral-light100": "bg-[var(--color-neutral-light100)]",
  "neutral-dark200": "bg-[var(--color-neutral-dark200)]",
  "coral-red": "bg-[var(--color-coral-red)]",
  "gold-light": "bg-[var(--color-gold-light)]",
};

const textColorClasses = {
  "neutral-black": "text-[var(--color-neutral-black)]",
  "neutral-white": "text-[var(--color-neutral-white)]",
  "neutral-dark100": "text-[var(--color-neutral-dark100)]",
};

const Button: React.FC<ButtonProps> = ({
  size = "basic",
  bgColor = "neutral-light100",
  textColor = "neutral-black",
  children,
  className,
  disabled,
  ...props
}) => {
  return (
    <button
      className={classNames(
        "font-semibold inline-flex items-center justify-center whitespace-nowrap", 
        disabled ? "cursor-not-allowed" : "cursor-pointer",
        sizeClasses[size],
        bgColorClasses[bgColor],
        textColorClasses[textColor],
        className
      )}
      disabled={disabled}
      {...props}
    >
      {children}
    </button>
  );
};

export default Button;
