import Modal from "../components/Modal";

interface ModalAlertProps {
  isOpen: boolean;
  closeModal: () => void;
  title?: string;
  text?: string;
}

const ModalAlert = ({ isOpen, closeModal, title, text }: ModalAlertProps) => {
  return (
    <Modal
      isOpen={isOpen}
      closeModal={closeModal}
      type="alert"
      modalStyle="info"
      title={title}
      content={
        <p className="text-lg font-semibold whitespace-pre-line text-center">
          {text}
        </p>
      }
      onConfirm={closeModal}
      confirmText="닫기"
    />
  );
};

export default ModalAlert;
