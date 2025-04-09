import { ReactNode } from "react";
import Button from "../../../components/buttons/Button";
import MaterialIcon from "../../../components/MaterialIcon";
import ModalPortal from "./ModalPortal";

type ModalType = "alert" | "confirm" | "dismiss";
type ModalStyle = "info" | "close" | "default";

interface ModalProps {
  isOpen: boolean;
  closeModal: () => void;
  type?: ModalType;
  modalStyle?: ModalStyle;
  title?: string | null;
  content: ReactNode;
  onConfirm?: () => void;
  onCancel?: () => void;
  confirmText?: string;
  cancelText?: string;
}

const Modal = ({
  isOpen,
  closeModal,
  type = "dismiss",
  modalStyle = "default",
  title,
  content,
  onConfirm,
  onCancel,
  confirmText,
  cancelText,
}: ModalProps) => {
  if (!isOpen) return null;

  return (
    <ModalPortal>
      {/* background */}
      <div className="fixed inset-0 flex items-center justify-center z-100 bg-neutral-black/70">
        {/* modal box */}
        <div className="bg-real-white rounded-xl w-full max-w-lg">
          {/* header */}
          {title && (
            <div
              className={`p-[1.25rem] font-medium text-lg ${
                modalStyle === "info"
                  ? ""
                  : "border-b-1 border-neutral-light100"
              }`}
            >
              {modalStyle === "info" && (
                <div className="w-full flex justify-center items-center gap-3 pr-3">
                  <MaterialIcon icon="info" />
                  {title}
                </div>
              )}
              {modalStyle === "close" && (
                <div className="flex justify-between">
                  {title}
                  <button
                    className="cursor-pointer"
                    onClick={() => {
                      onCancel?.();
                      closeModal();
                    }}
                  >
                    <MaterialIcon icon="close" />
                  </button>
                </div>
              )}
              {modalStyle === "default" && <div>{title}</div>}
            </div>
          )}
          {/* content */}
          <div className="p-[1rem_1.25rem] flex flex-col justify-center items-center">
            {content}
          </div>
          {/* footer */}
          <div className="flex flex-wrap justify-center gap-3 p-[1.25rem]">
            {type === "confirm" && (
              <Button
                variant="warning"
                size="medium"
                onClick={() => {
                  onCancel?.();
                  closeModal();
                }}
              >
                {cancelText || "취소"}
              </Button>
            )}
            <Button
              variant="point"
              size="medium"
              className={`${type === "dismiss" ? "w-full" : ""}`}
              onClick={() => {
                onConfirm?.();
                closeModal();
              }}
            >
              {confirmText || "확인"}
            </Button>
          </div>
        </div>
      </div>
    </ModalPortal>
  );
};

export default Modal;
