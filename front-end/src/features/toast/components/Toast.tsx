import { useEffect } from "react";
import { motion, AnimatePresence } from "framer-motion";
import ToastPortal from "./ToastPortal";

interface ToastProps {
  message: string;
  onClose: () => void;
  duration?: number;
}

const Toast = ({ message, onClose, duration = 3000 }: ToastProps) => {
  useEffect(() => {
    const timer = setTimeout(onClose, duration);
    return () => clearTimeout(timer);
  }, [onClose, duration]);

  return (
    <ToastPortal>
      <AnimatePresence>
        {message && (
          <motion.div
            initial={{ x: 100, opacity: 0 }}
            animate={{ x: 0, opacity: 1 }}
            exit={{ x: 100, opacity: 0 }}
            transition={{ duration: 0.4 }}
            className="fixed top-6 right-6 z-[10000] bg-neutral-dark300 text-neutral-white px-4 py-2 rounded shadow-lg"
          >
            {message}
          </motion.div>
        )}
      </AnimatePresence>
    </ToastPortal>
  );
};

export default Toast;
