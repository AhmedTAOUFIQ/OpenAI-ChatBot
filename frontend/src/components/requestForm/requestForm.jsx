import './requestForm.css';
import {ArrowBarRight} from "react-bootstrap-icons"
import React, { useState } from 'react';


function RequestForm({onSubmit}) {
  const [question, setQuestion] = useState('');

  
  const handleInputChange = (event) => {
    const value = event.target.value;
    setQuestion(value);
  };

  const handleSubmit = (e)=>{
    e.preventDefault();
    onSubmit(question);
    setQuestion('');
  }
  return (
    <div >
      <form onSubmit={handleSubmit} >
        <div className="form-group  ">
          <input
            type="text"
            value={question}
            onChange={handleInputChange}
            className="myInput"
            placeholder="Write your question here"
          ></input>
          <button className="myBtn"  type="submit" >
          <ArrowBarRight/>
          </button>
        </div>
      </form>
    </div>
  );
}

export default RequestForm;