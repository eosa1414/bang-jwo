import { useState } from "react";
import LayoutTitle from "../../../layouts/LayoutTitle";
import {
  CreateRoomRequestDto,
  UpdateRoomRequestDto,
} from "../../../types/roomTypes";
import StepIndicator from "../../../components/StepIndicator";
import { Outlet, useLocation, useNavigate } from "react-router-dom";

export interface RoomSellCreateContext {
  handleNext: (
    form: CreateRoomRequestDto | UpdateRoomRequestDto,
    images: File[],
    deleteImageIds?: number[],
    latLng?: { lat: number; lng: number }
  ) => void;
  formData: CreateRoomRequestDto | null;
  images: File[];
}

const PageRoomSellCreate = () => {
  const [formData] = useState<CreateRoomRequestDto | null>(null);
  const [images] = useState<File[]>([]);
  const navigate = useNavigate();

  const handleNext = (roomId: number) => {
    navigate(`verify/${roomId}`);
  };

  const steps = [
    { icon: "edit", label: "정보 입력" },
    { icon: "real_estate_agent", label: "집주인 인증" },
    { icon: "check", label: "등록 완료" },
  ];

  const location = useLocation();

  const currentStep =
    location.pathname === "/room/sell/write"
      ? 0
      : location.pathname.includes("verify")
      ? 1
      : location.pathname.includes("success")
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
