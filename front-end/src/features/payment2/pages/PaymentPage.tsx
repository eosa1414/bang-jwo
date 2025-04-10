// App.tsx
import { useEffect, useState, FormEvent, JSX } from "react";
import PortOne from "@portone/browser-sdk/v2";

// 아이템 인터페이스
interface Item {
  id: string;
  name: string;
  price: number;
  currency: string;
}

// 결제 상태 타입 정의
type PaymentStatusType = "IDLE" | "PENDING" | "PAID" | "FAILED";

// 결제 상태 인터페이스
interface PaymentStatus {
  status: PaymentStatusType;
  message?: string;
}

// PortOne.requestPayment의 반환 결과에 대한 인터페이스
interface PaymentResponse {
  // 오류 발생 시 code가 있을 수 있음
  code?: number;
  message?: string;
  // 정상 결제 요청 시 paymentId가 반환됨
  paymentId?: string;
}

export function PaymentPage(): JSX.Element {
  const [item, setItem] = useState<Item | null>(null);
  const [paymentStatus, setPaymentStatus] = useState<PaymentStatus>({
    status: "IDLE",
  });

  useEffect(() => {
    async function loadItem() {
      try {
        const response = await fetch("/api/item");
        if (!response.ok) {
          throw new Error("아이템 정보를 불러오지 못했습니다.");
        }
        const data: Item = await response.json();
        setItem(data);
      } catch (error) {
        console.error("아이템 불러오기 에러:", error);
      }
    }
    loadItem();
  }, []);

  // item이 아직 로딩 중이면 dialog로 안내
  if (item === null) {
    return (
      <dialog open>
        <article aria-busy="true">결제 정보를 불러오는 중입니다.</article>
      </dialog>
    );
  }

  // 랜덤 결제 ID 생성 함수
  function randomId(): string {
    return [...crypto.getRandomValues(new Uint32Array(2))]
      .map((word) => word.toString(16).padStart(8, "0"))
      .join("");
  }

  // 결제 요청 핸들러
  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setPaymentStatus({ status: "PENDING" });
    const paymentId = randomId();

    let payment: PaymentResponse;
    try {
      const payment2 = await PortOne.requestPayment({
        storeId: import.meta.env.VITE_PORTONE_STORE_ID,
        channelKey: import.meta.env.VITE_PORTONE_CHANNEL_KEY,
        paymentId,
        orderName: item.name,
        totalAmount: item.price,
        currency: "CURRENCY_KRW",
        payMethod: "CARD",
        customData: {
          item: item.id,
        },
      });
      payment = payment2 as PaymentResponse;
    } catch (error) {
      console.error("결제 요청 중 에러 발생:", error);
      setPaymentStatus({ status: "FAILED", message: "결제 요청 실패" });
      return;
    }

    // 오류 코드가 포함된 경우 처리
    if (payment.code !== undefined) {
      setPaymentStatus({
        status: "FAILED",
        message: payment.message || "알 수 없는 결제 오류",
      });
      return;
    }

    // paymentId가 없으면 오류 처리
    if (!payment.paymentId) {
      setPaymentStatus({
        status: "FAILED",
        message: "결제 ID가 제공되지 않았습니다.",
      });
      return;
    }

    // 결제 완료 API 호출
    try {
      const completeResponse = await fetch("/api/payment/complete", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          paymentId: payment.paymentId,
        }),
      });
      if (completeResponse.ok) {
        const paymentComplete = await completeResponse.json();
        setPaymentStatus({
          status: paymentComplete.status,
        });
      } else {
        const errorMessage = await completeResponse.text();
        setPaymentStatus({
          status: "FAILED",
          message: errorMessage,
        });
      }
    } catch (error) {
      console.error("결제 완료 처리 에러:", error);
      setPaymentStatus({ status: "FAILED", message: "결제 완료 처리 실패" });
    }
  };

  const isWaitingPayment = paymentStatus.status === "PENDING";

  const handleClose = () => {
    setPaymentStatus({
      status: "IDLE",
    });
  };

  return (
    <>
      <main>
        <form onSubmit={handleSubmit}>
          <article>
            <div className="item">
              <div className="item-image">
                <img src={`/${item.id}.png`} alt={item.name} />
              </div>
              <div className="item-text">
                <h5>{item.name}</h5>
                <p>{item.price.toLocaleString()}원</p>
              </div>
            </div>
            <div className="price">
              <label>총 구입 가격</label>
              {item.price.toLocaleString()}원
            </div>
          </article>
          <button
            type="submit"
            aria-busy={isWaitingPayment}
            disabled={isWaitingPayment}
          >
            결제
          </button>
        </form>
      </main>
      {paymentStatus.status === "FAILED" && (
        <dialog open>
          <header>
            <h1>결제 실패</h1>
          </header>
          <p>{paymentStatus.message}</p>
          <button type="button" onClick={handleClose}>
            닫기
          </button>
        </dialog>
      )}
      <dialog open={paymentStatus.status === "PAID"}>
        <header>
          <h1>결제 성공</h1>
        </header>
        <p>결제에 성공했습니다.</p>
        <button type="button" onClick={handleClose}>
          닫기
        </button>
      </dialog>
    </>
  );
}

