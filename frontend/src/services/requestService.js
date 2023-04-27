import axios from 'axios';
const BACKEND_URL="http://localhost:8080/sessions";
    const requestService = {
        postRequest: async (data,sessionId) => {
          try {
            
            const response = await axios.post(`${BACKEND_URL}/${sessionId}/qa`, data,{
              headers: {
                "Content-Type": "text/plain",
              },
            });

            return response.data;
          } catch (error) {
            console.error(error);
            return [];
          }
}
    };

    export default requestService;