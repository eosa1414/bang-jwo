import { ReactNode, useEffect, useState } from "react";
import { createPortal } from "react-dom";

interface ToastPortalProps {
  children: ReactNode;
}

const ToastPortal = ({ children }: ToastPortalProps) => {
  const [portalElement, setPortalElement] = useState<HTMLElement | null>(null);

  useEffect(() => {
    let toastRoot = document.getElementById("toast-root");

    if (!toastRoot) {
      toastRoot = document.createElement("div");
      toastRoot.id = "toast-root";
      document.body.appendChild(toastRoot);
    }

    setPortalElement(toastRoot);
  }, []);

  if (!portalElement) return null;

  return createPortal(children, portalElement);
};

export default ToastPortal;
