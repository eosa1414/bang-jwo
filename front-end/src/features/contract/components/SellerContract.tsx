import NoticeDefault from "../../../components/notices/NoticeDefault";

const SellerContract = () => {
  return (
    // mx-auto 제거 + 내부 여백 유지
    <section className="w-full max-w-[800px] pt-6 pb-4">
      {/* 계약서 상단 공지 (좌측 정렬) */}
      <NoticeDefault>
        이 계약서는 법무부에서 제공하는 주택임대차표준계약서를 중개인 항목을
        제외하여 재구성한 것입니다.
        <br />
        법의 보호를 받기 위해 계약서 하단의 중요확인사항을 꼭 확인하시기
        바랍니다.
      </NoticeDefault>

      {/* 계약서 본문 */}
      <div className="mt-10">
        <h2 className="text-2xl font-extrabold text-center">
          주택임대차계약서
        </h2>
        {/* 계약서 본문 컴포넌트들 여기에 순차적으로 추가될 예정 */}
      </div>
    </section>
  );
};

export default SellerContract;
