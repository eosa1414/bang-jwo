import { ReactNode, useEffect, useState } from "react";
import { createPortal } from "react-dom";

interface ModalPortalProps {
  children: ReactNode;
}

const ModalPortal = ({ children }: ModalPortalProps) => {
  const [portalElement, setPortalElement] = useState<HTMLElement | null>(null);

  useEffect(() => {
    let modalRoot = document.getElementById("modal-root");

    if (!modalRoot) {
      modalRoot = document.createElement("div");
      modalRoot.id = "modal-root";
      document.body.appendChild(modalRoot);
    }

    setPortalElement(modalRoot);
  }, []);

  if (!portalElement) return null;

  return createPortal(children, portalElement);
};

export default ModalPortal;
