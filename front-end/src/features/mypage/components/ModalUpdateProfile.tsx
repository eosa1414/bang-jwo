import { useState, useEffect, useRef, ChangeEvent } from "react";
import Button from "../../../components/buttons/Button";
import InputBasic from "../../../components/InputBasic";
import RoundedImage from "../../../components/RoundedImage";
import Modal from "../../modal/components/Modal";
import {
  getMyProfile,
  updateMyProfile,
} from "../account/services/accountService";

interface ModalUpdateProfileProps {
  isOpen: boolean;
  closeModal: () => void;
  onProfileUpdated?: () => void;
  setToastMessage?: (msg: string) => void;
}

const ModalUpdateProfile = ({
  isOpen,
  closeModal,
  onProfileUpdated,
  setToastMessage,
}: ModalUpdateProfileProps) => {
  const [nickname, setNickname] = useState("");
  const [profileUrl, setProfileUrl] = useState("");
  const [profileFile, setProfileFile] = useState<File | null>(null);
  const inputImageRef = useRef<HTMLInputElement>(null);

  // 최초 렌더링 시 유저 정보 불러오기
  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const data = await getMyProfile();
        setNickname(data.nickName || "");
        setProfileUrl(data.profileUrl || "");
      } catch (err) {
        console.error("프로필 정보 불러오기 실패", err);
      }
    };

    if (isOpen) {
      fetchProfile();
    }
  }, [isOpen]);

  const handleImageChange = (e: ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      setProfileFile(file);
      const previewUrl = URL.createObjectURL(file);
      setProfileUrl(previewUrl);
    }
  };

  const triggerFileInput = () => {
    inputImageRef.current?.click();
  };

  const updateProfile = async () => {
    const formData = new FormData();
    formData.append("nickname", nickname);
    if (profileFile) {
      formData.append("profileImage", profileFile);
    }

    console.log(nickname);
    console.log(profileFile);
    try {
      await updateMyProfile(formData);
      setToastMessage?.("프로필 수정 완료!");
    } catch (err) {
      setToastMessage?.(
        "프로필 수정이 제대로 되지 않았어요. 다시 시도해주세요."
      );
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      closeModal={closeModal}
      type="confirm"
      modalStyle="close"
      title="프로필 수정"
      content={
        <>
          <div className="w-full flex flex-col gap-3">
            <div className="flex flex-wrap items-center gap-3">
              <div className="flex w-[3rem] items-center justify-center">
                <RoundedImage src={profileUrl} alt="profile image" />
              </div>
              <div>
                <input
                  ref={inputImageRef}
                  type="file"
                  accept="image/*"
                  onChange={handleImageChange}
                  className="hidden"
                />
                <Button isLine onClick={triggerFileInput}>
                  이미지 수정
                </Button>
              </div>
            </div>
            <div className="flex flex-wrap items-center gap-3">
              <div className="flex w-[3rem] items-center justify-center">
                <span className="font-medium">닉네임</span>
              </div>
              <InputBasic
                value={nickname}
                onChange={(e) => setNickname(e.target.value)}
                placeholder="닉네임을 입력해주세요"
              />
            </div>
          </div>
        </>
      }
      onConfirm={async () => {
        await updateProfile();
        console.log("프로필 수정 성공");
        closeModal();
        onProfileUpdated?.();
      }}
      onCancel={() => {
        console.log("프로필 수정 취소");
        closeModal();
      }}
      confirmText="확인"
      cancelText="취소"
    />
  );
};

export default ModalUpdateProfile;
