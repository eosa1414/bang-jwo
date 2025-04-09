// import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
// import  axiosInstance from '../utils/axiosInstances'
// nstance'
// // 채팅 관련 API
// export function useChatRooms(type = 'GROUP') {
//   return useQuery({
//     queryKey: ['chatRooms', type],
//     queryFn: () =>
//       axiosInstance.get('/chatting', { params: { type } }).then(res => res.data),
//     refetchInterval: 1000,
//   })
// }

// export function useJoinChatRoom() {
//   return useMutation({
//     mutationFn: chattingRoomId =>
//       axiosInstance
//         .post(`/chatting/${chattingRoomId}/join`)
//         .then(res => res.data),
//   })
// }

// export function useLeaveChatRoom() {
//   return useMutation({
//     mutationFn: chattingRoomId =>
//       axiosInstance
//         .post(`/chatting/${chattingRoomId}/leave`)
//         .then(res => res.data),
//   })
// }

// export function useChatHistory(chattingRoomId) {
//   return useQuery({
//     queryKey: ['chatHistory', chattingRoomId],
//     queryFn: () =>
//       axiosInstance.get(`/chatting/${chattingRoomId}`).then(res => res.data),
//   })
// }
