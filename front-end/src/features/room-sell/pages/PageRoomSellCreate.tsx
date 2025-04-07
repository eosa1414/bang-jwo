import { useState } from "react";
import LayoutTitle from "../../../layouts/LayoutTitle";
import {
  CreateRoomRequestDto,
  UpdateRoomRequestDto,
} from "../../../types/roomTypes";
import StepIndicator from "../../../components/StepIndicator";
import { Outlet, useNavigate } from "react-router-dom";

export interface RoomSellCreateContext {
  handleNext: (
    form: CreateRoomRequestDto | UpdateRoomRequestDto,
    images: File[],
    deleteImageIds?: number[]
  ) => void;
  formData: CreateRoomRequestDto | null;
  images: File[];
}

const PageRoomSellCreate = () => {
  const [formData, setFormData] = useState<CreateRoomRequestDto | null>(null);
  const [images, setImages] = useState<File[]>([]);
  const navigate = useNavigate();

  const handleNext = (data: CreateRoomRequestDto, imageFiles: File[]) => {
    setFormData(data);
    setImages(imageFiles);
    navigate("verify");
  };

  // 서버로 최종 제출
  const handleVerificationSuccess = async () => {
    const submitData = new FormData();
    Object.entries(formData!).forEach(([key, value]) => {
      if (Array.isArray(value)) {
        value.forEach((v) => submitData.append(key, String(v)));
      } else {
        submitData.append(key, String(value));
      }
    });
    images.forEach((img) => submitData.append("images", img));

    try {
      // await axiosInstance.post("/api/v1/room", submitData);
      navigate("success");
    } catch (err) {
      alert("등록 실패");
    }
  };

  const steps = [
    { icon: "edit", label: "정보 입력" },
    { icon: "real_estate_agent", label: "집주인 인증" },
    { icon: "check", label: "등록 완료" },
  ];

  const currentStepPath = location.pathname.split("/").pop();
  const currentStep =
    currentStepPath === "write"
      ? 0
      : currentStepPath === "verify"
      ? 1
      : currentStepPath === "success"
      ? 2
      : -1;

  const contextValue = {
    handleNext,
    formData,
    images,
  };

  return (
    <>
      {currentStep !== -1 && (
        <LayoutTitle title="집 내놓기">
          <StepIndicator steps={steps} current={currentStep} />
          <Outlet context={contextValue} />
        </LayoutTitle>
      )}
      {currentStep === -1 && <Outlet context={contextValue} />}
    </>
  );
};

export default PageRoomSellCreate;
