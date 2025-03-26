//router
import { BrowserRouter, Routes, Route } from "react-router-dom";
//layouts
import LayoutMain from "./layouts/LayoutMain";
//pages
import Home from "./pages/home/Home";
import RoomFind from "./pages/room-find/RoomFind";

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
              wrapperClassName="h-screen"
              mainClassName="flex flex-row overflow-y-auto"
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
      </Routes>
    </BrowserRouter>
  );
}

export default App;
