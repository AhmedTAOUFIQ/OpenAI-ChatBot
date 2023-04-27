import React, { useRef, useEffect }  from 'react';
import './questionsAndAnswers.css'
function QuestionsAndAnswers({questionsAndAnswers}){
const listRef = useRef(null);

useEffect( () => {
  const scrollBottomlist= async () => {
    await listRef.current.lastChild.scrollIntoView({ behavior: 'smooth' });
};

scrollBottomlist()}, [questionsAndAnswers]);
  
    return (
      <div className="questionsAndAnswers">
       <ul ref={listRef}>
        {questionsAndAnswers?.map((object, index) => (
          <React.Fragment key={index}>
            <li className="question">{object.question}</li>
            <li className="answer">{object.answer}</li>
          </React.Fragment>
              ))}
            
        </ul>
      </div>
    );
  }


  export default QuestionsAndAnswers;


