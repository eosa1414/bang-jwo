import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import PageRoomFind from "../pages/PageRoomFind";

const RedirectRoomFind = () => {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    if (!location.search) {
      navigate("/room/find/onetworoom?lat=33.450701&lng=126.570667&zoom=4", {
        replace: true,
      });
    }
  }, [navigate, location]);

  return <PageRoomFind />;
};

export default RedirectRoomFind;
