//router
import { BrowserRouter, Routes, Route } from "react-router-dom";
//layouts
import LayoutMain from "./layouts/LayoutMain";
//pages
import Home from "./pages/home/Home";
import TestPage from "./pages/TestPage";

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
          element={<LayoutMain>집 찾기 화면</LayoutMain>}
        />
        <Route
          path="/room/sell"
          element={<LayoutMain>집 내놓기 화면</LayoutMain>}
        />
        <Route
          path="/mypage"
          element={<LayoutMain>마이페이지 화면</LayoutMain>}
        />
        <Route path='/test' element={<TestPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
