import axios from 'axios';
const THE_URL="http://localhost:8080/sessions";
const conversationService = {
  getConversation : async(sessionId)=>{
  try {
    const Conversation = await axios.get(`${THE_URL}/${sessionId}`);

    console.log("createSession called");
    console.log(sessionId);
    return Conversation.data;
  } catch (error) {
    console.log(error);
    return [];
  }
}
};
export default conversationService;