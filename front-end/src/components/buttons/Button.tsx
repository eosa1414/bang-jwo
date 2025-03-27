import classNames from "classnames";
import { ButtonHTMLAttributes, ReactNode } from "react";

type ButtonSize = "basic" | "small" | "medium";
type Variant =
  | "default"
  | "dark"
  | "warning"
  | "neutral"
  | "point"
  | "gold"
  | "opaque";

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  size?: ButtonSize;
  variant?: Variant;
  children: ReactNode;
  isAngular?: boolean;
  isLine?: boolean;
}

const baseClasses =
  "inline-flex items-center justify-center w-fit flex-shrink-0";
const sizeClasses = {
  basic: `${baseClasses} h-9 min-w-[64px] px-3 text-base`,
  small: `${baseClasses} h-7 min-w-[48px] px-2 text-sm`,
  medium: `${baseClasses} h-12 min-w-[160px] px-4 text-lg`,
};

const variantClasses = {
  default: {
    defaultstyle: "text-neutral-black bg-neutral-light100",
    linestyle: "text-neutral-black border-neutral-gray",
  },
  dark: {
    defaultstyle: "text-neutral-white bg-neutral-dark200",
    linestyle: "text-neutral-dark200 border-neutral-dark200",
  },
  warning: {
    defaultstyle: "text-neutral-white bg-coral-red",
    linestyle: "text-coral-red border-coral-red",
  },
  neutral: {
    defaultstyle: "text-neutral-dark100 bg-neutral-light100",
    linestyle: "text-neutral-gray border-neutral-light100",
  },
  point: {
    defaultstyle: "text-neutral-black bg-gold-light",
    linestyle: "text-neutral-black border-neutral-black bg-gold-light",
  },
  gold: {
    defaultstyle: "text-gold-dark bg-gold-light/20",
    linestyle: "text-gold-dark border-gold",
  },
  opaque: {
    defaultstyle: "text-neutral-light200 bg-neutral-black/30",
    linestyle: "text-neutral-dark100 border-neutral-black/30",
  },
};

const Button = ({
  size = "basic",
  variant = "default",
  children,
  isAngular = false,
  isLine = false,
  className,
  disabled,
  ...props
}: ButtonProps) => {
  const { defaultstyle, linestyle } = variantClasses[variant];

  return (
    <button
      className={classNames(
        "font-semibold inline-flex items-center justify-center whitespace-nowrap",
        disabled ? "cursor-not-allowed" : "cursor-pointer",
        sizeClasses[size],
        !isAngular &&
          (size === "basic"
            ? "rounded-lg"
            : size === "small"
            ? "rounded-lg"
            : size === "medium"
            ? "rounded-2xl"
            : "rounded-lg"),
        isLine ? `border-1 ${linestyle}` : defaultstyle,
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
