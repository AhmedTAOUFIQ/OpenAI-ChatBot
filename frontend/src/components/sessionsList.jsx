import { createSession } from "../services/createSession";
import { useEffect, useState } from "react";
import { Link } from 'react-router-dom';
import axios from "axios";
const BACKEND_BASE_URL = "http://localhost:8080/sessions";

const SessionsList = ({ chatSessions, setChatSessions, updateSessionId }) => {

 
  useEffect(() => {
    const fetchChatSessions = async () => {
      try {
        const response = await axios.get(`${BACKEND_BASE_URL}/getsessions`);
        setChatSessions(response.data);
        console.log('i fire once to get sessions from sessionsList');
      } catch (error) {
        console.error(error);
      }
    };
    fetchChatSessions();
  }, [setChatSessions]);

  const handleSessionClick = async (sessionId) => {
    const newSessionId = await createSession(sessionId);
    updateSessionId(newSessionId);

  };

  return (
    <ul>
      {chatSessions.map((chatSession) => (
        <li key={chatSession.sessionId}>
          <a href="/" onClick={handleSessionClick(chatSession.sessionId)}>
            {chatSession.subject}
          </a>
        </li>
      ))}
    </ul>
  );
};

export default SessionsList;