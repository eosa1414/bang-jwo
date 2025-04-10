export interface Alert {
  id: string;
    chatRoomId: number;
    receiverId: number;
    senderId: number;
    senderImage: string;
    senderNickname: string;
    message: string;
    sendAt: string;
    read: boolean;
}