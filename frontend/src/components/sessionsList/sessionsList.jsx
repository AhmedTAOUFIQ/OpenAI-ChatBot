import React from "react";
import './sessionsList.css'

function SessionsList  ({onChangeSession, sessionsList}) {

 const handleClick = async (e, sessionId) => {
  e.preventDefault();
  onChangeSession(sessionId);
}
  const reversedList = [...sessionsList].reverse();
  return (
    <div id="sessions-list">
    
    <div className="newSessionButton">
            <a href="/" onClick={(e)=>{handleClick(e,'newsession')}}>New Session</a>
    </div>
    <h2> Previous converstations </h2>
    <div id="theList">
    
      
       <ul>
        {reversedList.map((chatSession) => (
          <li key={chatSession.sessionId}>
            <a href="/"  onClick={(e)=>{handleClick(e,chatSession.sessionId)}}>
              {chatSession.subject}
            </a>
          </li>
        ))}
      </ul>
    </div>
    </div>
  );
};

export default SessionsList;