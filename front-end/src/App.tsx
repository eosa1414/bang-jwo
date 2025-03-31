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

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
            <LayoutMain>
              <PageHome />
            </LayoutMain>
          }
        />
        <Route
          path="/room/find"
          element={
            <LayoutMain
              wrapperClassName="h-screen min-h-[calc(24rem+55px+7.9rem)] min-w-[calc(5.035rem+660px)]"
              mainClassName="flex flex-row overflow-hidden"
              hasFooter={false}
            >
              <PageRoomFind />
            </LayoutMain>
          }
        />
        <Route
          path="/room/sell"
          element={<LayoutMain>집 내놓기 화면</LayoutMain>}
        />
        <Route
          path="/mypage"
          element={<LayoutMain>마이페이지 화면</LayoutMain>}
        />
        <Route path="/test" element={<PageTest />} />
        <Route path="/test/button" element={<PageTestButton />} />
        <Route path="/seller-contract" element={<SellerContract />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
