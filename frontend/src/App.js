import './App.css';
import RequestForm from './components/requestForm';
import SessionsList from './components/sessionsList';
import React, { useState, useEffect, useLayoutEffect } from 'react';
import { BrowserRouter as Routes, Route } from 'react-router-dom';
import { createSession } from './services/createSession';
import axios from 'axios';
import { BrowserRouter } from 'react-router-dom';
 


function App() {
  const [sessionId, setSessionId] = useState('');
  const [chatSessions, setChatSessions] = useState([]);

  useLayoutEffect(() => {
    document.body.style.backgroundColor = "#2c3034";
});




  

  return (
    
    
      <div className="container-fluid w-100 d-flex flex-fill">
        <div className="col-3" style={{ height: '100vh' ,overflowY: 'scroll' }}>
            <h2> Previous converstations </h2>
        <SessionsList chatSessions={chatSessions} setChatSessions={setChatSessions}/>
        </div>
        <div className="col-9" style={{ height: '100vh'}}>
        <RequestForm sessionId={sessionId}/>
        </div>
        
      </div>
     
    
  );
}

export default App;
