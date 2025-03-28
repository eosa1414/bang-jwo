import { useEffect, useState, useRef } from "react";
import { motion, AnimatePresence } from "framer-motion";
import TabMenu from "./TabMenu";
import Accordion from "../../components/Accordion";
import ListItemLine from "../../components/ListItemLine";
import InfoText from "../../components/InfoText";

interface RoomDetailProps {
  selectedRoomId: number | null;
  onClose: () => void;
}

const RoomDetail = ({ selectedRoomId, onClose }: RoomDetailProps) => {
  const [isHeaderColorChange, setIsHeaderColorChange] = useState(false);
  const boxRef = useRef<HTMLDivElement | null>(null);
  const headerRef = useRef<HTMLDivElement | null>(null);
  const textStartRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    const boxElement = boxRef.current;
    const headerElement = headerRef.current;
    const textStartElement = textStartRef.current;

    const handleScroll = () => {
      if (headerElement && textStartElement) {
        const headerRect = headerElement.getBoundingClientRect();
        const textStartRect = textStartElement.getBoundingClientRect();

        //헤더 색상 변경
        if (textStartRect.top <= headerRect.bottom) {
          setIsHeaderColorChange(true);
        } else {
          setIsHeaderColorChange(false);
        }
      }
    };

    if (boxElement) {
      boxElement.addEventListener("scroll", handleScroll);
    }

    return () => {
      if (boxElement) {
        boxElement.removeEventListener("scroll", handleScroll);
      }
    };
  });

  return (
    <AnimatePresence>
      {selectedRoomId && (
        <motion.div
          initial={{ x: "-100%", opacity: 0 }}
          animate={{ x: "0%", opacity: 1 }}
          exit={{ x: "-100%", opacity: 0 }}
          transition={{ duration: 0.3, ease: "easeOut" }}
          className="absolute z-[-1] top-0 bottom-0 p-[0.625rem] left-[340px] w-[320px]"
        >
          {/* gray box */}
          <div className="bg-neutral-light100 w-full h-full flex rounded-xl shadow-custom relative overflow-hidden">
            <div
              ref={boxRef}
              className="w-full h-full overflow-y-auto scroll-custom"
            >
              {/* box header */}
              <div
                ref={headerRef}
                className={`absolute z-1 w-full transition-colors duration-300 ${
                  isHeaderColorChange
                    ? "bg-real-white text-neutral-black"
                    : "text-neutral-white"
                }`}
              >
                <div className="w-full flex justify-between p-[12px_14px] h-[48px]">
                  <i className="material-symbols-rounded">arrow_back_ios</i>
                  <i
                    className="material-symbols-rounded cursor-pointer"
                    onClick={onClose}
                  >
                    close
                  </i>
                </div>
              </div>

              {/* Image */}
              <div className="bg-neutral-light100 w-full h-[12rem] overflow-hidden relative">
                <img
                  className="w-full h-full object-cover"
                  src="https://images.pexels.com/photos/1457842/pexels-photo-1457842.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
                  alt={`room image of number ${selectedRoomId}`}
                />
                {/* 그라데이션 오버레이 */}
                <div className="absolute top-0 left-0 bottom-0 right-0 w-full h-full bg-gradient-to-b from-black via-transparent to-transparent opacity-50"></div>
              </div>

              {/* Info */}
              <div
                ref={textStartRef}
                className="p-[0.875rem_1rem] mb-[0.375rem] bg-real-white"
              >
                {/* info header */}
                <div className="flex justify-between">
                  <div className="flex gap-1">
                    집주인 등록번호 {selectedRoomId}
                  </div>
                </div>
                {/* title */}
                <div className="flex flex-col gap-1">
                  <Accordion
                    trigger={
                      <button className="cursor-pointer">가격 조정 가능</button>
                    }
                  >
                    <p>test</p>
                  </Accordion>
                  <div className="flex flex-wrap gap-2">
                    <span>월세 500/40</span>
                    <span>아파트</span>
                  </div>
                  <div className="flex flex-wrap gap-1">
                    <i className="material-symbols-rounded">reviews</i>
                    <span>리뷰 1개</span>
                    <i className="material-symbols-rounded">
                      arrow_forward_ios
                    </i>
                  </div>
                </div>

                {/* save price */}
                <p>직거래로 247,500원을 아낄 수 있어요!</p>

                <div className="flex flex-wrap">
                  <img src="" alt="" />
                  <p className="flex gap-1">
                    <span>작성자</span>
                    <span>·</span>
                    <span>집에 가고 싶은 쥐</span>
                  </p>
                </div>

                <hr />
                <div>
                  <span>3일 전</span>
                  <span>·</span>
                  <span>1일 전 수정</span>
                </div>
                <div className="flex flex-wrap gap-1">
                  <button>수정</button>
                  <button>삭제</button>
                </div>
              </div>

              {/* Detail */}
              <TabMenu AddClassName="sticky top-[48px] p-[0.875rem_1rem]" />
              <div className="bg-real-white p-[0.875rem_1rem_calc(0.875rem+48px)_1rem]">
                <p>기본 정보</p>
                <p>깔끔하고 전망 좋은 12층 집입니다.</p>
                <div className="flex gap-1 flex-wrap">
                  <button>보안/안전</button>
                  <button>엘리베이터</button>
                  <button>냉장고</button>
                  <button>세탁기</button>
                </div>
                <hr />
                <div className="flex gap-1 flex-wrap">
                  <button>전기세</button>
                  <button>인터넷</button>
                  <button>가스</button>
                  <button>청소비</button>
                  <button>유선 TV</button>
                  <button>주차비</button>
                  <button>난방비</button>
                </div>

                <div>관리비와 별도인 항목</div>
                <div className="flex gap-1 flex-wrap">
                  <button>수도세</button>
                </div>
                <hr />

                <div>집 소개</div>
                <div>
                  12층이고 창문 앞으로 가로 막힌 게 없어서 전망이 좋습니다.
                  곰팡이 같은 것 없이 깔끔한 상태이구요. 반려동물은 가능합니다.
                  주차 공간은 따로 없어요.
                </div>
                <hr />

                <div>집 정보</div>
                <ListItemLine title="입주 가능일" content="즉시입주가능" />
                <div className="flex flex-1 items-center gap-1">
                  <ListItemLine
                    title="면적 (공급/전용)"
                    content="138.52㎡ / 110.57㎡"
                  />
                  <button>평</button>
                </div>
                <ListItemLine title="방 수 / 욕실 수" content="1개 / 1개" />
                <ListItemLine title="위치" content="1동 12층 (총 20층)" />
                <ListItemLine title="방향" content="남향 (거실 기준)" />
                <ListItemLine
                  title="주소"
                  content="서울특별시 강남구 테헤란로 212 1동 12층"
                />
                <ListItemLine title="사용 승인일" content="2007. 5. 14" />
                <ListItemLine
                  title="주차대수"
                  content="1,335대(세대 당 1.87대)"
                />
                <ListItemLine
                  title="부동산 고유 번호"
                  content="1345-2017-021929"
                />
                <ListItemLine title="총 세대 수" content="713세대" />
                <ListItemLine title="유형" content="아파트" />
                <hr />

                <div>위치</div>
                <div className="w-full h-[160px] bg-neutral-gray"></div>
                <div>서울특별시 강남구 테헤란로 212</div>
                <hr />

                <div>
                  <span>등기부등본</span>
                  <span>·</span>
                  <span>위험도</span>
                </div>
                <InfoText text="등기부등본을 확인하기 위해서는 700원의 수수료가 필요합니다." />
                <button>[유료] 등기부등본·위험도 확인하러 가기</button>
                <button>지난 위험도 분석 결과 확인하기</button>
              </div>
              {/* box footer */}
              <div className="w-full h-[48px] bg-gold bottom-[0px] absolute"></div>
            </div>
          </div>
        </motion.div>
      )}
    </AnimatePresence>
  );
};

export default RoomDetail;
