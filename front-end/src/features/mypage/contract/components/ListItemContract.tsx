import Button from "../../../../components/buttons/Button";
import LineBox from "../../../../components/LineBox";
import MaterialIcon from "../../../../components/MaterialIcon";
import { useRoomNavigation } from "../../../../hooks/useRoomNavigation";
import { Room, RoomBuildingType } from "../../../../types/roomTypes";
import { roomBuildingTypeLabel } from "../../../../utils/roomMapper";

interface ListItemContractProps {
  contract: Room;
}

const ListItemContract = ({ contract }: ListItemContractProps) => {
  const { goToRoomDetail } = useRoomNavigation();

  const handleAddressClick = () => {
    goToRoomDetail(contract.lat, contract.lng, contract.roomId);
  };

  return (
    <>
      <li className="w-full sm:w-1/2 md:w-1/3 p-2">
        <LineBox addClassName=" flex flex-col justify-center items-center gap-4">
          <div className="flex flex-col gap-1 items-center">
            <Button as="div" size="small" isAngular variant="gold">
              내가 계약한 집
            </Button>
            <div
              onClick={handleAddressClick}
              className="cursor-pointer flex flex-wrap items-center"
            >
              <div className="flex gap-1 text-xl font-semibold">
                <span>월세</span>
                <span>
                  {contract.deposit / 10000}/{contract.monthlyRent / 10000}
                </span>
              </div>
              <MaterialIcon icon="arrow_forward_ios" />
            </div>
            <div className="font-light text-neutral-gray">
              {contract.buildingType in roomBuildingTypeLabel
                ? roomBuildingTypeLabel[
                    contract.buildingType as RoomBuildingType
                  ]
                : "기타"}
            </div>
          </div>
          <Button size="small">계약서 작성하기</Button>
        </LineBox>
      </li>
    </>
  );
};

export default ListItemContract;
