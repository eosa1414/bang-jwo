import { AnalysisResultDto } from "../types/registryTypes";
import axiosInstance from "../../../utils/axiosInstances";

export const fetchRiskAnalysis = async (paymentId: number) => {
  console.log(paymentId);
  const res = await axiosInstance.post<AnalysisResultDto>(
    `/api/v1/registry/risk/payment/${paymentId}`
  );
  return res.data;
};

export const fetchPdf = async (paymentId: number): Promise<Blob> => {
  const res = await axiosInstance.get(`/api/v1/registry/pdf/${paymentId}`, {
    responseType: "blob",
    headers: {
      Accept: "application/pdf",
    },
  });
  return res.data;
};
