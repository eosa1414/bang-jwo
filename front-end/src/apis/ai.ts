import axios_ai from "../utils/aiAxiosInsance";

interface request {
    question: string;
}

export const fetchChatbot = async (data: request) => {
  console.log(data);
  const res = await axios_ai.post("/api/v1/chatbot/message", data);
  console.log(res.data);
  return res.data;
};

