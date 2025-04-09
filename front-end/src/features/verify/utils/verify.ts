import * as PortOne from "@portone/browser-sdk/v2";
import axiosInstance from "../../../utils/axiosInstances";

interface VerifyParams {
  verificationType: "member" | "contract" | "room";
  contractId?: number;
  roomId?: number;
  role?: "LANDLORD" | "TENANT";
  redirectUrl?: string;
  onSuccess?: () => void;
  onFailure?: () => void;
}

export const runIdentityVerification = async ({
  verificationType,
  contractId,
  roomId,
  role,
  redirectUrl = `${import.meta.env.VITE_APP_BASE_URL}`,
  onSuccess,
  onFailure,
}: VerifyParams) => {
  const storeId = import.meta.env.VITE_PORTONE_STORE_ID;
  const channelKey = import.meta.env.VITE_PORTONE_CHANNEL_KEY;
  const identityVerificationId = `identity-verification-${crypto.randomUUID()}`;

  try {
    const response = await PortOne.requestIdentityVerification({
      storeId,
      identityVerificationId,
      channelKey,
      redirectUrl,
    });

    if (response !== undefined && response.code !== undefined) {
      console.error("본인인증 실패:", response.message);
      onFailure?.();
      return;
    }

    const endpoint =
      verificationType === "member"
        ? "/api/v1/member/verify"
        : verificationType === "contract"
        ? "/api/v1/contract/verify"
        : `/api/v1/room/${roomId}/verify`;

    const payload =
      verificationType === "member"
        ? { identityVerificationId }
        : { contractId, identityVerificationId, role };

    console.log(payload);

    await axiosInstance.request({
      url: endpoint,
      method: verificationType === "member" ? "PUT" : "PATCH",
      data: payload,
    });

    console.log("본인인증 성공");
    onSuccess?.();
  } catch (error: any) {
    console.error("본인인증 중 오류 발생:", error.message);
    onFailure?.();
  }
};
