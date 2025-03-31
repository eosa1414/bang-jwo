import HeaderContract from "../../../components/headers/HeaderContract";
import NoticeDefault from "../../../components/notices/NoticeDefault";
import SellerContract from "../components/SellerContract";

const SellerContractPage = () => {
  return (
    <div className="min-h-screen bg-white">
      <HeaderContract title="임대인 - 주택임대차계약서 작성" />

      <main className="flex flex-col gap-6 pt-10 px-4">
        <div className="flex flex-col items-center gap-6">
          <NoticeDefault>
            이 페이지는 <span className="text-green font-bold">임대인</span>
            이 작성하는 주택임대차계약서 페이지예요.
            <br />
            <span className="font-bold">초록색</span>으로 표시된 항목을 작성하면
            돼요.
            <br />
            작성이 끝나면 <span className="font-bold">제출하기</span>를 눌러
            임차인에게 계약서를 보내요.
          </NoticeDefault>

          <NoticeDefault>
            계약서 작성 전, <span className="font-bold">건축물대장</span>을
            준비해주세요.
          </NoticeDefault>
        </div>

        <SellerContract />
      </main>
    </div>
  );
};

export default SellerContractPage;
