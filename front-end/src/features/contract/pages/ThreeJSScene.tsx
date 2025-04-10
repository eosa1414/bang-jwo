import { useEffect, useRef } from "react";
import * as THREE from "three";
// import { gsap } from 'gsap';
import { useNavigate } from "react-router-dom";

const ThreeJSScene: React.FC = () => {
  const mountRef = useRef<HTMLDivElement>(null);
  const navigate = useNavigate();

  useEffect(() => {
    // 10초 후 리다이렉트 (예시: "/another-page"로 이동)
    const timer = setTimeout(() => {
      navigate("/mypage/contract");
    }, 7000);
    console.log(timer); //임시 타입 해결

    // Three.js 기본 설정
    const scene = new THREE.Scene();
    const camera = new THREE.PerspectiveCamera(
      75,
      window.innerWidth / window.innerHeight,
      0.1,
      1000
    );
    const renderer = new THREE.WebGLRenderer({ antialias: true });
    renderer.setSize(window.innerWidth, window.innerHeight);

    // mountRef div에 canvas 추가
    if (mountRef.current) {
      mountRef.current.appendChild(renderer.domElement);
    }

    // 중앙에 빛나는 큐브
    const cubeGeometry = new THREE.BoxGeometry();
    const cubeMaterial = new THREE.MeshStandardMaterial({
      color: 0xffd700, // 금색
      emissive: 0xffcc00,
      emissiveIntensity: 0.8,
      metalness: 1,
      roughness: 0.3,
    });
    const cube = new THREE.Mesh(cubeGeometry, cubeMaterial);
    scene.add(cube);

    // 입자 시스템
    const particleCount = 5000;
    const particleGeometry = new THREE.BufferGeometry();
    const positions = new Float32Array(particleCount * 3);
    for (let i = 0; i < particleCount * 3; i++) {
      positions[i] = (Math.random() - 0.5) * 10;
    }
    particleGeometry.setAttribute(
      "position",
      new THREE.BufferAttribute(positions, 3)
    );
    const particleMaterial = new THREE.PointsMaterial({
      color: 0xffd700,
      size: 0.05,
      transparent: true,
      opacity: 0.8,
    });
    const particles = new THREE.Points(particleGeometry, particleMaterial);
    scene.add(particles);

    // 광원 추가
    const pointLight = new THREE.PointLight(0xffd700, 2, 100);
    pointLight.position.set(5, 5, 5);
    scene.add(pointLight);
    scene.add(new THREE.AmbientLight(0x404040));

    camera.position.z = 3;

    // 애니메이션 루프
    const animate = () => {
      requestAnimationFrame(animate);
      cube.rotation.x += 0.01;
      cube.rotation.y += 0.01;
      particles.rotation.y += 0.002;
      particles.rotation.x += 0.001;
      renderer.render(scene, camera);
    };

    animate();

    // 창 크기 변화 대응
    const handleResize = () => {
      renderer.setSize(window.innerWidth, window.innerHeight);
      camera.aspect = window.innerWidth / window.innerHeight;
      camera.updateProjectionMatrix();
    };
    window.addEventListener("resize", handleResize);

    // Clean-up: 컴포넌트 언마운트시 이벤트 리스너 제거 및 renderer 제거
    return () => {
      window.removeEventListener("resize", handleResize);
      if (mountRef.current) {
        mountRef.current.removeChild(renderer.domElement);
      }
    };
  }, []);

  return <div ref={mountRef} style={{ width: "100%", height: "100vh" }} />;
};

export default ThreeJSScene;
