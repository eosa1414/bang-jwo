import { fetchRiskAnalysis } from "../apis/registry";
import { AnalysisResultDto } from "../types/registryTypes";

export const getRiskAnalysis = async (
  paymentId: number
): Promise<AnalysisResultDto> => {
  try {
    const data = await fetchRiskAnalysis(paymentId); // Call API to fetch risk analysis
    return data;
  } catch (error) {
    console.error("Error fetching risk analysis:", error);
    throw error;
  }
};
