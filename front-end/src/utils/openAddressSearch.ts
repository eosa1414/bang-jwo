import { DaumPostcodeData } from "../types/kakaoTypes";

export const openAddressSearch = (
  onComplete: (data: DaumPostcodeData) => void
) => {
  new window.daum.Postcode({
    oncomplete: function (data: DaumPostcodeData) {
      onComplete(data);
    },
  }).open();
};
