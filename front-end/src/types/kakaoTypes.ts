export interface DaumPostcodeData {
  roadAddress: string;
  jibunAddress: string;
  zonecode: string;
  address: string;
  addressType: "R" | "J";
  buildingName: string;
  apartment: "Y" | "N";
  bname: string;
}

declare global {
  interface Window {
    daum: {
      Postcode: new (options: {
        oncomplete: (data: DaumPostcodeData) => void;
      }) => {
        open(): void;
      };
    };
    kakao: any;
  }
}

export {}; // 파일을 모듈로 간주하게 하여 declare global 사용
