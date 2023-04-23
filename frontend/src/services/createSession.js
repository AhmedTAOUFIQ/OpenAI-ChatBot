import axios from 'axios';
import { useState } from 'react';
const THE_URL="http://localhost:8080/sessions";
export const createSession = async(sessionId, setSessionId)=>{
 

  try {
    const response = await axios.get(`${THE_URL}/${sessionId}` );
    console.log("createSession called");
    console.log(sessionId);
    return response.data;
  } catch (error) {
    console.log(error);
  }
};