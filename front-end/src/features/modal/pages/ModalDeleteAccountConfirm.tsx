import Modal from "../components/Modal";

interface ModalDeleteAccountConfirmProps {
  isOpen: boolean;
  closeModal: () => void;
}

const ModalDeleteAccountConfirm = ({
  isOpen,
  closeModal,
}: ModalDeleteAccountConfirmProps) => {
  const deleteAccount = () => {
    alert("회원 가입 구현 예정");
  };

  return (
    <Modal
      isOpen={isOpen}
      closeModal={closeModal}
      type="confirm"
      modalStyle="info"
      title="탈퇴 확인"
      content={<>정말 탈퇴하시겠습니까?</>}
      onConfirm={() => {
        deleteAccount();
        console.log("탈퇴 성공");
        closeModal();
      }}
      onCancel={() => {
        console.log("탈퇴 취소");
        closeModal();
      }}
      confirmText="확인"
      cancelText="취소"
    />
  );
};

export default ModalDeleteAccountConfirm;
