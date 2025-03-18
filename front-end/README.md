# 프로젝트 구조

```
front-end/
├── public/                   # 정적 파일
├── src/
│   ├── apis/                 # 외부 API 호출 (네트워크 요청)
│   ├── assets/               # 이미지, 아이콘 등
│   │   └── styles/        # 스타일
│   ├── components/           # 재사용 가능한 컴포넌트
│   ├── constants/            # 상수 값, 에러 코드 등
│   ├── features/             # 기능 단위로 분리하여 로직 및 상태 관리 (UI와 상태 관리, API 호출이 결합됨)
│   │   └── blockchain        # 블록체인 관련 기능(상태 관리 및 관련 훅, 페이지 모음)
│   ├── hooks/                # 커스텀 훅
│   ├── pages/                # 페이지 컴포넌트 (전체 레이아웃 및 구조를 담당하는 전체 화면 구성 컴포넌트, 라우팅에 연결되는 페이지)
│   ├── services/             # 비즈니스 로직, 상태 관리, 외부 서비스 연동 등 애플리케이션의 서비스 관련 코드, API 호출을 추상화하여 관리

│   ├── store/                # 상태 관리 (ex. Redux, Zustand, Recoil 등)
│   ├── utils/                # 유틸리티 함수들
│   ├── App.tsx               # 주요 애플리케이션 컴포넌트
│   ├── App.css               # App 컴포넌트 스타일 (리액트 프로젝트 기본 파일)
│   ├── index.css             # main 컴포넌트 스타일 (리액트 프로젝트 기본 파일)
│   ├── main.tsx              # 리액트 렌더링 진입점
│   └── vite-env.d.ts         # Vite 관련 타입 정의
├── .gitignore                # Git에 등록하지 않는 파일 명시
├── eslint.config.js          #  ESLint 설정 파일, 코드 품질과 스타일 유지를 위해 사용하는 설정 파일
├── index.html                # HTML 기본 뼈대
├── package-lock.json         # npm 패키지 매니저에서 의존성 트리 관리
├── package.json              # 프로젝트 설정 및 의존성 관리
├── README.md                 # 프로젝트 설명 파일
├── tsconfig.app.json         # 애플리케이션에서 쓰는 TypeScript 설정
├── tsconfig.json             # TypeScript 설정
├── tsconfig.json             # node 환경에서 쓰는 TypeScript 설정
└── vite.config.ts            # vite 빌드 도구 설정
```
