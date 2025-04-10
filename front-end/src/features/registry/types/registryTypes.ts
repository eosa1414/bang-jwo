export interface RiskDetailDto {
  category: string;
  info: string;
  riskLevel: string;
  description: string;
}

export interface AnalysisResultDto {
  details: {
    [key: string]: string;
  };
  riskDetails: RiskDetailDto[];
}
