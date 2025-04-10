import { useRef } from "react";
import SignatureCanvas from "react-signature-canvas";

interface SignatureModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSave: (data: string) => void;
}

const SignatureModal = ({ isOpen, onClose, onSave }: SignatureModalProps) => {
  const sigCanvasRef = useRef<SignatureCanvas | null>(null);

  const handleSave = () => {
    if (sigCanvasRef.current?.isEmpty()) {
      alert("서명을 입력해주세요.");
      return;
    }
    const dataUrl = sigCanvasRef.current?.toDataURL("image/png");
    if (dataUrl) {
      onSave(dataUrl);
    }
  };

  const handleClear = () => {
    sigCanvasRef.current?.clear();
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black/50 z-50">
      <div className="bg-white p-6 rounded-lg shadow-md w-[90%] max-w-[400px]">
        <h3 className="text-lg font-bold mb-4">서명</h3>
        <SignatureCanvas
          ref={sigCanvasRef}
          penColor="black"
          canvasProps={{
            width: 360,
            height: 150,
            className: "border border-gray-300",
          }}
        />
        <div className="flex justify-between mt-4">
          <button
            onClick={handleClear}
            className="px-4 py-2 bg-neutral-light200 text-sm rounded"
          >
            지우기
          </button>
          <div className="flex gap-2">
            <button
              onClick={onClose}
              className="px-4 py-2 bg-neutral-light200 text-sm rounded"
            >
              취소
            </button>
            <button
              onClick={handleSave}
              className="px-4 py-2 bg-gold text-white text-sm rounded"
            >
              저장
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SignatureModal;
