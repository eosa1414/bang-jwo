import Button from "../../../components/buttons/Button";
import ContentWithTitle from "../../../components/ContentWithTitle";
import InfoText from "../../../components/InfoText";
import TitleBox from "../../../components/TitleBox";

const VerifyOwner = () => {
  return (
    <div className="justify-center items-center text-center flex flex-col gap-12">
      <TitleBox title="본인 확인" required>
        <div className="cursor-pointer w-[11.25rem] max-w-full h-[7.75rem] bg-gold flex flex-col p-2 items-center justify-center text-neutral-white font-bold text-lg rounded-md mx-auto">
          <p>휴대전화로</p>
          <p>인증하기</p>
        </div>
        <div className="text-sm">인증 완료되었습니다.</div>
      </TitleBox>
      <TitleBox title={`등기사항전부증명서 확인\n(구 등기부등본)`} required>
        <div className="mx-auto">
          <InfoText text="700원의 수수료가 듭니다." />
        </div>
        <div className="cursor-pointer w-[11.25rem] max-w-full h-[7.75rem] bg-gold flex flex-col p-2 items-center justify-center text-neutral-white font-bold text-lg rounded-md mx-auto">
          <p>등기사항전부증명서</p>
          <p>확인하기</p>
        </div>
        <div className="text-sm">인증 완료되었습니다.</div>
      </TitleBox>
      <Button size="large" variant="point">
        등록하기
      </Button>
    </div>
  );
};

export default VerifyOwner;
