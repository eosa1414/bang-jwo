import { Link, useNavigate } from "react-router-dom";
import Logo from "../Logo";
import { useAuth } from "../../contexts/AuthContext";
import Button from "../buttons/Button";

interface HeaderProps {
  title?: string;
  variant?: "dark" | "light";
}

const Header = ({ title, variant = "light" }: HeaderProps) => {
  const { user } = useAuth();
  const navigate = useNavigate();

  // 모바일 가로일 때 메뉴 동작 - 차후 추가
  // const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  // const toggleMobileMenu = () => {
  //   setIsMobileMenuOpen(!isMobileMenuOpen);
  // };

  return (
    <header className="flex w-full h-[55px] p-[12px_14px] justify-center items-center gap-[16px] border-b-1 border-neutral-light100">
      {/* Logo */}
      <div>
        <Link to="/" className="flex gap-2 items-center justify-center">
          <span className="pb-[3.5px]">
            <Logo className="w-[35.28px]" variant={variant} />
          </span>
          <span
            className={`text-lg font-['TmonMonsori'] ${
              variant === "light" ? "text-gold" : "text-neutral-black"
            }`}
          >
            방줘
          </span>
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
                {user ? (
                  <Link to="/mypage">
                    <li className="text-neutral-black">마이페이지</li>
                  </Link>
                ) : null}
              </ul>
            </nav>
            {/* 임시 로그인/로그아웃 버튼 */}
            {user ? (
              <>
                <i className="material-symbols-rounded">chat</i>
                <i className="material-symbols-rounded">account_circle</i>
              </>
            ) : (
              <Button size="small" onClick={() => navigate("/login")}>
                로그인 및 회원가입
              </Button>
            )}
          </div>
        </>
      )}
    </header>
  );
};

export default Header;
