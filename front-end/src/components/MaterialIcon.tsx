interface MaterialIconProps {
  icon: string;
  fill?: boolean;
  size?: number;
}

const MaterialIcon = ({ icon, fill, size = 24 }: MaterialIconProps) => {
  return (
    <i
      className="material-symbols-rounded"
      style={{
        fontVariationSettings: `'FILL' ${fill ? 1 : 0}, 'opsz' ${size}`,
      }}
    >
      {icon}
    </i>
  );
};

export default MaterialIcon;