// import PortOne from "@portone/browser-sdk/v2"
// import { useEffect, useState } from "react"

// export function PaymentPage() {
//   const [item, setItem] = useState<{ id: string; name: string; price: number } | null>(null)
//   const [paymentStatus, setPaymentStatus] = useState<{
//     status: string
//     message?: string
//   }>({
//     status: "IDLE",
//   })

//   useEffect(() => {
//     async function loadItem() {
//       const response = await fetch("/api/item")
//       setItem(await response.json())
//     }

//     loadItem().catch((error) => console.error(error))
//   }, [])

//   if (item == null) {
//     return (
//       <dialog open>
//         <article aria-busy>결제 정보를 불러오는 중입니다.</article>
//       </dialog>
//     )
//   }

//   function randomId() {
//     return [...crypto.getRandomValues(new Uint32Array(2))]
//       .map((word) => word.toString(16).padStart(8, "0"))
//       .join("")
//   }

//   const handleSubmit = async (e: any) => {
//     e.preventDefault()
//     setPaymentStatus({ status: "PENDING" })
//     const paymentId = randomId()
//     const payment = await PortOne.requestPayment({
//       storeId: "store-e4038486-8d83-41a5-acf1-844a009e0d94",
//       channelKey: "channel-key-ebe7daa6-4fe4-41bd-b17d-3495264399b5",
//       paymentId,
//       orderName: item.name,
//       totalAmount: item.price,
//       currency: "CURRENCY_KRW",
//       payMethod: "CARD",
//       customData: {
//         item: item.id,
//       },
//     })
//     if (!payment || payment.code !== undefined) {
//       setPaymentStatus({
//         status: "FAILED",
//         message: payment?.message ?? "결제 요청에 실패했습니다.",
//       })
//       return
//     }
//     const completeResponse = await fetch("/api/payment/complete", {
//       method: "POST",
//       headers: {
//         "Content-Type": "application/json",
//       },
//       body: JSON.stringify({
//         paymentId: payment.paymentId,
//       }),
//     })
//     if (completeResponse.ok) {
//       const paymentComplete = await completeResponse.json()
//       setPaymentStatus({
//         status: paymentComplete.status,
//       })
//     } else {
//       setPaymentStatus({
//         status: "FAILED",
//         message: await completeResponse.text(),
//       })
//     }
//   }

//   const isWaitingPayment = paymentStatus.status !== "IDLE"

//   const handleClose = () =>
//     setPaymentStatus({
//       status: "IDLE",
//     })

//   return (
//     <>
//       <main>
//         <form onSubmit={handleSubmit}>
//           <article>
//             <div className="item">
//               <div className="item-image">
//                 <img src={`/${item.id}.png`} />
//               </div>
//               <div className="item-text">
//                 <h5>{item.name}</h5>
//                 <p>{item.price.toLocaleString()}원</p>
//               </div>
//             </div>
//             <div className="price">
//               <label>총 구입 가격</label>
//               {item.price.toLocaleString()}원
//             </div>
//           </article>
//           <button
//             type="submit"
//             aria-busy={isWaitingPayment}
//             disabled={isWaitingPayment}
//           >
//             결제
//           </button>
//         </form>
//       </main>
//       {paymentStatus.status === "FAILED" && (
//         <dialog open>
//           <header>
//             <h1>결제 실패</h1>
//           </header>
//           <p>{paymentStatus.message}</p>
//           <button type="button" onClick={handleClose}>
//             닫기
//           </button>
//         </dialog>
//       )}
//       <dialog open={paymentStatus.status === "PAID"}>
//         <header>
//           <h1>결제 성공</h1>
//         </header>
//         <p>결제에 성공했습니다.</p>
//         <button type="button" onClick={handleClose}>
//           닫기
//         </button>
//       </dialog>
//     </>
//   )
// }
