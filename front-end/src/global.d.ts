declare global {
  interface Window {
    kakao: any;
  }
}

declare module "*.svg" {
  import { FunctionComponent, SVGProps } from "react";
  const content: FunctionComponent<SVGProps<SVGSVGElement>>;
  export default content;
}

export {};
