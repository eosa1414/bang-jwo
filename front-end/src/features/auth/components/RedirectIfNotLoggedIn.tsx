import { JSX, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../../contexts/AuthContext";

type RedirectIfNotLoggedInProps = {
  children: JSX.Element;
};

const RedirectIfNotLoggedIn = ({ children }: RedirectIfNotLoggedInProps) => {
  const { user } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!user) {
      navigate("/login");
    }
  }, [user, navigate]);

  return user ? children : null;
};

export default RedirectIfNotLoggedIn;
