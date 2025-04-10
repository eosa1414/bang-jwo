import { AnalysisResultDto } from "../types/registryTypes";
import axiosInstance from "../../../utils/axiosInstances";

export const fetchRiskAnalysis = async (paymentId: number) => {
  console.log(paymentId);
  const res = await axiosInstance.post<AnalysisResultDto>(
    `/api/v1/registry/risk/payment/${paymentId}`
  );
  return res.data;
};
