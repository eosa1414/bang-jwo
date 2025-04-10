// src/utils/loadScript.ts
export const loadIamportScript = (): Promise<void> => {
    return new Promise((resolve, reject) => {
      const existingScript = document.querySelector('script[src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"]');
      if (existingScript) {
        // 이미 스크립트가 로드된 상태이면 바로 resolve
        resolve();
        return;
      }
      const script = document.createElement("script");
      script.src = "https://cdn.iamport.kr/js/iamport.payment-1.2.0.js";
      script.async = true;
      script.onload = () => resolve();
      script.onerror = () => reject(new Error("아임포트 스크립트 로드 실패"));
      document.body.appendChild(script);
    });
  };
  