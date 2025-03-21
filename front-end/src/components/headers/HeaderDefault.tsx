// src/components/Header.tsx
import { useState } from "react";
import { Link } from "react-router-dom";
import Logo from "/logo.svg";

interface HeaderProps {
  title?: string;
}

const Header = ({ title }: HeaderProps) => {
  // 임시로 로그인 안 된 상태를 설정
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // 임시 로그인/로그아웃 함수 (차후 API 연결시 변동)
  const handleLogin = () => {
    setIsLoggedIn(true); //임시 로그인 처리
  };
  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  // 모바일 가로일 때 메뉴 동작
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const toggleMobileMenu = () => {
    setIsMobileMenuOpen(!isMobileMenuOpen);
  };

  return (
    <header className="flex w-full h-[55px] p-[12px_14px] justify-center items-center gap-[16px]">
      {/* Logo */}
      <div>
        <Link to="/" className="flex gap-2 items-center">
          <img src={Logo} className="w-[35.28px]" alt="BangJwo logo" />
          <span className={`text-lg font-['TmonMonsori'] text-gold`}>방줘</span>
        </Link>
      </div>

      {/* title 값이 있다면 다른 형태로 렌더링 */}
      {title ? (
        <div className="text-lg font-bold">{title}</div>
      ) : (
        <>
          {/* title 값이 있다면 다른 형태로 렌더링 */}
          <div className="flex justify-end gap-8 flex-1 items-center">
            <nav>
              <ul className="flex gap-4">
                <Link to="/room/find">
                  <li className="text-neutral-black">집 찾기</li>
                </Link>
                <Link to="/room/sell">
                  <li className="text-neutral-black">집 내놓기</li>
                </Link>
                {isLoggedIn ? (
                  <Link to="/mypage">
                    <li className="text-neutral-black">마이페이지</li>
                  </Link>
                ) : null}
              </ul>
            </nav>
            {/* 임시 로그인/로그아웃 버튼 */}
            {isLoggedIn ? (
              <>
                <i class="material-symbols-rounded">chat</i>
                <i class="material-symbols-rounded">account_circle</i>
                <button onClick={handleLogout}>임시 로그아웃</button>
              </>
            ) : (
              <button onClick={handleLogin}>임시 로그인</button>
            )}
          </div>
        </>
      )}
    </header>
  );
};

export default Header;
