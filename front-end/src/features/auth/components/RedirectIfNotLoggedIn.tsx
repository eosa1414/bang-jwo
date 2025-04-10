import { JSX, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../../contexts/AuthContext";

type RedirectIfNotLoggedInProps = {
  children: JSX.Element;
};

const RedirectIfNotLoggedIn = ({ children }: RedirectIfNotLoggedInProps) => {
  const { status } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (status === "unauthenticated") {
      navigate("/login");
    }
  }, [status, navigate]);

  if (status === "loading") {
    return <div className="m-auto">로그인 확인 중...</div>;
  }

  return status === "authenticated" ? children : null;
};

export default RedirectIfNotLoggedIn;
