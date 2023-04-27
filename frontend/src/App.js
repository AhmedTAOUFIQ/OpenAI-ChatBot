import './App.css'; 
import RequestForm from './components/requestForm/requestForm';
import SessionsList from './components/sessionsList/sessionsList';
import requestService from './services/requestService';
import conversationService from './services/conversationService';
import QuestionsAndAnswers from './components/questionsAndAnswers/questionsAndAnswers';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const BACKEND_URL= "http://localhost:8080";


function App(){
  const [sessionId, setSessionId] = useState('newsession');
  const [questionsAndAnswers, setQuestionsAndAnswers] = useState([]);
  const [sessionsList, setSessionsList] = useState([]);
  
  useEffect(() => {
    const fetchChatSessions = async () => {
      try {
        const response = await axios.get(`${BACKEND_URL}/sessions/getsessions`);
        setSessionsList(response.data);
      } catch (error) {
        console.error(error);
        setSessionsList([]);
      }
    };
    fetchChatSessions();
  }, [questionsAndAnswers]);

 


const handleFormSubmit =  async(q)=>{
    const newAnswer = await requestService.postRequest(q, sessionId);
    setQuestionsAndAnswers(newAnswer.questionAnswerPairs);
    setSessionId(newAnswer.sessionId)
  };

 const handleChangeSession = async (sessionId)=>{
    setSessionId(sessionId);
    const conversation = await conversationService.getConversation(sessionId);
    setQuestionsAndAnswers(conversation);
    console.log(`session id is : ${sessionId}`);

 }

 
      
   
  
  return (
    
    
      <div className="container">
        <div className="col-3" >
        <SessionsList 
          sessionsList={sessionsList} 
          onChangeSession={handleChangeSession}
        />
        </div>
        <div className="col-9" >
          <QuestionsAndAnswers className="questionsAndAnswers" questionsAndAnswers={questionsAndAnswers}/>
          <RequestForm classNAme="request-form" onSubmit={handleFormSubmit} />
        </div>
        
      </div>
     
    
  );
}

export default App;