import { useRef } from "react";
import Button from "../../../components/buttons/Button";
import HeaderContract from "../../../components/headers/HeaderContract";
import NoticeDefault from "../../../components/notices/NoticeDefault";
import Contract, { ContractRefType } from "../components/Contract";
import {
  useSaveLandlordInfo,
  useFinalizeLandlordContract,
} from "../../../apis/contract";
import { UpdateLandlordInfoDto } from "../data/contract.dto"; // ✅ DTO 타입 import

const SellerContractPage = () => {
  const contractRef = useRef<ContractRefType>(null);

  // ✅ 임시 저장 훅
  const { mutate: saveLandlordInfo, isPending: isSaving } =
    useSaveLandlordInfo();

  // ✅ 최종 저장 훅
  const { mutate: finalizeContract, isPending: isFinalizing } =
    useFinalizeLandlordContract();

  // ✅ 임시 저장 버튼 핸들러
  const handleTempSave = () => {
    const data = contractRef.current?.getFormData() as UpdateLandlordInfoDto;

    if (!data) {
      alert("저장할 데이터가 없습니다.");
      return;
    }

    const dataWithId = {
      ...data,
      contractId: 9, // <- 하드코딩으로 contractId 추가
    };

    saveLandlordInfo(dataWithId, {
      onSuccess: () => {
        alert("임시 저장 완료!");
      },
      onError: () => {
        alert("저장 실패!");
      },
    });
  };

  // ✅ 최종 등록 버튼 핸들러
  const handleFinalize = () => {
    const data = contractRef.current?.getFormData() as UpdateLandlordInfoDto;

    if (!data) {
      alert("제출할 데이터가 없습니다.");
      return;
    }
    const dataWithId = {
      ...data,
      contractId: 9, // <- 하드코딩으로 contractId 추가
    };

    finalizeContract(dataWithId, {
      onSuccess: () => {
        alert("계약서 등록이 완료되었습니다!");
        // TODO: 페이지 이동 또는 상태 초기화 등 처리 가능
      },
      onError: () => {
        alert("등록에 실패했습니다. 다시 시도해주세요.");
      },
    });
  };

  return (
    <div className="min-h-screen bg-white">
      <HeaderContract title="임대인 - 주택임대차계약서 작성" />

      <main className="flex flex-col gap-6 pt-10 px-4">
        <div className="flex flex-col items-center gap-6">
          <NoticeDefault>
            이 페이지는 <span className="text-green font-bold">임대인</span>이
            작성하는 주택임대차계약서 페이지예요.
            <br />
            <span className="font-bold">초록색</span>으로 표시된 항목을 작성하면
            돼요.
            <br />
            작성이 끝나면 <span className="font-bold">등록 완료</span>를 눌러
            임차인에게 계약서를 보내요.
          </NoticeDefault>

          <NoticeDefault>
            계약서 작성 전, <span className="font-bold">건축물대장</span>을
            준비해주세요.
          </NoticeDefault>
        </div>

        {/* ✅ 계약서 폼 컴포넌트 */}
        <Contract mode="lessor" ref={contractRef} />

        {/* ✅ 하단 버튼 */}
        <div className="flex justify-center gap-6 pt-8 pb-16">
          <Button
            size="medium"
            variant="default"
            onClick={handleTempSave}
            disabled={isSaving}
          >
            {isSaving ? "저장 중..." : "임시저장"}
          </Button>
          <Button
            size="medium"
            variant="point"
            onClick={handleFinalize}
            disabled={isFinalizing}
          >
            {isFinalizing ? "등록 중..." : "등록 완료"}
          </Button>
        </div>
      </main>
    </div>
  );
};

export default SellerContractPage;
