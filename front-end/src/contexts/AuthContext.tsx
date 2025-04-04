import { createContext, useContext } from "react";
import { DecodedJwtPayload } from "../utils/jwt";

interface AuthContextType {
  accessToken: string | null;
  user: DecodedJwtPayload | null;
  login: (accessToken: string) => void;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const useAuth = () => {
  const context = useContext(AuthContext);

  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};

export default AuthContext;
