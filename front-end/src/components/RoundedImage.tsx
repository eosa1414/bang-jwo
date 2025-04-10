interface RoundedImageProps {
  src?: string;
  alt?: string;
  size?: string;
  className?: string;
}

const RoundedImage = ({
  src,
  alt = "rounded image",
  size = "2.375rem",
  className,
}: RoundedImageProps) => {
  return (
    <div
      className={`bg-neutral-light100 rounded-full overflow-hidden ${className}`}
      style={{ width: size, height: size }}
    >
      {src && (
        <img
          className="w-full h-full object-cover rounded-full"
          src={src}
          alt={alt}
        />
      )}
    </div>
  );
};

export default RoundedImage;
