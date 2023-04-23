import React, { useState } from 'react';
import requestService from '../services/requestService';
import { useEffect } from 'react';
import { createSession } from '../services/createSession';

function RequestForm(props) {
  const [questionsandanswers, setQuestionsAndAnswers] = useState([]);
  const [question, setQuestion] = useState('');
  const [sessionId, setSessionId] = useState(props.sessionId || '');

  useEffect(() => {
    async function fetchData() {
      const newSessionId = await createSession(sessionId);
      setSessionId(newSessionId);
      console.log('i fire once');
      localStorage.setItem('sessionId', sessionId);
    }

    fetchData();
  }, [sessionId]);

  const handlePostRequest = async (e) => {
    e.preventDefault();
    const newAnswer = await requestService.postRequest(question, sessionId);
    setQuestionsAndAnswers(newAnswer);
  };

  const handleInputChange = (event) => {
    const value = event.target.value;
    setQuestion(value);
  };

  return (
    <div style={{ width: '100%' }}>
      <table style={{ height: 'calc(100vh - 120px)', overflowY: 'scroll' }} class="table table-striped table-dark flex-fill">
        <thead></thead>
        <tbody>
          {questionsandanswers.map((object, index) => (
            <React.Fragment key={index}>
              <tr>
                <td>{object.question}</td>
              </tr>
              <tr>
                <td>{object.answer}</td>
              </tr>
            </React.Fragment>
          ))}
        </tbody>
      </table>
      <form onSubmit={handlePostRequest} style={{  position: 'fixed', bottom: '0', width: '100%', zIndex: '1' }}>
        <div class="form-group container-fluid  d-flex flex-fill w-100">
          <input
            
            type="text"
            value={question}
            onChange={handleInputChange}
            class="form-control"
            id="theRequestText"
            placeholder="Write your question here"
          ></input>
          <button style={{visibility:"hidden"}}className=" btn btn-primary" type="submit" >
            Submit
          </button>
        </div>
      </form>
    </div>
  );
}

export default RequestForm;