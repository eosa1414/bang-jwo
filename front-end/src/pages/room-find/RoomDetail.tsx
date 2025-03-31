import { useEffect, useState, useRef } from "react";
import { motion, AnimatePresence } from "framer-motion";
import TabMenu from "./TabMenu";
import Accordion from "../../components/Accordion";
import ListItemLine from "../../components/ListItemLine";
import InfoText from "../../components/InfoText";
import TabContent from "./TabContent";
import Button from "../../components/buttons/Button";
import RoomOptions from "./RoomOptions";

interface RoomDetailProps {
  selectedRoomId: number | null;
  onClose: () => void;
}

const RoomDetail = ({ selectedRoomId, onClose }: RoomDetailProps) => {
  const [isHeaderColorChange, setIsHeaderColorChange] = useState(false);
  const [isTitleScrolled, setIsTitleScrolled] = useState(false);

  const boxRef = useRef<HTMLDivElement | null>(null);
  const headerRef = useRef<HTMLDivElement | null>(null);
  const titleRef = useRef<HTMLDivElement | null>(null);
  const textStartRef = useRef<HTMLDivElement | null>(null);

  const tabTitles = [
    "기본 정보",
    "관리비",
    "집 소개",
    "집 정보",
    "위치",
    "등기부등본 · 위험도",
  ];
  const roomOptions = ["보안/안전", "엘리베이터", "냉장고", "세탁기"];
  const maintenanceOptions = [
    "전기세",
    "인터넷",
    "가스",
    "청소비",
    "유선 TV",
    "주차비",
    "난방비",
  ];
  const notMaintenanceOptions = ["수도세"];

  // tab scroll
  const tabContentsRef = useRef<(HTMLDivElement | null)[]>([]);

  const handleTabClick = (idx: number) => {
    tabContentsRef.current[idx]?.scrollIntoView({
      behavior: "smooth",
      block: "start",
    });
  };

  useEffect(() => {
    const boxElement = boxRef.current;
    const headerElement = headerRef.current;
    const textStartElement = textStartRef.current;
    const titleElement = titleRef.current;

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

      if (headerElement && titleElement) {
        const headerRect = headerElement.getBoundingClientRect();
        const titleRect = titleElement.getBoundingClientRect();

        //title이 scroll로 가려지면 header에 title 등장
        if (titleRect.top <= headerRect.bottom) {
          setIsTitleScrolled(true);
        } else {
          setIsTitleScrolled(false);
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
          className="absolute z-[-1] top-0 bottom-0 p-[0.625rem] left-[340px] w-[360px]"
        >
          {/* gray box */}
          <div className="bg-neutral-light100 w-full h-full flex rounded-xl shadow-custom relative overflow-hidden text-sm">
            <div
              ref={boxRef}
              className="w-full h-full overflow-y-auto scroll-custom"
            >
              {/* box header */}
              <div
                ref={headerRef}
                className={`absolute z-1 w-full flex justify-between items-center p-[12px_14px] h-[48px] transition-colors duration-300 ${
                  isHeaderColorChange
                    ? "bg-real-white text-neutral-black"
                    : "text-neutral-white"
                }`}
              >
                <i className="material-symbols-rounded">arrow_back_ios</i>
                {isTitleScrolled && (
                  <span className="font-bold">월세 500/40</span>
                )}
                <i
                  className="material-symbols-rounded cursor-pointer"
                  onClick={onClose}
                >
                  close
                </i>
              </div>

              {/* Image */}
              <div className="bg-neutral-light100 w-full h-[13rem] overflow-hidden relative">
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
                className="flex flex-col gap-4 p-[0.875rem] mb-[0.375rem] bg-real-white"
              >
                {/* info header */}
                <div className="flex justify-between">
                  <div className="flex gap-2">
                    <Button
                      size="small"
                      isAngular={true}
                      variant="gold"
                      as="div"
                    >
                      집주인
                    </Button>
                    <Button
                      size="small"
                      isAngular={true}
                      isLine={true}
                      variant="gold"
                      as="div"
                    >
                      등록번호 {selectedRoomId}
                    </Button>
                  </div>
                  <i className="material-symbols-rounded">favorite</i>
                </div>

                {/* title */}
                <div className="flex flex-col gap-1">
                  <Accordion
                    trigger={
                      <Button size="small">
                        <i className="material-symbols-rounded text-base text-neutral-dark100">
                          keyboard_arrow_down
                        </i>
                        <span className="text-neutral-dark100">
                          가격 조정 가능
                        </span>
                      </Button>
                    }
                  >
                    <div className="text-neutral-dark100 py-2">
                      보증금 1000에 25로 합의 가능합니다.
                    </div>
                  </Accordion>

                  <div
                    ref={titleRef}
                    className="flex flex-wrap gap-2 items-center"
                  >
                    <span className="font-semibold text-xl">월세 500/40</span>
                    <span className="font-light text-neutral-dark100">
                      아파트
                    </span>
                  </div>
                  {/* review buttom */}
                  <div className="flex flex-wrap gap-1 items-center cursor-pointer">
                    <i className="material-symbols-rounded text-lg">reviews</i>
                    <span className="font-semibold">리뷰 1개</span>
                    <i className="material-symbols-rounded text-base">
                      arrow_forward_ios
                    </i>
                  </div>
                </div>

                {/* save price */}
                <div className="p-[.875rem_1rem] font-semibold border rounded-md border-neutral-light100">
                  직거래로 <span className="text-gold-dark">247,500원</span>을
                  아낄 수 있어요!
                </div>

                <div className="flex flex-wrap items-center gap-3">
                  <div className="w-[2.375rem] h-[2.375rem] bg-neutral-light100 rounded-full overflow-hidden">
                    <img
                      className="w-full h-full object-cover rounded-full"
                      src="https://images.pexels.com/photos/207272/pexels-photo-207272.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
                      alt="profile image"
                    />
                  </div>
                  <div className="flex gap-1">
                    <span>작성자</span>
                    <span>·</span>
                    <span>집에 가고 싶은 쥐</span>
                  </div>
                </div>

                <div className="divider" />

                <div className="text-neutral-dark100 flex flex-wrap gap-1">
                  <span>3일 전</span>
                  <span>·</span>
                  <span>1일 전 수정</span>
                </div>
                <div className="flex flex-wrap gap-1">
                  <Button size="small" variant="dark">
                    수정
                  </Button>
                  <Button size="small" variant="warning">
                    삭제
                  </Button>
                </div>
              </div>

              {/* Detail */}
              <TabMenu
                tabTitles={tabTitles}
                addClassName="sticky top-[48px] p-[0.875rem_1rem]"
                onTabClick={handleTabClick}
              />
              {/* Tab Contents */}
              <div className="flex flex-col gap-4 bg-real-white p-[0.875rem_1rem_calc(0.875rem+48px)_1rem]">
                <TabContent
                  title="기본 정보"
                  ref={(el) => {
                    if (el) tabContentsRef.current[0] = el;
                  }}
                >
                  <div>깔끔하고 전망 좋은 12층 집입니다.</div>
                  <RoomOptions roomOptions={roomOptions} />
                </TabContent>

                <div className="divider" />

                <TabContent
                  title="관리비"
                  ref={(el) => {
                    if (el) tabContentsRef.current[1] = el;
                  }}
                >
                  <p className="text-xl font-bold">120,000원</p>
                  <RoomOptions
                    title="관리비 포함 항목"
                    roomOptions={maintenanceOptions}
                  />
                  <RoomOptions
                    title="관리비와 별도인 항목"
                    roomOptions={notMaintenanceOptions}
                  />
                </TabContent>

                <div className="divider" />

                <TabContent
                  title="집 소개"
                  ref={(el) => {
                    if (el) tabContentsRef.current[2] = el;
                  }}
                >
                  <div>
                    <p>
                      12층이고 창문 앞으로 가로 막힌 게 없어서 전망이 좋습니다.
                      곰팡이 같은 것 없이 깔끔한 상태이구요.
                    </p>
                    <br />
                    <p>반려동물은 가능합니다.</p>
                    <br />
                    <p>주차 공간은 따로 없어요.</p>
                  </div>
                </TabContent>

                <div className="divider" />

                <TabContent
                  title="집 정보"
                  ref={(el) => {
                    if (el) tabContentsRef.current[3] = el;
                  }}
                >
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
                </TabContent>

                <div className="divider" />

                <TabContent
                  title="위치"
                  ref={(el) => {
                    if (el) tabContentsRef.current[4] = el;
                  }}
                >
                  <div className="w-full h-[160px] bg-neutral-gray"></div>
                  <div className="font-medium">
                    서울특별시 강남구 테헤란로 212
                  </div>
                </TabContent>

                <div className="divider" />

                <TabContent
                  title="등기부등본 · 위험도"
                  ref={(el) => {
                    if (el) tabContentsRef.current[5] = el;
                  }}
                >
                  <InfoText text="등기부등본을 확인하기 위해서는 700원의 수수료가 필요합니다." />
                  <Button size="large" variant="point" className="w-full">
                    [유료] 등기부등본·위험도 확인하러 가기
                  </Button>
                  <Button size="large" className="w-full">
                    지난 위험도 분석 결과 확인하기
                  </Button>
                </TabContent>
              </div>
              {/* box footer */}
              <div className="flex w-full h-[48px] bg-gold-light justify-around items-center bottom-[0px] absolute py-[0.875rem] text-base font-semibold">
                <div className="flex-grow text-center">
                  <p className="cursor-pointer">전화</p>
                </div>
                <span className="separator"></span>
                <div className="flex-grow text-center">
                  <p className="cursor-pointer">문의하기</p>
                </div>
              </div>
            </div>
          </div>
        </motion.div>
      )}
    </AnimatePresence>
  );
};

export default RoomDetail;
