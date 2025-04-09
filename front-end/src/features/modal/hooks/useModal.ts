import { useState } from "react";

type ModalId = string;

export default function useModalManager() {
  const [openModals, setOpenModals] = useState<Record<ModalId, boolean>>({});

  const openModal = (id: ModalId) => {
    setOpenModals((prev) => ({ ...prev, [id]: true }));
  };

  const closeModal = (id: ModalId) => {
    setOpenModals((prev) => ({ ...prev, [id]: false }));
  };

  const isOpen = (id: ModalId) => !!openModals[id];

  return { isOpen, openModal, closeModal };
}
