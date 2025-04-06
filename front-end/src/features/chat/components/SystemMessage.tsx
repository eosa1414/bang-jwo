import { FC } from "react";
import Button from "../../../components/buttons/Button";

interface SystemMessageProps {
  role: "임대인" | "임차인";
}

const SystemMessage: FC<SystemMessageProps> = ({ role }) => {
  const handleClick = () => {
    const path =
      role === "임대인" ? "/seller-contract-form" : "/tenant-contract-form";
    window.open(path, "_blank"); // ✅ 새 탭에서 열기
  };

  return (
    <div className="flex items-start gap-2 mb-3">
      {/* 노란 원형 프로필 */}
      <div className="w-10 h-10 rounded-full bg-yellow-400 flex-shrink-0" />

      {/* 메시지 블록 */}
      <div>
        {/* 닉네임 */}
        <div className="text-sm font-semibold text-neutral-black mb-1">
          방줘
        </div>

        {/* 말풍선 + 시간 */}
        <div className="flex items-end gap-2">
          <div className="bg-white px-4 py-3 rounded-2xl shadow border border-neutral-light200 max-w-xs">
            <div className="text-center">
              <p className="text-sm text-neutral-black whitespace-pre-line font-medium">
                {role} 임대차계약서{"\n"}작성하기
              </p>
              <div className="mt-2 flex justify-center">
                <Button size="small" variant="point" onClick={handleClick}>
                  확인
                </Button>
              </div>
            </div>
          </div>

          <span className="text-xs text-neutral-dark100">14:30</span>
        </div>
      </div>
    </div>
  );
};

export default SystemMessage;
