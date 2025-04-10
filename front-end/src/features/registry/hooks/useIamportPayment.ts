import { useEffect, useState } from "react";
import { loadIamportScript } from "../../../utils/loadIamport";
import { PaymentRequestParams } from "../../../apis/pay";

export const useIamportPayment = () => {
  const [scriptLoaded, setScriptLoaded] = useState(false);

  useEffect(() => {
    const load = async () => {
      try {
        await loadIamportScript();
        setScriptLoaded(true);
      } catch (err) {
        console.error("아임포트 스크립트 로드 실패", err);
      }
    };
    load();
  }, []);

  const requestPayment = (
    params: PaymentRequestParams,
    callback: (rsp: any) => void
  ) => {
    if (!scriptLoaded || !window.IMP) {
      throw new Error("아임포트가 아직 준비되지 않았습니다.");
    }

    window.IMP.init(import.meta.env.VITE_IMP_ID);

    window.IMP.request_pay(
      {
        pg: "html5_inicis",
        pay_method: "card",
        merchant_uid: params.merchant_uid,
        name: params.name,
        amount: params.amount,
        buyer_email: params.buyer_email,
        buyer_name: params.buyer_name,
        buyer_tel: params.buyer_tel,
        buyer_addr: params.buyer_addr,
        buyer_postcode: params.buyer_postcode,
      },
      callback
    );
  };

  return {
    scriptLoaded,
    requestPayment,
  };
};
