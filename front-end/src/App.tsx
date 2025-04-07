//router
import { BrowserRouter, Routes, Route } from "react-router-dom";
//layouts
import LayoutMain from "./layouts/LayoutMain";
//pages
import PageHome from "./pages/PageHome";
import PageTest from "./pages/PageTest";
import PageTestButton from "./pages/PageTestButton";
import SellerContract from "./features/contract/components/SellerContract";
import SellerContractPage from "./features/contract/pages/SellerContractPage";
import "react-datepicker/dist/react-datepicker.css";
import { AuthProvider } from "./contexts/AuthProvider";
import RedirectIfNotLoggedIn from "./features/auth/components/RedirectIfNotLoggedIn";
import PageMy from "./features/mypage/pages/PageMy";
import PageAccount from "./features/mypage/account/pages/PageAccount";
import RedirectRoomFind from "./features/room-find/components/RedirectRoomFind";
import PageNotFound from "./pages/PageNotFound";
import PageLogin from "./features/auth/pages/PageLogin";
import PageKakaoRedirect from "./features/auth/pages/PageKakaoRedirect";
import PageRoomSellCreate from "./features/room-sell/pages/PageRoomSellCreate";
import PageRoomSellUpdate from "./features/room-sell/pages/PageRoomSellUpdate";
import RoomForm from "./features/room-sell/components/RoomForm";
import VerifyOwner from "./features/room-sell/components/VerifyOwner";
import CreateSuccess from "./features/room-sell/components/CreateSuccess";
import RoomSellNotice from "./features/room-sell/components/RoomSellNotice";
import ChatPageOnly from "./features/chat/pages/ChatPageOnly";

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route
            path="/"
            element={
              <LayoutMain darkHeader wrapperClassName="bg-gold-light">
                <PageHome />
              </LayoutMain>
            }
          />
          <Route
            path="/room/find/:category?"
            element={
              <LayoutMain
                wrapperClassName="h-screen min-h-[calc(24rem+55px+7.9rem)] min-w-[calc(5.035rem+660px)]"
                mainClassName="flex flex-row overflow-hidden"
                hasFooter={false}
              >
                <RedirectRoomFind />
              </LayoutMain>
            }
          />
          <Route
            path="/room/sell"
            element={
              <LayoutMain>
                <PageRoomSellCreate />
              </LayoutMain>
            }
          >
            <Route index element={<RoomSellNotice />} />
            <Route path="write" element={<RoomForm type="create" />} />
            <Route path="verify" element={<VerifyOwner />} />
            <Route path="success" element={<CreateSuccess />} />
          </Route>
          <Route
            path="/room/update/:roomId"
            element={
              <LayoutMain>
                <PageRoomSellUpdate />
              </LayoutMain>
            }
          />
          <Route
            path="/login"
            element={
              <LayoutMain>
                <PageLogin />
              </LayoutMain>
            }
          />
          <Route
            path="/welcome"
            element={<LayoutMain>회원가입 완료</LayoutMain>}
          />
          <Route path="/auth/kakao/callback" element={<PageKakaoRedirect />} />
          <Route
            path="/mypage"
            element={
              <RedirectIfNotLoggedIn>
                <LayoutMain>
                  <PageMy />
                </LayoutMain>
              </RedirectIfNotLoggedIn>
            }
          >
            <Route index element={<PageAccount />} />
          </Route>
          <Route path="/test" element={<PageTest />} />
          <Route path="/test/button" element={<PageTestButton />} />
          <Route path="/seller-contract" element={<SellerContract />} />
          <Route
            path="/seller-contract-form"
            element={<SellerContractPage />}
          />
          <Route path="/chat" element={<ChatPageOnly />} />
          {/* 그 외 모든 페이지는 404 not found */}
          <Route
            path="*"
            element={
              <LayoutMain
                wrapperClassName="bg-gold-light"
                mainClassName="flex"
                darkHeader
              >
                <PageNotFound />
              </LayoutMain>
            }
          />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
