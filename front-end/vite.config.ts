// import { defineConfig } from "vite";
// import react from "@vitejs/plugin-react";
// import tailwindcss from "@tailwindcss/vite";
// import svgr from "vite-plugin-svgr";

// export default defineConfig({
//   plugins: [react(), tailwindcss(), svgr()],
//   server: {
//     proxy: {
//       '/chat': {
//         target: 'http://localhost:8080',
//         changeOrigin: true,
//         ws: true, // WebSocket 프록시 지원
//       },
//       '/api': {
//         target: 'http://localhost:8080',
//         changeOrigin: true,
//       },
//     },
//   },
//   define: {
//     global: {},
//   },
// });



// import { defineConfig } from "vite";
// import react from "@vitejs/plugin-react";
// import tailwindcss from "@tailwindcss/vite";
// import svgr from "vite-plugin-svgr";

// // https://vite.dev/config/
// export default defineConfig({
//   plugins: [react({ fastRefresh: false } as any), tailwindcss(), svgr()],
//   server: {
//     hmr: false,
//     proxy: {
//       '/ws': {
//         target: 'http://localhost:8080',
//         changeOrigin: true,
//         ws: true, // WebSocket 프록시 지원
//       },
//       // API 요청이 있다면 추가 설정 (예: /api)
//       '/api': {
//         target: 'http://localhost:8080',
//         changeOrigin: true,
//       },
//     },
//   },
//   define: {
//     global: {},
//   },
// });



import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import tailwindcss from "@tailwindcss/vite";
import svgr from "vite-plugin-svgr";
import path from "path";

export default defineConfig({
  plugins: [react(), tailwindcss(), svgr()],
  define: {
    global: {}, // stompjs 내부의 global 참조 문제 해결
  },
  resolve: {
    alias: [{ find: '@', replacement: path.resolve(__dirname, 'src') }],
  },
});
