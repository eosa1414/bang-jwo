import { useParams } from "react-router-dom";
import MaterialIcon from "../../../components/MaterialIcon";
import LayoutTitle from "../../../layouts/LayoutTitle";
import { Viewer, Worker } from "@react-pdf-viewer/core";
import "@react-pdf-viewer/core/lib/styles/index.css";
import { useEffect, useState } from "react";
import { getPdf, getRiskAnalysis } from "../services/registryService";
import { AnalysisResultDto } from "../types/registryTypes";

const PageRegistry = () => {
  const mockRiskData = {
    details: {
      registerId: "R-2024-000123",
      createdAt: "2025-04-10T15:22:00Z",
      status: "결제 완료",
    },
    riskDetails: [
      {
        category: "담보권",
        info: "총 5건의 근저당 설정 확인됨",
        riskLevel: "HIGH",
        description: "다수의 근저당 설정이 존재하여 위험도가 높습니다.",
      },
      {
        category: "소유권 이전",
        info: "최근 1년 내 3회 이전",
        riskLevel: "MEDIUM",
        description: "빈번한 소유권 이전이 관찰됨",
      },
      {
        category: "가압류",
        info: "없음",
        riskLevel: "LOW",
        description:
          "집주인이 돈을 갚지 않았기 때문에 돈을 빌려준 사람이 강제로 처분하는 절차예요",
      },
    ],
  };

  const pdfUrlDummy =
    "https://historykorea.org/wp-content/uploads/2021/12/%EA%B5%AD%EB%AF%BC-%ED%83%84%EC%83%9D%EC%9D%98-%EC%97%AD%EC%82%AC%EC%99%80-%EC%95%88%EC%A4%91%EA%B7%BC-%EC%9D%B4%ED%83%9C%EC%A7%84-%EB%8F%99%EB%B0%A9%ED%95%99%EC%A7%80-196%ED%98%B8.pdf";

  const { paymentId } = useParams();
  const [pdfUrl, setPdfUrl] = useState(pdfUrlDummy);
  const [riskData, setRiskData] = useState<AnalysisResultDto | null>(
    mockRiskData
  );
  const [loading, setLoading] = useState(true);

  const paymentIdNumber = paymentId ? parseInt(paymentId, 10) : null;

  useEffect(() => {
    const fetchPdfAndRiskAnalysis = async () => {
      setLoading(true);

      if (!paymentIdNumber) {
        console.error("Invalid paymentId");
        setLoading(false);
        return;
      }

      try {
        // PDF 파일 가져오기
        const pdfUrl = await getPdf(paymentIdNumber);
        setPdfUrl(pdfUrl);

        // 위험도 분석 데이터 가져오기
        const riskAnalysisData = await getRiskAnalysis(paymentIdNumber);
        setRiskData(riskAnalysisData);
      } catch (error) {
        console.error("데이터 불러오기 실패:", error);
      } finally {
        setLoading(false);
      }
    };

    if (paymentIdNumber !== null) {
      fetchPdfAndRiskAnalysis();
    }
  }, [paymentIdNumber]);

  if (loading) {
    return (
      <div className="flex w-full h-[calc(100vh-55px-44px)] justify-center items-center">
        등기사항증명서 및 위험도 분석 결과를 받아오는 중…
      </div>
    );
  }

  return (
    <>
      <LayoutTitle title="등기사항전부증명서 열람">
        <div className="flex items-center text-2xl font-bold p-4">
          <p className="text-gold flex items-center justify-center">
            <MaterialIcon icon="check" />
          </p>
          결제 완료되었습니다
        </div>
        <div className="space-y-6 py-6 bg-white rounded-xl shadow w-[24rem] max-w-full">
          <div className="text-xl px-6 font-bold flex items-center gap-2">
            <MaterialIcon icon="report" fill />
            위험도 분석
          </div>
          <div className="divider w-full" />

          <div className="space-y-4 px-6">
            {riskData?.riskDetails.map((risk, index) => (
              <div
                key={index}
                className="border border-neutral-light100 p-4 rounded-md bg-neutral-light300 flex flex-col gap-2"
              >
                <div className="flex justify-between items-center">
                  <span className="font-semibold text-lg">{risk.category}</span>
                  <span
                    className={`
                  text-xs font-bold px-2 p-1 rounded-lg
                  ${
                    risk.riskLevel === "HIGH"
                      ? "bg-coral-red text-neutral-white"
                      : risk.riskLevel === "MEDIUM"
                      ? "bg-gold-light text-neutral-black"
                      : "bg-green text-white"
                  }
                `}
                  >
                    {risk.riskLevel}
                  </span>
                </div>
                <p className="text-sm text-gray-700">{risk.description}</p>
                <p className="text-xs text-gray-500">{risk.info}</p>
              </div>
            ))}
          </div>
        </div>
        {/* pdf */}
        <div className="divider m-8 w-full" />
        <Worker workerUrl="https://unpkg.com/pdfjs-dist@3.4.120/build/pdf.worker.min.js">
          <Viewer fileUrl={pdfUrl} />
        </Worker>
      </LayoutTitle>
    </>
  );
};

export default PageRegistry;
