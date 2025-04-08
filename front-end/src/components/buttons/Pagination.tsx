interface PaginationProps {
  totalPages: number;
  currentPage: number;
  onPageChange: (page: number) => void;
  className?: string;
}

const Pagination: React.FC<PaginationProps> = ({
  totalPages,
  currentPage,
  onPageChange,
  className = "",
}) => {
  if (totalPages < 1) return null;

  return (
    <div className={`flex justify-center gap-2 mt-4 ${className}`}>
      {Array.from({ length: totalPages }, (_, idx) => {
        const pageNum = idx + 1;
        const isActive = currentPage === pageNum;

        return (
          <button
            key={pageNum}
            onClick={() => onPageChange(pageNum)}
            className={`cursor-pointer px-3 py-1 rounded-md transition ${
              isActive
                ? "font-bold bg-gold text-neutral-white"
                : "bg-neutral-light100"
            }`}
          >
            {pageNum}
          </button>
        );
      })}
    </div>
  );
};

export default Pagination;
