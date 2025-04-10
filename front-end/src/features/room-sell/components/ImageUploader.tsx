import { useRef } from "react";
import MaterialIcon from "../../../components/MaterialIcon";
import { RoomImage } from "../../../types/roomTypes";

interface ImageUploaderProps {
  existingImages: RoomImage[];
  setExistingImages: (images: RoomImage[]) => void;
  newImages: File[];
  setNewImages: (images: File[]) => void;
  onDeleteImage: (id: number) => void;
}

const ImageUploader = ({
  existingImages,
  setExistingImages,
  newImages,
  setNewImages,
  onDeleteImage,
}: ImageUploaderProps) => {
  const inputRef = useRef<HTMLInputElement>(null);

  const handleImageUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!e.target.files) return;

    const fileList = Array.from(e.target.files);
    const combined = [...newImages, ...fileList];

    const uniqueImages = Array.from(new Set(combined.map((f) => f.name))).map(
      (name) => combined.find((f) => f.name === name)!
    );

    setNewImages(uniqueImages);
  };

  const handleClick = () => inputRef.current?.click();

  const handleRemoveNew = (index: number) => {
    const updated = [...newImages];
    updated.splice(index, 1);
    setNewImages(updated);
  };

  const handleRemoveExisting = (index: number) => {
    const imageToRemove = existingImages[index];
    onDeleteImage(imageToRemove.imageId);
    const updated = [...existingImages];
    updated.splice(index, 1);
    setExistingImages(updated);
  };

  const merged = [
    ...existingImages.map((img) => ({ type: "existing" as const, data: img })),
    ...newImages.map((file) => ({ type: "new" as const, data: file })),
  ];

  return (
    <div className="flex flex-wrap gap-3">
      {/* Upload Button */}
      <div
        onClick={handleClick}
        className="transition-all hover:text-gold text-neutral-gray w-[11.25rem] h-[7.75rem] rounded-md border-2 border-dashed border-neutral-light100 flex items-center justify-center cursor-pointer hover:border-gold"
      >
        <MaterialIcon icon="add" />
      </div>

      {/* Render All Images */}
      {merged.map((item, index) => {
        const isFirst = index === 0;

        const imageUrl =
          item.type === "existing"
            ? item.data.imageUrl
            : URL.createObjectURL(item.data);

        return (
          <div
            key={
              item.type === "existing"
                ? `existing-${item.data.imageId}`
                : `new-${item.data.name}`
            }
            className="relative w-[11.25rem] h-[7.75rem] bg-neutral-light200 rounded-md overflow-hidden border-1 border-neutral-light100"
          >
            <img
              src={imageUrl}
              alt="preview"
              className="w-full h-full object-cover"
            />

            {/* 대표 태그 */}
            {isFirst && (
              <span className="absolute top-2 left-2 bg-gold-dark text-white text-sm px-2.5 py-[2px] z-10 rounded-sm">
                대표
              </span>
            )}

            {/* 삭제 버튼 */}
            <button
              onClick={() =>
                item.type === "existing"
                  ? handleRemoveExisting(index)
                  : handleRemoveNew(index - existingImages.length)
              }
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
