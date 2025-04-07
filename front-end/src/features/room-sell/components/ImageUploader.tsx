import { useRef } from "react";
import MaterialIcon from "../../../components/MaterialIcon";

type ImageUploaderProps = {
  images: File[];
  setImages: (images: File[]) => void;
};

const ImageUploader = ({ images, setImages }: ImageUploaderProps) => {
  const inputRef = useRef<HTMLInputElement>(null);

  const handleImageUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!e.target.files) return;

    const fileList = Array.from(e.target.files);
    const newImages = [...images, ...fileList];

    // 중복 제거
    const uniqueImages = Array.from(new Set(newImages.map((f) => f.name))).map(
      (name) => newImages.find((f) => f.name === name)!
    );

    setImages(uniqueImages);
  };

  const handleClick = () => {
    inputRef.current?.click();
  };

  const handleRemove = (index: number) => {
    const updated = [...images];
    updated.splice(index, 1);
    setImages(updated);
  };

  return (
    <div className="flex flex-wrap gap-3">
      {/* upload */}
      <div
        onClick={handleClick}
        className="transition-all hover:text-gold text-neutral-gray w-[11.25rem] h-[7.75rem] rounded-md border-2 border-dashed border-neutral-light100 flex items-center justify-center cursor-pointer hover:border-gold"
      >
        <MaterialIcon icon="add" />
      </div>
      {/* preview */}
      {images.map((file, index) => {
        const imageUrl = URL.createObjectURL(file);
        const isFirst = index === 0;

        return (
          <div
            key={index}
            className="relative w-[11.25rem] h-[7.75rem] bg-neutral-light200 rounded-md overflow-hidden border-1 border-neutral-light100"
          >
            <img
              src={imageUrl}
              alt={`preview-${index}`}
              className="w-full h-full object-cover"
            />
            {/* 대표 이미지 */}
            {isFirst && (
              <span className="absolute top-2 left-2 bg-gold-dark text-neutral-white text-sm  px-2.5 py-[2px] z-10 rounded-sm">
                대표
              </span>
            )}
            <button
              onClick={() => handleRemove(index)}
              className="cursor-pointer w-7 h-7 absolute top-1 right-1 bg-neutral-black/60 text-white text-xs rounded-full border-2 border-netral-white/60"
            >
              <MaterialIcon icon="close" />
            </button>
          </div>
        );
      })}

      <input
        type="file"
        accept="image/*"
        multiple
        onChange={handleImageUpload}
        ref={inputRef}
        className="hidden"
      />
    </div>
  );
};

export default ImageUploader;
