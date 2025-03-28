//router
import { BrowserRouter, Routes, Route } from "react-router-dom";
//layouts
import LayoutMain from "./layouts/LayoutMain";
//pages
import Home from "./pages/home/Home";
import TestPage from "./pages/TestPage";
import RoomFind from "./pages/room-find/RoomFind";
import TestButtonPage from "./pages/TestButtonPage";
import SellerContract from "./features/contract/components/SellerContract";
import SellerContractPage from "./features/contract/pages/SellerContractPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
            <LayoutMain>
              <Home />
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
              <RoomFind />
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
        <Route path="/test" element={<TestPage />} />
        <Route path="/test/button" element={<TestButtonPage />} />
        <Route path="/seller-contract" element={<SellerContract />} />
        <Route path="/seller-contract-form" element={<SellerContractPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
