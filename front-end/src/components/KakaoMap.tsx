import { useEffect, useRef } from "react";
import { loadKakaoMapScript, removeKakaoMapScript } from "../utils/kakaoMap";

interface KakaoMapProps {
  lat: number;
  lng: number;
  zoom?: number;
  onCenterChanged?: (lat: number, lng: number) => void;
  onZoomChanged?: (zoom: number) => void;
}

const KakaoMap = ({
  lat,
  lng,
  zoom = 4,
  onCenterChanged,
  onZoomChanged,
}: KakaoMapProps) => {
  const mapContainer = useRef<HTMLDivElement | null>(null);
  const mapInstance = useRef<any>(null);

  // 지도 초기 생성
  useEffect(() => {
    const initializeMap = async () => {
      await loadKakaoMapScript();
      const { kakao } = window;

      kakao.maps.load(() => {
        if (!mapContainer.current || mapInstance.current) return;

        const center = new kakao.maps.LatLng(lat, lng);
        const options = {
          center,
          level: zoom,
        };

        const map = new kakao.maps.Map(mapContainer.current, options);
        mapInstance.current = map;

        kakao.maps.event.addListener(map, "dragend", () => {
          const center = map.getCenter();
          const newLat = center.getLat();
          const newLng = center.getLng();
          if (onCenterChanged) onCenterChanged(newLat, newLng);
        });

        kakao.maps.event.addListener(map, "zoom_changed", () => {
          const newZoom = map.getLevel();
          if (onZoomChanged) onZoomChanged(newZoom);
        });
      });
    };

    initializeMap();
    return () => {
      removeKakaoMapScript();
    };
  }, []);

  useEffect(() => {
    if (mapInstance.current) {
      const { kakao } = window;
      const newCenter = new kakao.maps.LatLng(lat, lng);
      const map = mapInstance.current;
      const currentCenter = map.getCenter();
      if (
        currentCenter.getLat() !== newCenter.getLat() ||
        currentCenter.getLng() !== newCenter.getLng()
      ) {
        map.setCenter(newCenter);
      }
    }
  }, [lat, lng]);

  // zoom 변경 시 반영
  useEffect(() => {
    if (mapInstance.current && zoom !== undefined) {
      const map = mapInstance.current;
      const currentZoom = map.getLevel();
      if (currentZoom !== zoom) {
        map.setLevel(zoom);
      }
    }
  }, [zoom]);

  return <div ref={mapContainer} className="flex-grow h-full" />;
};

export default KakaoMap;
