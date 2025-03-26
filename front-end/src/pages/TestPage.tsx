import React, { useState } from 'react'
import Button from '../components/buttons/Button'

function TestPage() {
  const [clicked, setClicked] = useState(false)

  const handleClick = () => {
    setClicked((prev) => !prev)
  }

  return (
    <>
      {/* 
        1) flex-col은 세로 배치
        2) items-start를 추가해 자식이 왼쪽 정렬 & 내용물 크기에 맞도록 (가로로 늘어나지 않음)
        3) 만약 버튼들을 화면 중앙에 놓고 싶다면, items-center로 변경 
      */}
      <div className="flex flex-col items-start gap-4 p-8 bg-white min-h-screen">
        <h1 className="text-3xl font-bold text-black">Test Page</h1>

        <Button
          onClick={handleClick}
          size="small"
          bgColor="neutral-light100"
          textColor="neutral-black"
        >
          텍스트
        </Button>

        <Button
          onClick={handleClick}
          size="small"
          bgColor="coral-red"
          textColor="neutral-white"
        >
          텍스트
        </Button>

        <Button
          size="basic"
          bgColor="neutral-dark200"
          textColor="neutral-white"
          disabled
        >
          비활성화
        </Button>

        <Button
          onClick={handleClick}
          size="medium"
          bgColor="gold-light"
          textColor="neutral-black"
        >
          확인
        </Button>

        <div className="text-black mt-4">
          클릭 상태: {clicked ? '눌렀어요!' : '아직 안 눌림'}
        </div>
      </div>
    </>
  )
}

export default TestPage
