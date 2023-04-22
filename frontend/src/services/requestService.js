import axios from 'axios';
const BACKEND_BASE_URL="http://localhost:8080/sessions/000002";
    const requestService = {
        postRequest: async (data) => {
          try {
            
            const response = await axios.post(`${BACKEND_BASE_URL}/qa`, data,{
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