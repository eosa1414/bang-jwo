import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import PageRoomFind from "../pages/PageRoomFind";

const RedirectRoomFind = () => {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    if (!location.search) {
      navigate("/room/find/ONEROOM_TWOROOM?lat=37.5&lng=127.04&zoom=4", {
        replace: true,
      });
    }
  }, [navigate, location]);

  return <PageRoomFind />;
};

export default RedirectRoomFind;
