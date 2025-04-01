import Button from "../../../../components/buttons/Button";
import LineBox from "../../../../components/LineBox";
import { useAuth } from "../../../../contexts/AuthContext";
import useModal from "../../../modal/hooks/useModal";
import ModalDeleteAccountConfirm from "../../../modal/pages/ModalDeleteAccountConfirm";

const PageAccount = () => {
  const { user, logout } = useAuth();
  const { isOpen, openModal, closeModal } = useModal();

  return (
    <div className="flex flex-col justify-center max-w-lg mx-auto gap-2.5">
      <LineBox>
        <div className="flex justify-between items-center">
          <div className="flex items-center gap-3 flex-wrap flex-grow">
            <div className="rounded-full w-[48px] h-[48px] bg-neutral-light100" />
            <div className="font-semibold text-lg">{user}</div>
          </div>
          <Button size="small">프로필 수정</Button>
        </div>
      </LineBox>
      <LineBox>
        <button onClick={logout} className="cursor-pointer">
          로그아웃하기
        </button>
      </LineBox>
      <div className="text-right p-[0.375rem_1.5rem] text-sm text-neutral-gray">
        <button onClick={openModal} className="w-fit underline cursor-pointer">
          탈퇴하기
        </button>
        <ModalDeleteAccountConfirm isOpen={isOpen} closeModal={closeModal} />
      </div>
    </div>
  );
};

export default PageAccount;
