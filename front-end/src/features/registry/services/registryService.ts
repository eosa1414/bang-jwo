import { fetchPdf, fetchRiskAnalysis } from "../apis/registry";
import { AnalysisResultDto } from "../types/registryTypes";

export const getRiskAnalysis = async (
  paymentId: number
): Promise<AnalysisResultDto> => {
  try {
    const data = await fetchRiskAnalysis(paymentId);
    return data;
  } catch (error) {
    console.error("Error fetching risk analysis:", error);
    throw error;
  }
};

export const getPdf = async (paymentId: number): Promise<string> => {
  try {
    const pdfBlob = await fetchPdf(paymentId);
    const pdfUrl = URL.createObjectURL(pdfBlob);
    return pdfUrl;
  } catch (error) {
    console.error("Erro fetching PDF URL:", error);
    throw error;
  }
};
