import { useEffect, useRef, useState } from "react";
import { loadKakaoMapScript, removeKakaoMapScript } from "../utils/kakaoMap";
import { Room } from "../types/roomTypes";

interface KakaoMapProps {
  lat: number;
  lng: number;
  zoom?: number;
  onCenterChanged?: (lat: number, lng: number) => void;
  onZoomChanged?: (zoom: number) => void;
  rooms?: Room[];
}

const KakaoMap = ({
  lat,
  lng,
  zoom = 4,
  onCenterChanged,
  onZoomChanged,
  rooms = [],
}: KakaoMapProps) => {
  const mapContainer = useRef<HTMLDivElement | null>(null);
  const mapInstance = useRef<any>(null);
  const markersRef = useRef<any[]>([]);
  const [isMapReady, setIsMapReady] = useState(false);

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

        setIsMapReady(true);
      });
    };

    initializeMap();
    return () => {
      removeKakaoMapScript();
      setIsMapReady(false);
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

  // 마커 (매물 위치 표시)
  useEffect(() => {
    if (!isMapReady || !mapInstance.current || !window.kakao || !rooms) return;

    const { kakao } = window;
    const map = mapInstance.current;

    // 이전 마커 제거
    markersRef.current.forEach((marker) => marker.setMap(null));
    markersRef.current = [];

    rooms.forEach((room) => {
      const position = new kakao.maps.LatLng(room.lat, room.lng);

      const content = `
        <div class="flex flex-col items-center">
          <div class="rounded-2xl px-6 py-3 bg-gold text-neutral-white text-lg font-semibold shadow-custom shadow-neutral-dark300/10">
            ${room.deposit / 10000}/${room.monthlyRent / 10000}
          </div>
          <div class="w-0 h-0 border-l-[10px] border-l-transparent border-r-[10px] border-r-transparent border-t-[10px] border-t-gold"></div>
        </div>
      `;

      const customOverlay = new kakao.maps.CustomOverlay({
        position,
        content,
        yAnchor: 1,
        zIndex: 3,
        map,
      });

      markersRef.current.push(customOverlay);
    });
  }, [rooms, isMapReady]);

  return <div ref={mapContainer} className="flex-grow h-full" />;
};

export default KakaoMap;
