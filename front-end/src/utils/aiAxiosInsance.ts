import axios, {
    AxiosResponse,
    AxiosError,
    InternalAxiosRequestConfig,
  } from "axios";
  import { StatusCodes } from "../constants/statusCodes";
  
  const axios_ai = axios.create({
    baseURL: import.meta.env.VITE_AI_BASE_URL,
    withCredentials: true, //쿠키를 포함한 요청을 보낼지 여부
  });
  // 요청시 Bearer 헤더 추가
  const requestHandler = (config: InternalAxiosRequestConfig) => {
    const token = localStorage.getItem("accessToken");
    const isHeaderSettable =
      config.headers && typeof config.headers.set === "function";
  
    if (token && isHeaderSettable) {
      config.headers.set("Authorization", `Bearer ${token}`);
    }
  
    return config;
  };
  
  const requestErrorHandler = (error: any) => {
    return Promise.reject(error);
  };
  
  const responseHandler = (res: AxiosResponse) => {
    return res;
  };
  
  const responseErrorHandler = (err: AxiosError) => {
    if (err.response) {
      switch (err.response.status) {
        case StatusCodes.NOT_FOUND:
          //404 Not Found
          console.error("에러: Not Found");
          break;
        default:
          console.error("기타 에러: ", err.response.status);
          break;
      }
    } else if (err.request) {
      console.error("서버로부터 응답을 받지 못하였습니다.", err.request);
    } else {
      console.error("요청 설정 중 오류가 발생했습니다.", err.message);
    }
    return Promise.reject(err); //에러를 호출한 곳으로 전달함
  };
  
  axios_ai.interceptors.request.use(requestHandler, requestErrorHandler);
  axios_ai.interceptors.response.use(responseHandler, responseErrorHandler);
  

  export default axios_ai;