import { useEffect } from "react";
import ReactDOM from "react-dom/client";
import ChatPage from "../pages/ChatPage";

const ChatPopup = ({ onClose }: { onClose: () => void }) => {
  useEffect(() => {
    // 새 창 열기
    const newWindow = window.open(
      "",
      "_blank",
      "width=1000,height=700,left=200,top=200,menubar=no,toolbar=no,location=no,status=no"
    );

    // 새 창의 문서 설정
    if (newWindow) {
      newWindow.document.title = "채팅";
      const container = newWindow.document.createElement("div");
      newWindow.document.body.appendChild(container);
      newWindow.document.body.style.margin = "0";

      // React 앱 렌더링
      const root = ReactDOM.createRoot(container);
      root.render(
        <div style={{ width: "100%", height: "100%", fontFamily: "sans-serif" }}>
          <ChatPage />
          <button
            onClick={() => {
              onClose();
              newWindow.close();
            }}
            style={{
              position: "absolute",
              top: "10px",
              right: "10px",
              fontSize: "20px",
              cursor: "pointer",
              background: "none",
              border: "none",
            }}
          >
            ✕
          </button>
        </div>
      );

      // 창이 닫히면 콜백 실행
      const handleClose = () => {
        onClose();
      };
      newWindow.addEventListener("beforeunload", handleClose);

      return () => {
        newWindow.removeEventListener("beforeunload", handleClose);
        root.unmount();
        newWindow.close();
      };
    }
  }, [onClose]);

  return null; // 더 이상 본 창에 아무것도 렌더링하지 않음
};

export default ChatPopup;
