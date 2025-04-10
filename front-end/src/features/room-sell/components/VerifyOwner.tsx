import { useState } from "react";
import Button from "../../../components/buttons/Button";
import InfoText from "../../../components/InfoText";
import TitleBox from "../../../components/TitleBox";
import { useAuth } from "../../../contexts/AuthContext";
import Toast from "../../toast/components/Toast";
import { runIdentityVerification } from "../../verify/utils/verify";
import { useParams } from "react-router-dom";
import ModalAlert from "../../modal/pages/ModalAlert";
import useModal from "../../modal/hooks/useModal";

const VerifyOwner = () => {
  const { accessToken } = useAuth();
  const { roomId } = useParams();
  const roomIdNumber = roomId ? parseInt(roomId, 10) : undefined;
  const [toastMessage, setToastMessage] = useState("");
  const { isOpen, openModal, closeModal } = useModal();
  const [isPhoneVerified, setIsPhoneVerified] = useState(false);
  const [isCertificateVerified, setIsCertificateVerified] = useState(false);

  return (
    <div className="justify-center items-center text-center flex flex-col gap-12">
      <TitleBox title="본인 확인" required>
        <div
          onClick={() => {
            if (!accessToken) {
              setToastMessage("로그인이 필요합니다.");
              return;
            }
            runIdentityVerification({
              verificationType: "room",
              roomId: roomIdNumber,
              onSuccess: () => {
                setIsPhoneVerified(true);
                setToastMessage("본인 확인이 완료되었습니다.");
              },
              onFailure: () => {
                setToastMessage("본인 인증이 완료되지 않았습니다.");
              },
            });
          }}
          className="cursor-pointer w-[11.25rem] max-w-full h-[7.75rem] bg-gold flex flex-col p-2 items-center justify-center text-neutral-white font-bold text-lg rounded-md mx-auto"
        >
          <p>간편인증</p>
          <p>진행하기</p>
        </div>
        {isPhoneVerified && (
          <div className="text-sm">본인 확인이 완료되었습니다.</div>
        )}
      </TitleBox>
      <TitleBox title={`등기사항전부증명서 확인\n(구 등기부등본)`} required>
        <div className="mx-auto">
          <InfoText text="700원의 수수료가 듭니다." />
        </div>
        <div className="cursor-pointer w-[11.25rem] max-w-full h-[7.75rem] bg-gold flex flex-col p-2 items-center justify-center text-neutral-white font-bold text-lg rounded-md mx-auto">
          <p>등기사항전부증명서</p>
          <p>확인하기</p>
        </div>
        {isCertificateVerified && (
          <div className="text-sm">
            등가사항전부증명서의 확인이 완료되었습니다.
          </div>
        )}
      </TitleBox>
      <Button
        size="large"
        variant="point"
        onClick={() => {
          if (!isPhoneVerified || !isCertificateVerified) {
            openModal("notVerifed");
            return;
          }
          alert("구현 예정");
        }}
      >
        등록하기
      </Button>
      <ModalAlert
        isOpen={isOpen("notVerifed")}
        closeModal={() => closeModal("notVerifed")}
        title="확인해주세요"
        text="아직 인증이 완료되지 않았습니다."
      />
      {toastMessage && (
        <Toast message={toastMessage} onClose={() => setToastMessage("")} />
      )}
    </div>
  );
};

export default VerifyOwner;
