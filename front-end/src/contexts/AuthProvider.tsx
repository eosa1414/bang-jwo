import { ReactNode, useEffect, useState } from "react";
import AuthContext from "./AuthContext";
import { DecodedJwtPayload, getUserFromToken } from "../utils/jwt";

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider = ({ children }: AuthProviderProps) => {
  const [accessToken, setAccessToken] = useState<string | null>(null);
  const [user, setUser] = useState<DecodedJwtPayload | null>(null);

  useEffect(() => {
    const storedToken = localStorage.getItem("accessToken");

    if (storedToken) {
      try {
        setAccessToken(storedToken);
        const decodedUser = getUserFromToken(storedToken);
        setUser(decodedUser);
      } catch (error) {
        console.error("Failed to decode or load token:", error);
        localStorage.removeItem("accessToken");
        setAccessToken(null);
        setUser(null);
      }
    }
  }, []);

  const login = (accessToken: string) => {
    setAccessToken(accessToken);
    localStorage.setItem("accessToken", accessToken);
    const decodedUser = getUserFromToken(accessToken);
    setUser(decodedUser);
  };

  const logout = () => {
    setAccessToken(null);
    setUser(null);
    localStorage.removeItem("accessToken");
  };

  return (
    <AuthContext.Provider value={{ accessToken, user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
