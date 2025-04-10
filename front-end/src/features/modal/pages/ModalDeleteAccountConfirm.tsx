import { useNavigate } from "react-router-dom";
import { useAuth } from "../../../contexts/AuthContext";
import { leaveOurService } from "../../auth/services/authService";
import Modal from "../components/Modal";

interface ModalDeleteAccountConfirmProps {
  isOpen: boolean;
  closeModal: () => void;
  setToastMessage?: (msg: string) => void;
}

const ModalDeleteAccountConfirm = ({
  isOpen,
  closeModal,
  setToastMessage,
}: ModalDeleteAccountConfirmProps) => {
  const { logout } = useAuth();
  const navigate = useNavigate();

  const deleteAccount = async () => {
    try {
      await leaveOurService();
      setToastMessage?.("회원 탈퇴 완료. 안전한 방을 찾을 때 다시 만나요!");
      logout();
      navigate("/");
    } catch (err) {
      setToastMessage?.("회원 탈퇴가 완료되지 않았습니다. 다시 시도해주세요.");
    }
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
