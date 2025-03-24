import { useEffect, useRef } from "react";

interface KakaoMapProps {
  lat: number;
  lng: number;
}

const KakaoMap = ({ lat, lng }: KakaoMapProps) => {
  const mapContainer = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    const kakaoMapKey = import.meta.env.VITE_KAKAO_MAP_KEY;

    const script = document.createElement("script");
    script.type = "text/javascript";
    script.src = `https://dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoMapKey}&autoload=false`;
    document.head.appendChild(script);

    script.onload = () => {
      if (window.kakao && mapContainer.current) {
        const { kakao } = window;

        kakao.maps.load(() => {
          const center = new kakao.maps.LatLng(lat, lng);
          const options = {
            center,
            level: 3,
          };

          new kakao.maps.Map(mapContainer.current, options);
        });
      }
    };

    return () => {
      document.head.removeChild(script);
    };
  }, [lat, lng]);

  return <div ref={mapContainer} className="flex-grow h-full" />;
};

export default KakaoMap;
