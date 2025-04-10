import Modal from "../components/Modal";

interface ModalRoomPhoneProps {
  isOpen: boolean;
  closeModal: () => void;
  phone: string;
}

const ModalPhoneCheck = ({ isOpen, closeModal, phone }: ModalRoomPhoneProps) => {
  return (
    <Modal
      isOpen={isOpen}
      closeModal={closeModal}
      type="dismiss"
      modalStyle="info"
      title="연락처 정보"
      content={<p className="text-lg font-semibold">{phone}</p>}
      onConfirm={closeModal}
      confirmText="닫기"
    />
  );
};

export default ModalPhoneCheck;
