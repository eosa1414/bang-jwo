//router
import { BrowserRouter, Routes, Route } from "react-router-dom";
//layouts
import LayoutMain from "./layouts/LayoutMain";
//pages
import PageHome from "./pages/PageHome";
import PageTest from "./pages/PageTest";
import PageRoomFind from "./features/room-find/pages/PageRoomFind";
import PageTestButton from "./pages/PageTestButton";
import SellerContract from "./features/contract/components/SellerContract";
import SellerContractPage from "./features/contract/pages/SellerContractPage";
import { AuthProvider } from "./contexts/AuthProvider";
import RedirectIfNotLoggedIn from "./features/auth/components/RedirectIfNotLoggedIn";
import PageMy from "./features/mypage/pages/PageMy";
import PageAccount from "./features/mypage/account/pages/PageAccount";
import RedirectRoomFind from "./features/room-find/components/RedirectRoomFind";

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
            element={<LayoutMain>집 내놓기 화면</LayoutMain>}
          />
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
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
