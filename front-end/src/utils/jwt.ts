import { jwtDecode } from "jwt-decode";

export interface DecodedJwtPayload {
  sub: string;
  nickname: string;
  exp: number;
}

export const getUserFromToken = (token: string): DecodedJwtPayload | null => {
  try {
    const decoded = jwtDecode<DecodedJwtPayload>(token);
    return decoded;
  } catch (err) {
    console.error("Failed to decode Token:", err);
    return null;
  }
};
